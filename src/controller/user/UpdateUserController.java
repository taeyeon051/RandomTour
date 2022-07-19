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

public class UpdateUserController implements Controller {
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("GET")) {
			return new MyView("/main/mypage", true);
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
			request.setAttribute("state", "danger");
			request.setAttribute("message", "아이디는 이메일 형식이어야 합니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		if (!certifyNumber.equals(dao.certify(userId))) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "인증번호를 다시 확인해주세요.");
			return new MyView("/views/ajax/alert.jsp");
		}
		if (!userName.matches(regexList.getUserName())) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "이름은 한글, 영문만 사용할 수 있으며 2글자 이상이어야 합니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		if (!nickname.matches(regexList.getNickname())) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "닉네임은 한글, 영문, 숫자만 사용할 수 있으며 2~16글자여야 합니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		if (!password.matches(regexList.getPassword())) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "비밀번호는 영문 대소문자, 숫자, 기호를 포함하며 10글자 이상이어야 합니다.");
			return new MyView("/views/ajax/alert.jsp");
		}
		if (!password.equals(passwordCheck)) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "비밀번호와 확인이 일치하지 않습니다.");
			return new MyView("/views/ajax/alert.jsp");
		}

		boolean nicknameCheck = dao.nicknameCheck(nickname, userId);
		if (nicknameCheck) {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "이미 사용 중인 닉네임입니다.");
			return new MyView("/views/ajax/alert.jsp");
		}

		UserVO vo = new UserVO(userId, userName, nickname, password, true, 0);
		int n = dao.userUpdate(vo);
		if (n > 0) {
			dao.deleteCertify(userId);
			request.setAttribute("state", "success");
			request.setAttribute("message", "회원 정보가 수정되었습니다.");
		} else {
			request.setAttribute("state", "danger");
			request.setAttribute("message", "잘못된 정보이거나 DB 오류로 인하여 회원 정보 수정에 실패하였습니다. 잠시 후 다시 시도해주세요.");
		}
		return new MyView("/views/ajax/alert.jsp");
	}
}
