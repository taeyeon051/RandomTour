package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.RoomDAO;
import util.RandomCode;
import vo.RoomVO;

public class RoomCreateController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoomDAO dao = new RoomDAO();
		
		int roomId = Integer.parseInt(RandomCode.randomNumber(4));
		String title = request.getParameter("title");
		String userId = request.getParameter("user-id");
		
		if (dao.userRoomCheck(userId)) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "이미 접속중인 방이 있습니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		
		RoomVO vo = new RoomVO(roomId, title, userId, 1, false, false, "korea");
		int n = dao.createRoom(vo);
		
		if (n > 0) {
			request.setAttribute("state", "success");
			request.setAttribute("message", roomId);	
		} else {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "DB 오류로 인하여 방 생성에 실패하였습니다.");
		}
		
		return new MyView("/views/ajax/alert.jsp");
	}
}
