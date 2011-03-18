package org.osforce.e2.web.module.commons.fragment;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.commons.Link;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.service.commons.LinkService;
import org.osforce.e2.service.profile.ProfileService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 4, 2011 - 4:17:26 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class LinkFragment {

	private LinkService linkService;
	private ProfileService profileService;
	
	public LinkFragment() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	public String doListView(@Pref String entity, @Pref String mode, 
			Page<Link> page, Project project, FragmentContext context) {
		if(StringUtils.equals("link-to", mode)) {
			page = linkService.getLinkToPage(page, project.getProfileId(), entity);
		} else if(StringUtils.equals("link-from", mode)) {
			page = linkService.getLinkFromPage(page, project.getId(), entity);
		}
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		// fetch linked entity
		String view = null;
		for(Link link : page.getResult()) {
			Object linkedEntity = null;
			if(StringUtils.equals(Profile.NAME, entity)) {
				view = "profile/links_list";
				linkedEntity = profileService.getProfile(link.getToId());
			}
			link.setLinkedEntity(linkedEntity);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		context.putRequestData("mode", mode);
		return view;
	}
	
}
