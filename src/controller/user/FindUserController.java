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
			if (userId.equals("")) {
				request.setAttribute("alert", "이름과 닉네임에 맞는 아이디가 존재하지 않습니다.");
				return new MyView("/views/findUser.jsp");
			} else {
				request.setAttribute("userId", userId);
				return new MyView("/views/ajax/findId.jsp");
			}
		}
		
		return new MyView("/views/findUser.jsp");
	}
}
