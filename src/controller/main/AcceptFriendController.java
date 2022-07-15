package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.FriendDAO;
import vo.FriendVO;

public class AcceptFriendController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/main/mypage", true);
		}

		FriendDAO dao = new FriendDAO();
		String userId = request.getParameter("user-id");
		String nickname = request.getParameter("nickname");
		boolean accept = Boolean.parseBoolean(request.getParameter("accept"));
		String friendId = dao.getUserId(nickname);
				
		int n = 0;
		FriendVO vo = new FriendVO(friendId, userId, true);
		if (accept) {
			n = dao.acceptFriend(vo);
		} else {
			n = dao.deleteFriend(vo);
		}
		
		if (n > 0 && accept) {
			request.setAttribute("state", "success");
			request.setAttribute("message", nickname + "님의 친구 요청을 수락하였습니다.");
		} else if (!accept) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", nickname + "님의 친구 요청을 거절하였습니다.");
		} else {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "잘못된 정보이거나 DB 오류로 처리에 실패하였습니다. 잠시 후 다시 시도해주세요.");
		}
		
		return new MyView("/views/ajax/alert.jsp");
	}
}
