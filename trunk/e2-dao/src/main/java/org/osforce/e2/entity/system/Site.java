package org.osforce.e2.entity.system;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 3:41:16 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="sites")
@Cacheable
public class Site extends IdEntity {
	private static final long serialVersionUID = 2417731008556633617L;
	
	private String title;
	private String description;
	private String keywords;
	private String domain;
	private String contextPath;
	private Integer port;
	private Boolean ssl = false;
	private Boolean enabled = false;
	// helper
	private Long themeId;
	private Long mailSettingsId;
	// refer
	private Theme theme;
	private MailSettings mailSettings;
	
	public Site() {
	}

	public Site(String title, String description, String keywords,
			String domain, Boolean enabled) {
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.domain = domain;
		this.enabled = enabled;
	}

	@Column(nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length=500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(unique=true, nullable=false)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getContextPath() {
		return contextPath;
	}
	
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Column(name="ssl")
	public Boolean getSsl() {
		return ssl;
	}

	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public String getHomeURL() {
		String protocol = "http://";
		if(ssl) {
			protocol = "https://";
		}
		StringBuffer buffer = new StringBuffer(protocol);
		buffer.append(domain);
		if(port!=null) {
			buffer.append(":").append(port);
		}
		if(StringUtils.isNotBlank(contextPath)) {
			if(contextPath.startsWith("/")) {
				buffer.append(contextPath);
			} else {
				buffer.append("/").append(contextPath);
			}
		}
		return buffer.toString();
	}
	
	@Transient
	public Long getThemeId() {
		return themeId;
	}
	
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	
	@Transient
	public Long getMailSettingsId() {
		return mailSettingsId;
	}
	
	public void setMailSettingsId(Long mailSettingsId) {
		this.mailSettingsId = mailSettingsId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="theme_id")
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mail_settings_id")
	public MailSettings getMailSettings() {
		return mailSettings;
	}
	
	public void setMailSettings(MailSettings mailSettings) {
		this.mailSettings = mailSettings;
	}
	
}
