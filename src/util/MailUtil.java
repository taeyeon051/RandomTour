package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
	public static boolean sendMail(String mail, String content) {
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
			
			InternetAddress from = new InternetAddress("RandomTour<randomtour@naver.com>");
			msg.setFrom(from);
			
			InternetAddress to = new InternetAddress(mail);
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setHeader("content-Type", "text/html");
			msg.setSubject("이메일 인증하기", "UTF-8");
			msg.setText(content, "UTF-8");
			
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
