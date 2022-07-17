package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.FriendDAO;
import util.MailUtil;

public class SendInquiryController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/main/mypage", true);
		}

		FriendDAO dao = new FriendDAO();
		String userId = request.getParameter("user-id");
		String title = request.getParameter("title");
		String select = request.getParameter("select");
		String content = request.getParameter("content");

		int n = dao.sendInquiry(userId, title, select, content);
		
//		boolean sendmail = MailUtil.sendMail(userId, title, select, content);
		request.setAttribute("state", n > 0);

		return new MyView("/views/ajax/sendMail.jsp");
	}
}
