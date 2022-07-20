package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.InquiryDAO;
import vo.InquiryVO;

public class SendInquiryController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/main/mypage", true);
		}

		InquiryDAO dao = new InquiryDAO();
		String userId = request.getParameter("user-id");
		String title = request.getParameter("title");
		String select = request.getParameter("select");
		String content = request.getParameter("content");
		InquiryVO vo = new InquiryVO(0, userId, title, select, content);

		int n = dao.sendInquiry(vo);
		if (n > 0) {
			request.setAttribute("state", "success");
			request.setAttribute("message", "성공적으로 전송되었습니다.");
		} else {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "전송에 실패하였습니다. 계속 안 될 경우 randomtour@naver.com으로 문의하시기 바랍니다.");
		}

		return new MyView("/views/ajax/alert.jsp");
	}
}
