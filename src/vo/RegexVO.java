package vo;

public class RegexVO {
	private String userId = "[0-9a-zA-Z]([-_]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}";
	private String userName = "[가-힣a-zA-Z]{2,}";
	private String nickname = "[A-Za-z0-9가-힣]{2,16}";
	private String password = "(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*().])[A-Za-z\\d!@#$%^&*().]{10,}";

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}
}
