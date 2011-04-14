package org.osforce.connect.task.support;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.osforce.connect.entity.system.MailSettings;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.system.SiteService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public abstract class AbstractEmailTask extends AbstractTask {

	static Map<Site, JavaMailSender> mailSenderCache = new HashMap<Site, JavaMailSender>();
	
	private SiteService siteService;
	
	public AbstractEmailTask() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}
	
	protected void doTask(final Map<Object, Object> context) {
		Long siteId = (Long) context.get("siteId");
		if(siteId==null) {
			throw new NullPointerException("siteId can not be null...");
		}
		final Site site = siteService.getSite(siteId);
		final MailSettings settings = site.getMailSettings();
		JavaMailSender mailSender = mailSenderCache.get(site);
		if(mailSender==null) {
			JavaMailSenderImpl impl = new JavaMailSenderImpl();
			impl.setHost(settings.getHost());
			impl.setPort(settings.getPort());
			impl.setUsername(settings.getUsername());
			impl.setPassword(settings.getPassword());
			mailSender = impl;
			mailSenderCache.put(site, mailSender);
		}
		//
		context.put("site", site);
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
				helper.setFrom(settings.getHost(), site.getTitle());
				prepareMessage(helper, context);
			}
		};
		mailSender.send(preparator);
	}

	protected abstract void prepareMessage(MimeMessageHelper helper, Map<Object, Object> context) throws Exception;
}
