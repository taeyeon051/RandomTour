package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;

public class FindUserController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/views/findUser.jsp");
		}
		
		String type = request.getParameter("type");
		
		if (type.equals("id")) {
			String username = request.getParameter("user-name");
			String nickname = request.getParameter("nickname");
			UserDAO dao = new UserDAO();
			String userId = dao.findUserId(username, nickname);
			request.setAttribute("userId", userId);
			return new MyView("/views/ajax/findId.jsp");
		} else if (type.equals("pw")) {
			String userId = request.getParameter("user-id");
			String certify = request.getParameter("certify-number");
			UserDAO dao = new UserDAO();
			if (!certify.equals(dao.certify(userId))) {
				request.setAttribute("state", "실패");
			} else {
				request.setAttribute("state", "성공");
			}
			return new MyView("/views/ajax/findPw.jsp");
		}
		
		return new MyView("/views/findUser.jsp");
	}
}
