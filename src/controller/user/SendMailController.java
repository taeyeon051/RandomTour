package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import util.MailUtil;
import util.RandomCode;

public class SendMailController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/index.jsp");
		}
		
		String certifyNumber = RandomCode.randomNumber(6);
		boolean sendmail = MailUtil.sendMail(request.getParameter("user-id"), "<h3>인증번호는 " + certifyNumber + "입니다.</h3>");
		
		if (sendmail) {
			request.setAttribute("state", certifyNumber);
		} else {
			request.setAttribute("state", "실패");
		}
		return new MyView("/views/ajax/sendMail.jsp");
	}

}
