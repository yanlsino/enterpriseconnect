package org.osforce.connect.task.support;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create May 5, 2011 - 4:01:31 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public abstract class AbstractEmailTask extends AbstractTask {

	private JavaMailSender mailSender;

	public AbstractEmailTask() {
	}

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	protected void doTask(final Map<Object, Object> context) {
		final String from = ((JavaMailSenderImpl)mailSender).getUsername();
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
				helper.setFrom(from);
				prepareMessage(helper, context);
			}
		};
		mailSender.send(preparator);
	}

	protected abstract void prepareMessage(MimeMessageHelper helper, Map<Object, Object> context) throws Exception;
}
