package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;

public class UpdatePwdController implements Controller {
	private String passwordRegex = "(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*().])[A-Za-z\\d!@#$%^&*().]{10,}";
	
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/views/findUser.jsp");
		}
		
		UserDAO dao = new UserDAO();
		String userId = request.getParameter("user-id");
		String password = request.getParameter("user-pwd");
		String passwordCheck = request.getParameter("user-pwdc");
		
		boolean userIdCheck = dao.userIdCheck(userId);
		if (!userIdCheck) {
			request.setAttribute("alert", "존재하지 않는 아이디입니다.");
			return new MyView("/views/join.jsp");
		}
		if (!password.matches(passwordRegex)) {
			request.setAttribute("alert", "비밀번호는 영문 대소문자,숫자,기호를 포함하여햐 하며 10글자 이상이여야 합니다.");
			return new MyView("/views/findUser.jsp");
		}
		if (!password.equals(passwordCheck)) {
			request.setAttribute("alert", "비밀번호와 확인이 일치하지 않습니다.");
			return new MyView("/views/findUser.jsp");
		}
		
		int n = dao.updatePwd(userId, password);
		if (n > 0) {
			request.setAttribute("success", "비밀번호가 변경되었습니다.");			
			return new MyView("/index.jsp");
		} else {
			request.setAttribute("alert", "비밀번호 변경에 실패하였습니다. 잠시 후에 다시 시도해주세요.");
			return new MyView("/views/findUser.jsp");
		}
	}
}
