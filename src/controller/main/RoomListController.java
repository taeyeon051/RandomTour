package controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.MyView;
import vo.UserVO;

public class RoomListController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("randomtour-user");
		if (user == null) return new MyView("/user/login", true);
		
		if (user.getnowRoom() != null) return new MyView("/room?id=" + user.getnowRoom(), true);
		
		return new MyView("/views/roomList.jsp");
	}
}
