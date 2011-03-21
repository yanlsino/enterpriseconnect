package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.system.SiteLink;
import org.osforce.e2.service.system.SiteLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 21, 2011 - 11:21:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class SiteLinkController {

	private SiteLinkService siteLinkService;
	
	public SiteLinkController() {
	}
	
	@Autowired
	public void setSiteLinkService(SiteLinkService siteLinkService) {
		this.siteLinkService = siteLinkService;
	}
	
	@RequestMapping(value="site_link", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(SiteLink siteLink) {
		if(siteLink.getId()==null) {
			siteLinkService.createSiteLink(siteLink);
		} else {
			siteLinkService.updateSiteLink(siteLink);
		}
		return Collections.singletonMap("id", siteLink.getId());
	}
}
