package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;
import vo.UserVO;

public class LoginController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/views/login.jsp");
		}

		UserDAO dao = new UserDAO();
		String userId = request.getParameter("user-id");
		String password = request.getParameter("user-pwd");

		if (userId.trim().equals("") || password.trim().equals("")) {
			request.setAttribute("alert", "빈 값이 있습니다.");
			return new MyView("/views/login.jsp");
		}
		
		boolean loginCheck = dao.loginCheck(userId);
		if (loginCheck) {
			request.setAttribute("alert", "다른 기기에서 로그인 중인 아이디입니다.");
			return new MyView("/views/login.jsp");
		}
		
		UserVO vo = dao.userLogin(userId, password);
		if (vo != null) {
			HttpSession session = request.getSession();
			int SESSION_MAX_TIME = 3660;
			session.setMaxInactiveInterval(SESSION_MAX_TIME);
			session.setAttribute("randomtour-user", vo);
			return new MyView("/main/roomList", true);
		} else {
			request.setAttribute("alert", "아이디 혹은 비밀번호가 올바르지 않습니다.");
			return new MyView("/views/login.jsp");
		}
	}
}
