package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.FriendDAO;
import dao.UserDAO;
import vo.FriendVO;

public class AddFriendController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/main/mypage");
		}
		
		UserDAO userDao = new UserDAO();
		FriendDAO friendDao = new FriendDAO();
		String userId = request.getParameter("user-id");
		String nickname = request.getParameter("user-nickname");
		
		boolean nicknameCheck = userDao.nicknameCheck(nickname);
		if (!nicknameCheck) {
			request.setAttribute("alert", "존재하지 않는 닉네임입니다.");
			return new MyView("/main/mypage");
		}
		
		FriendVO vo = new FriendVO(userId, nickname, false);
		int n = friendDao.sendFriend(vo);
		if (n > 0) {
			request.setAttribute("success", "친구요청이 전송되었습니다.");
		} else {
			request.setAttribute("alert", "친구요청 전송에 실패하였습니다.");
		}
		
		return new MyView("/main/mypage");
	}
}
