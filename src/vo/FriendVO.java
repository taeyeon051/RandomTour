package vo;

public class FriendVO {
	private String sendUserId;
	private String acceptUserId;
	private boolean state;

	public FriendVO() {
		super();
	}

	public FriendVO(String sendUserId, String acceptUserId, boolean state) {
		super();
		this.sendUserId = sendUserId;
		this.acceptUserId = acceptUserId;
		this.state = state;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getAcceptUserId() {
		return acceptUserId;
	}

	public void setAcceptUserId(String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
