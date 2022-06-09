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

		String userId = request.getParameter("user-id");
		String password = request.getParameter("user-pwd");
		
		if (userId.trim().equals("") || password.trim().equals("")) {
			request.setAttribute("alert", "빈 값이 있습니다.");
			return new MyView("/view/login.jsp");
		}
		
		UserDAO dao = new UserDAO();
		UserVO vo = dao.userLogin(userId, password);
		
		if (vo != null) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(3660);
			session.setAttribute("randomtour-user", vo);
			request.setAttribute("success", "성공적으로 로그인 되었습니다.");
			return new MyView("/views/roomList.jsp");
		} else {
			request.setAttribute("alert", "아이디 혹은 비밀번호가 올바르지 않습니다.");
			return new MyView("/views/login.jsp");
		}
	}
}
