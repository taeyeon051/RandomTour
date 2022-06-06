package vo;

public class UserVO {
	private String userId;
	private String nickname;
	private String password;
	private boolean login_check;
	private int now_room;

	public UserVO() {
		super();
	}

	public UserVO(String userId, String nickname, String password, boolean login_check, int now_room) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.password = password;
		this.login_check = login_check;
		this.now_room = now_room;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogin_check() {
		return login_check;
	}

	public void setLogin_check(boolean login_check) {
		this.login_check = login_check;
	}

	public int getNow_room() {
		return now_room;
	}

	public void setNow_room(int now_room) {
		this.now_room = now_room;
	}
}
