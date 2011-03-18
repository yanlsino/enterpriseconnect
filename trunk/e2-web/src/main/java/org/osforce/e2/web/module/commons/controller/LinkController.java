package org.osforce.e2.web.module.commons.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.commons.Link;
import org.osforce.e2.service.commons.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:19:15 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/commons")
public class LinkController {

	private LinkService linkService;
	
	public LinkController() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	@RequestMapping(value="/link")
	public @ResponseBody Map<String, Long> update(Link link) {
		// enabled this link
		link.setEnabled(true);
		if(link.getId()==null) {
			linkService.createLink(link);
		} else {
			linkService.updateLink(link);
		}
		return Collections.singletonMap("id", link.getId());
	}
	
}
