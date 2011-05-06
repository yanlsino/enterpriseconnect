package org.osforce.connect.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.system.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 1:52:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class SiteController {

	private SiteService siteService;
	
	public SiteController() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}
	
	@RequestMapping(value="/site", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Site site) {
		if(site.getId()==null) {
			siteService.createSite(site);
		} else {
			siteService.updateSite(site);
		}
		return Collections.singletonMap("id", site.getId());
	}
	
	@RequestMapping(value="/site/backup", method=RequestMethod.GET)
	public String backup(@RequestParam Long siteId) {
		Site site = siteService.getSite(siteId);
		siteService.backupSite(site);
		return "redirect:/system/sites";
	}
	
	@RequestMapping(value="/site/restore", method=RequestMethod.GET)
	public String restore(@RequestParam Long siteId) {
		Site site = siteService.getSite(siteId);
		siteService.restoreSite(site);
		return "redirect:/system/sites";
	}
}
