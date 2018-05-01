package com.tyrantqiao.pojo;

/**
 * date: 2018/5/1
 * Description: email
 * blog https://tyrantqiao.github.io/Blog
 *
 * @author tyrantqiao
 * @version 0.0.1
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
public class Email {
	private String toEmail;
	private String fromEmail;
	private String emailSubject;
	private String emailContent;

	public Email(String toEmail, String fromEmail, String emailSubject, String emailContent) {
		this.toEmail = toEmail;
		this.fromEmail = fromEmail;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
	}

	public Email(String toEmail, String emailSubject, String emailContent) {
		this(toEmail, "tyrantqiao@gmail.com", emailSubject, emailContent);
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
}
