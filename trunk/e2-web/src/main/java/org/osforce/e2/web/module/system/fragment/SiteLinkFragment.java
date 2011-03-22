package org.osforce.e2.web.module.system.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.SiteLink;
import org.osforce.e2.service.system.SiteLinkService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 21, 2011 - 11:26:01 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SiteLinkFragment {

	private SiteLinkService siteLinkService;
	
	public SiteLinkFragment() {
	}
	
	@Autowired
	public void setSiteLinkService(SiteLinkService siteLinkService) {
		this.siteLinkService = siteLinkService;
	}
	
	public String doListView(@Param Long siteId, Site site,
			@Pref String[] categoryCodes,  FragmentContext context) {
		if(siteId==null) { siteId = site.getId(); }
		List<SiteLink> siteLinks = siteLinkService.getSiteLinkList(siteId);
		Map<String, List<SiteLink>> siteLinksMap = new HashMap<String, List<SiteLink>>();
		for(SiteLink siteLink : siteLinks) {
			List<SiteLink> tmp = siteLinksMap.get(siteLink.getCode());
			if(tmp==null) {
				tmp = new ArrayList<SiteLink>();
			}
			tmp.add(siteLink);
			siteLinksMap.put(siteLink.getCode(), tmp);
		}
		if(categoryCodes!=null) {
			context.putRequestData("categoryCodes", categoryCodes);
			context.putRequestData("siteLinksMap", siteLinksMap);
		} else {
			context.putRequestData(AttributeKeys.SITE_LINK_LIST_KEY_READABLE, siteLinks);
		}
		return "system/site_links_list";
	}
	
	public String doFormView(@Param Long linkId, 
			@Param Long siteId, FragmentContext context) {
		SiteLink siteLink = new SiteLink();
		siteLink.setSiteId(siteId);
		if(linkId!=null) {
			siteLink = siteLinkService.getSiteLink(linkId);
		}
		context.putRequestData(AttributeKeys.SITE_LINK_KEY_READABLE, siteLink);
		return "system/site_link_form";
	}
}
