package playground.business;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Vector;
import java.io.*;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

public class Mailer {
    
    protected static Message prepareHeader(String smtp_host, String from,
					   String to, String subject) 
	throws IOException, AddressException,
	MessagingException {
	Properties props = new Properties();
	props.put("smtp.host", smtp_host);
	Session session = Session.getDefaultInstance(props, null);
	
	Message msg = new MimeMessage(session);
	
	InternetAddress addr = new InternetAddress(to);
	msg.addRecipients(Message.RecipientType.TO,
			  new InternetAddress[] {addr });
	
	InternetAddress from_addr = new InternetAddress(from);
	msg.setFrom(from_addr);
	
	msg.setSubject(subject);
	
	return msg;
    }
    
    public static void sendMail(String smtp_host, String from,
				String to, String subject, String message)
	throws IOException, AddressException,
	MessagingException {
	Message msg = prepareHeader(smtp_host, from, to, subject);
	msg.setContent(message, "text/plain");
	Transport.send(msg);
    }
    
    public static void sendWithAttachments(String smtp_host, String from,
					   String to, String subject, String message, Vector attach)
	throws IOException, AddressException,
	MessagingException {
	Message msg = prepareHeader(smtp_host, from, to, subject);
	
	MimeMultipart mp = new MimeMultipart();
	
	MimeBodyPart text = new MimeBodyPart();
	text.setDisposition(Part.INLINE);
	text.setContent(message, "text/plain");
	mp.addBodyPart(text);
	
	for (int i = 0; i < attach.size(); i++) {
	    MimeBodyPart file_part = new MimeBodyPart();
	    File file = (File) attach.elementAt(i);
	    FileDataSource fds = new FileDataSource(file);
	    DataHandler dh = new DataHandler(fds);
	    file_part.setFileName(file.getName());
	    file_part.setDisposition(Part.ATTACHMENT);
	    file_part.setDescription("Attached file: " + file.getName());
	    file_part.setDataHandler(dh);
	    mp.addBodyPart(file_part);
	}
	
	msg.setContent(mp);
	Transport.send(msg);
    }
    
} // Mailer
