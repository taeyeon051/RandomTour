package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.FriendDAO;
import vo.FriendVO;

public class DeleteFriendController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/main/mypage", true);
		}
		
		FriendDAO dao = new FriendDAO();
		String userId = request.getParameter("user-id");
		String nickname = request.getParameter("nickname");
		String friendId = dao.getUserId(nickname);
		
		int n = 0;
		FriendVO vo = new FriendVO(userId, friendId, true);
		n = dao.deleteFriend(vo);
		
		if (n > 0) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "삭제되었습니다.");
		} else {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "잘못된 정보이거나 DB 오류로 처리에 실패하였습니다. 잠시 후 다시 시도해주세요.");
		}
		
		return new MyView("/views/ajax/alert.jsp");
	}
}
