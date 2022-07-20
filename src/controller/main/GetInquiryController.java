package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.InquiryDAO;
import vo.InquiryVO;

public class GetInquiryController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InquiryDAO dao = new InquiryDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		InquiryVO vo = dao.getInquiry(id);
		
		request.setAttribute("inquiry", vo);
		
		return new MyView("/views/components/mypage/GetInquiry.jsp");
	}
}
