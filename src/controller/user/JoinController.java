package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;
import vo.UserVO;

public class JoinController implements Controller {
	private String userIdRegex = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}";
    private String nicknameRegex = "[A-Za-z0-9가-힣]{2,16}";
    private String passwordRegex = "(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*().])[A-Za-z\\d!@#$%^&*().]{10,}";
	
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/views/join.jsp");
		}
		
		String userId = request.getParameter("user-id");
		String nickname = request.getParameter("user-nickname");
		String password = request.getParameter("user-pwd");
		String passwordCheck = request.getParameter("user-pwdc");
		
		if (!userId.matches(userIdRegex)) {
			request.setAttribute("alert", "아이디는 이메일 형식이어야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!nickname.matches(nicknameRegex)) {
			request.setAttribute("alert", "닉네임은 한글,영문,숫자만 사용 가능하며 2글자 이상 16글자 이하여야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!password.matches(passwordRegex)) {
			request.setAttribute("alert", "비밀번호는 영문 대소문자,숫자,기호를 포함하여햐 하며 10글자 이상이여야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!password.equals(passwordCheck)) {
			request.setAttribute("alert", "비밀번호와 확인이 일치하지 않습니다.");
			return new MyView("/views/join.jsp");
		}
		
		UserDAO dao = new UserDAO();
		boolean userIdCheck = dao.userIdCheck(userId);
		boolean nicknameCheck = dao.nicknameCheck(nickname);
		
		if (userIdCheck) {
			request.setAttribute("alert", "이미 사용중인 아이디입니다.");
			return new MyView("/views/join.jsp");			
		}
		if (nicknameCheck) {
			request.setAttribute("alert", "이미 사용중인 닉네임입니다.");
			return new MyView("/views/join.jsp");
		}
		
		UserVO vo = new UserVO(0, userId, nickname, password, false, 0);
		int n = dao.userJoin(vo);
		if (n > 0) {
			request.setAttribute("success", "회원가입이 완료되었습니다.");
			return new MyView("/views/login.jsp");
		} else {
			request.setAttribute("alert", "데이터베이스 오류로 인하여 회원가입에 실패하였습니다.");
			return new MyView("/views/join.jsp");
		}		
	}
}
