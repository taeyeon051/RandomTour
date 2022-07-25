package vo;

import java.sql.Date;

public class ChattingVO {
	private String sendUserId;
	private String acceptUserId;
	private String chatting;
	private String sendDate;
	private String acceptDate;

	public ChattingVO() {
		super();
	}

	public ChattingVO(String sendUserId, String acceptUserId, String chatting, String sendDate, String acceptDate) {
		super();
		this.sendUserId = sendUserId;
		this.acceptUserId = acceptUserId;
		this.chatting = chatting;
		this.sendDate = sendDate;
		this.acceptDate = acceptDate;
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

	public String getChatting() {
		return chatting;
	}

	public void setChatting(String chatting) {
		this.chatting = chatting;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
}
