package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailData extends Authenticator {
	private String id = "";
	private String pw = "";
	PasswordAuthentication account;
		
	public MailData() {
		account = new PasswordAuthentication(id, pw);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return account;
	}
}
