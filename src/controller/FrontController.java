package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.main.AcceptFriendController;
import controller.main.AddFriendController;
import controller.main.DeleteFriendController;
import controller.main.MyPageController;
import controller.main.RoomListController;
import controller.main.SendInquiryController;
import controller.user.FindUserController;
import controller.user.JoinController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.SendMailController;
import controller.user.UpdatePwdController;
import controller.user.UpdateUserController;

@WebServlet(urlPatterns = { "/user/*", "/main/*" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashMap<String, Controller> controllerMap = new HashMap<>();

    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// 로그인, 회원가입, 로그아웃
		controllerMap.put("/user/login", new LoginController());
		controllerMap.put("/user/join", new JoinController());
		controllerMap.put("/user/logout", new LogoutController());
		// 아이디, 비밀번호 찾기
		controllerMap.put("/user/find", new FindUserController());
		controllerMap.put("/user/updatePwd", new UpdatePwdController());
		// 이메일 인증번호 전송
		controllerMap.put("/user/sendMail", new SendMailController());
		// 메인페이지 (방목록 페이지)
		controllerMap.put("/main/roomList", new RoomListController());
		
		// 마이페이지
		controllerMap.put("/main/mypage", new MyPageController());
		controllerMap.put("/user/update", new UpdateUserController());
		controllerMap.put("/main/inquiry", new SendInquiryController());
		// 친구 추가
		controllerMap.put("/main/friend/add", new AddFriendController());
		// 받은 요청 수락
		controllerMap.put("/main/friend/accept", new AcceptFriendController());
		// 친구 삭제
		controllerMap.put("/main/friend/delete", new DeleteFriendController());
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		
		Controller controller = controllerMap.get(path);
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		MyView view = controller.process(request, response);
		view.render(request, response);
	}
}
