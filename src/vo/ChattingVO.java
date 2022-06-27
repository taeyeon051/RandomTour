package vo;

public class ChattingVO {
	private String userId;
	private String nickname;
	private String message;

	public ChattingVO() {
		super();
	}

	public ChattingVO(String userId, String nickname, String message) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
