package Servlet;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


public class b2_Mailer {
	public static void send(String from, String to, String subject, String body) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true"); 
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587"); 
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

		String username = "langochungse23@gmail.com";
		String password = "cajf dbqb ofzr mddu";
		Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		session.setDebug(true);

		try {
			MimeMessage mail = new MimeMessage(session);
			mail.setFrom(new InternetAddress(from));
			mail.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			mail.setSubject(subject, "utf-8");
			mail.setContent(body, "text/html; charset=UTF-8");
			mail.setReplyTo(mail.getFrom());

			Transport.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
