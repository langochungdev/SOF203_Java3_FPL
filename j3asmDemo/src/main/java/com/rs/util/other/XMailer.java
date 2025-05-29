package com.rs.util.other;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

import java.util.Properties;

public class XMailer {
    private static final String DEFAULT_HOST_EMAIL = "peterdamlinh1215@gmail.com";
    private static final String DEFAULT_HOST_PASSWORD = "ziys jute rzbx aixe";
    private static Properties props = new Properties();

    static {
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
    }
//	private static Session getSession() {
//		return Session.getDefaultInstance(props , new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(DEFAULT_HOST_USERNAME, DEFAULT_HOST_PASSWORD);
//			}
//		});
//	}

    private static Session getSession(String username, String password) {
        return Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * Gửi email
     *
     * @param mail đối tượng chứa dữ liệu email
     * @throws lỗi gửi mail
     */
    public static void send(MailData mail) throws MessagingException {
        Session session = XMailer.getSession(DEFAULT_HOST_EMAIL, DEFAULT_HOST_PASSWORD);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mail.getFrom()));
        message.setRecipients(RecipientType.TO, mail.getTo());
//			message.setRecipients(RecipientType.CC, mail.getCc());
//			message.setRecipients(RecipientType.BCC, mail.getBcc());
        message.setSubject(mail.getSubject(), "utf-8");
        message.setText(mail.getBody(), "utf-8", "html");
        message.setReplyTo(message.getFrom());
//			String filenames = mail.getFilenames();
//			if(filenames != null && filenames.trim().length() > 0) {
//				MimeMultipart attachs = new MimeMultipart();
//				for(String filename: filenames.split("[,;]+")) {
//					MimeBodyPart bodyPart = new MimeBodyPart();
//					try {
//						bodyPart.attachFile(filename.trim());
//						attachs.addBodyPart(bodyPart);
//					} catch (Exception e) {
//						System.out.println("Attach error: " + filename);
//					}
//				}
//				message.setContent(attachs);
//			}
        Transport.send(message);
    }

    /**
     * Gửi email đơn giản
     *
     * @param to      email người nhận
     * @param subject tiêu đề mail
     * @param body    nội dung mail
     * @throws lỗi gửi mail
     */
    public static void send(String from, String to, String subject, String body) throws MessagingException {
        XMailer.send(new MailData(from, to, subject, body));
    }

    public static void send(String to, String subject, String body) throws MessagingException {
            XMailer.send(new MailData(DEFAULT_HOST_EMAIL, to, subject, body));
    }

    public static class MailData {
        String from;
        String to;
        String cc;
        String bcc;
        String subject;
        String body;
        String filenames;
        /**
         * Tạo đối tượng với 3 thuộc tính đơn giản
         * @param to email người nhận
         * @param subject tiên đề mail
         * @param body nội dung email
         */
//		public MailData(String from, String to, String subject, String body) {
//			this(from, to, subject, body
//					, Map.of()
//					);
//		}

        /**
         * Tạo đối tượng với 3 thuộc tính đơn giản và các thuộc tính tùy chọn
         *
         * @param to      email người nhận
         * @param subject tiên đề mail
         * @param body    nội dung email
         * @param others  các thuộc tính bổ sung (from, cc, bcc, filenames)
         */
        public MailData(String from, String to, String subject, String body
//				, Map<String, String> others
        ) {
            this.from = from;
            this.to = to;
            this.subject = subject;
            this.body = body;
//			this.from = others.get("from");
//			this.cc = others.get("cc");
//			this.bcc = others.get("bcc");
//			this.filenames = others.get("filenames");
        }

        public MailData(String to, String subject, String body
//				, Map<String, String> others
        ) {
            this.to = to;
            this.subject = subject;
            this.body = body;
//			this.from = others.get("from");
//			this.cc = others.get("cc");
//			this.bcc = others.get("bcc");
//			this.filenames = others.get("filenames");
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public String getBcc() {
            return bcc;
        }

        public void setBcc(String bcc) {
            this.bcc = bcc;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getFilenames() {
            return filenames;
        }

        public void setFilenames(String filenames) {
            this.filenames = filenames;
        }
    }
}
