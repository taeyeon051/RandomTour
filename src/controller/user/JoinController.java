package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;

public class JoinController implements Controller {
	private String userIdRegex = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}";
    private String nicknameRegex = "[A-Za-z0-9°¡-ÆR]{2,16}";
    private String passwordRegex = "(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*().])[A-Za-z\\d!@#$%^&*().]{10,}";
	
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/views/join.jsp");
		}
		
		String userId = request.getParameter("user-id");
		String nickname = request.getParameter("user-nickname");
		String password = request.getParameter("user-pwd");
		String passwordCheck = request.getParameter("user-pwdc");
		
		System.out.println(userId);
		if (!userId.matches(userIdRegex)) {
			request.setAttribute("alert", "¾ÆÀÌµğ´Â ÀÌ¸ŞÀÏ Çü½ÄÀÌ¾î¾ß ÇÕ´Ï´Ù.");
			return new MyView("/views/join.jsp");
		}
		if (!nickname.matches(nicknameRegex)) {
			request.setAttribute("alert", "´Ğ³×ÀÓÀº ÇÑ±Û,¿µ¹®,¼ıÀÚ¸¸ »ç¿ë °¡´ÉÇÏ¸ç 2±ÛÀÚ ÀÌ»ó 16±ÛÀÚ ÀÌÇÏ¿©¾ß ÇÕ´Ï´Ù.");
			return new MyView("/views/join.jsp");
		}
		if (!password.matches(passwordRegex)) {
			request.setAttribute("alert", "ºñ¹Ğ¹øÈ£´Â ¿µ¹® ´ë¼Ò¹®ÀÚ,¼ıÀÚ,±âÈ£¸¦ Æ÷ÇÔÇÏ¿©Çá ÇÏ¸ç 10±ÛÀÚ ÀÌ»óÀÌ¿©¾ß ÇÕ´Ï´Ù.");
			return new MyView("/views/join.jsp");
		}
		if (!password.equals(passwordCheck)) {
			request.setAttribute("alert", "ºñ¹Ğ¹øÈ£¿Í È®ÀÎÀÌ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
			return new MyView("/views/join.jsp");
		}
		
		UserDAO dao = new UserDAO();
		boolean userIdCheck = dao.userIdCheck(userId);
		boolean nicknameCheck = dao.nicknameCheck(nickname);
		
		if (userIdCheck) {
			request.setAttribute("alert", "¾ÆÀÌµğ°¡ Áßº¹µË´Ï´Ù.");
			return new MyView("/views/join.jsp");			
		}
		if (nicknameCheck) {
			request.setAttribute("alert", "´Ğ³×ÀÓÀÌ Áßº¹µË´Ï´Ù.");
			return new MyView("/views/join.jsp");
		}
		
		return new MyView("/views/login.jsp");
	}
}
