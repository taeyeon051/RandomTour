package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
	public static boolean sendMail(String userId, String title, String select, String content) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.naver.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			
			Authenticator auth = new MailData();
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			
			boolean isCertify = select.equals("certify");
			userId = isCertify ? userId : "";
			title = isCertify ? title : title + " (" + select + ") <" + userId + ">";
			
			InternetAddress from = new InternetAddress("RandomTour<randomtour@naver.com>");
			msg.setFrom(from);
			
			InternetAddress to = new InternetAddress(userId);
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject(title, "UTF-8");
			msg.setText(content, "UTF-8", "html");
			
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

class MailData extends Authenticator {
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
