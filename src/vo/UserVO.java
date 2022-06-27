package vo;

public class UserVO {
	private String userId;
	private String userName;
	private String nickname;
	private String password;
	private boolean loginCheck;
	private int nowRoom;

	public UserVO() {
		super();
	}

	public UserVO(String userId, String userName, String nickname, String password, boolean loginCheck, int nowRoom) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickname = nickname;
		this.password = password;
		this.loginCheck = loginCheck;
		this.nowRoom = nowRoom;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public boolean isloginCheck() {
		return loginCheck;
	}

	public void setloginCheck(boolean loginCheck) {
		this.loginCheck = loginCheck;
	}

	public int getnowRoom() {
		return nowRoom;
	}

	public void setnowRoom(int nowRoom) {
		this.nowRoom = nowRoom;
	}
}
