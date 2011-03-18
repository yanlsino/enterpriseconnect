package org.osforce.e2.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.LinkService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 3:47:54 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class EntityTag extends TagSupport {
	private static final long serialVersionUID = -354960572917425149L;

	private String name;
	private String exist;
	private User user;
	private Project project;
	
	
	public EntityTag() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getExist() {
		return exist;
	}
	
	public void setExist(String exist) {
		this.exist = exist;
	}
	
	@Override
	public int doStartTag() throws JspException {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(pageContext.getServletContext());
		Object entity = null;
		if(StringUtils.equals(Profile.NAME, name)) {
			LinkService linkService = applicationContext.getBean(LinkService.class);
			if(project!=null && user!=null) {
				entity = linkService.getLink(user.getProjectId(), project.getProfileId(), name);
			} else {
				return SKIP_BODY;
			}
		}
		boolean flag = false;
		if(StringUtils.equals("true", exist) || StringUtils.equals("yes", exist)) {
			flag = entity!=null;
		} else {
			flag = entity==null;
		}
		if(flag) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
	
}
