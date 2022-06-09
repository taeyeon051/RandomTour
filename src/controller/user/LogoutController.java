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

public class LogoutController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("randomtour-user");
		int n = 0;
		if (user != null) {
			UserDAO dao = new UserDAO();
			n = dao.userLogout(user);
		}
		
		if (n > 0) {
			session.removeAttribute("randomtour-user");
			request.setAttribute("success", "로그아웃 되었습니다.");
		}

		return new MyView("/views/login.jsp");
	}
}
