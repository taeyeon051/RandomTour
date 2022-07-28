package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.RoomDAO;
import vo.RoomVO;

public class RoomController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoomDAO dao = new RoomDAO();
		
		String roomId = request.getParameter("id");
		if (roomId == null || roomId.equals("")) {
			request.setAttribute("alert", "존재하지 않는 방 번호입니다.");
			return new MyView("/views/roomList.jsp");
		}
		
		RoomVO vo = dao.getRoomData(roomId);
		request.setAttribute("room-data", vo);
		
		return new MyView("/views/room.jsp");
	}
}
