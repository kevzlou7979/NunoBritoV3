package freelance.nunobrito.server;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public MailSender(String to, String subject, String msgBody) throws UnsupportedEncodingException {
		sendMail(to, subject, msgBody);
	}
	
	private void sendMail(String to, String subject, String msgBody) throws UnsupportedEncodingException{
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("info@dotclick.com", "Dot Click Team"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, "Sir/Madam,"));
            msg.setSubject(subject);
            msg.setText(msgBody);
            Transport.send(msg);

        } catch (AddressException e) {
        	e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }	
	}
}
