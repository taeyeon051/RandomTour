package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.MyView;
import dao.UserDAO;
import vo.RegexVO;
import vo.UserVO;

public class JoinController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/views/join.jsp");
		}

		UserDAO dao = new UserDAO();
		RegexVO regexList = new RegexVO();
		String userId = request.getParameter("user-id");
		String certifyNumber = request.getParameter("certify-number");
		String userName = request.getParameter("user-name");
		String nickname = request.getParameter("user-nickname");
		String password = request.getParameter("user-pwd");
		String passwordCheck = request.getParameter("user-pwdc");
		
		if (!userId.matches(regexList.getUserId())) {
			request.setAttribute("alert", "아이디는 이메일 형식이어야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!certifyNumber.equals(dao.certify(userId))) {
			request.setAttribute("alert", "인증번호를 다시 확인해주세요.");
			return new MyView("/views/join.jsp");
		}
		if (!userName.matches(regexList.getUserName())) {
			request.setAttribute("alert", "이름은 한글, 영문만 사용할 수 있으며 2글자 이상이어야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!nickname.matches(regexList.getNickname())) {
			request.setAttribute("alert", "닉네임은 한글, 영문, 숫자만 사용할 수 있으며 2~16글자여야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!password.matches(regexList.getPassword())) {
			request.setAttribute("alert", "비밀번호는 영문 대소문자, 숫자, 기호를 포함하며 10글자 이상이어야 합니다.");
			return new MyView("/views/join.jsp");
		}
		if (!password.equals(passwordCheck)) {
			request.setAttribute("alert", "비밀번호와 확인이 일치하지 않습니다.");
			return new MyView("/views/join.jsp");
		}

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

		UserVO vo = new UserVO(userId, userName, nickname, password, false, 0);
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