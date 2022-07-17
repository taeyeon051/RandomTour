package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;
import util.MailUtil;
import util.RandomCode;

public class SendMailController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/index.jsp");
		}
		
		UserDAO dao = new UserDAO();
		String userId = request.getParameter("user-id");
		String certifyNumber = RandomCode.randomNumber(6);
		int n = dao.setCertifyNumber(userId, certifyNumber);
		if (n > 0) {
			String title = "이메일 인증하기";
			String content = "<h3>인증번호는 " + certifyNumber + "입니다.</h3>";
			boolean sendmail = MailUtil.sendMail(userId, title, "certify", content);
			request.setAttribute("state", sendmail);
		} else {
			request.setAttribute("state", "실패");
		}
		return new MyView("/views/ajax/sendMail.jsp");
	}
}
