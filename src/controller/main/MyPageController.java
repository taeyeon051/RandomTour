package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.MyView;
import dao.FriendDAO;
import dao.MyPageDAO;
import vo.ChattingVO;
import vo.UserVO;

public class MyPageController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("randomtour-user");
		if (user == null) return new MyView("/user/login", true);
		
		String page = request.getParameter("p");
		String nickname = request.getParameter("nickname");
		
		if (page != null && nickname != null) {
			FriendDAO friendDao = new FriendDAO();
			MyPageDAO mypageDao = new MyPageDAO();
			boolean friendCheck = friendDao.friendCheck(user.getUserId(), nickname);
			if (!friendCheck) return new MyView("/main/mypage?p=list", true);
			ChattingVO vo = new ChattingVO(user.getUserId(), friendDao.getUserId(nickname), "", null, null);
			mypageDao.insertChat(vo);
		}

		return new MyView("/views/myPage.jsp");
	}
}
