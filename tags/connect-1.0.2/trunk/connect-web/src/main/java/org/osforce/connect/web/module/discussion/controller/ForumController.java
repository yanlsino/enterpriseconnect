package org.osforce.connect.web.module.discussion.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.service.discussion.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 15, 2011 - 8:28:45 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/discussion")
public class ForumController {

	private ForumService forumService;
	
	public ForumController() {
	}
	
	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@RequestMapping(value="/forum", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Forum forum) {
		if(forum.getId()==null) {
			forumService.createForum(forum);
		} else {
			forumService.updateForum(forum);
		}
		return Collections.singletonMap("id", forum.getId());
	}
}
