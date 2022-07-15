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
			return new MyView("/main/mypage", true);
		}
		
		UserDAO userDao = new UserDAO();
		FriendDAO friendDao = new FriendDAO();
		String userId = request.getParameter("user-id");
		String nickname = request.getParameter("nickname");
		
		if (userId.equals(friendDao.getUserId(nickname))) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "자기 자신에게 친구 요청을 보낼 수 없습니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		
		boolean nicknameCheck = userDao.nicknameCheck(nickname, userId);
		if (!nicknameCheck) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "존재하지 않는 닉네임입니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		
		boolean friendCheck = friendDao.friendCheck(userId, nickname);
		if (friendCheck) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "이미 친구추가가 된 닉네임입니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		
		String friendId = friendDao.getUserId(nickname);
		FriendVO vo = new FriendVO(userId, friendId, false);
		int n = friendDao.sendFriend(vo);
		if (n > 0) {
			request.setAttribute("state", "success");
			request.setAttribute("message", "친구요청이 전송되었습니다.");
		} else {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "친구요청 전송에 실패하였습니다.");
		}
		
		return new MyView("/views/ajax/alert.jsp");
	}
}
