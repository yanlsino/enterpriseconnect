package org.osforce.e2.web.module.discussion.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.discussion.Reply;
import org.osforce.e2.service.discussion.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 16, 2011 - 3:36:50 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/discussion")
public class ReplyController {

	private ReplyService replyService;
	
	public ReplyController() {
	}
	
	@Autowired
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Reply reply) {
		if(reply.getId()==null) {
			replyService.createReply(reply);
		} else {
			replyService.updateReply(reply);
		}
		return Collections.singletonMap("id", reply.getId());
	}
}
