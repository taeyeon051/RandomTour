package vo;

public class FriendVO {
	private String sendNickname;
	private String acceptNickname;
	private boolean state;

	public FriendVO() {
		super();
	}

	public FriendVO(String sendNickname, String acceptNickname, boolean state) {
		super();
		this.sendNickname = sendNickname;
		this.acceptNickname = acceptNickname;
		this.state = state;
	}

	public String getSendNickname() {
		return sendNickname;
	}

	public void setSendNickname(String sendNickname) {
		this.sendNickname = sendNickname;
	}

	public String getAcceptNickname() {
		return acceptNickname;
	}

	public void setAcceptNickname(String acceptNickname) {
		this.acceptNickname = acceptNickname;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
