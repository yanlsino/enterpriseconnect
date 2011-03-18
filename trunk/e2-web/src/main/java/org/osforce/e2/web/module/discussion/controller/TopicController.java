package org.osforce.e2.web.module.discussion.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.service.discussion.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 15, 2011 - 10:51:19 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
@RequestMapping("/discussion")
public class TopicController {

	private TopicService topicService;
	
	public TopicController() {
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@RequestMapping(value="/topic", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Topic topic) {
		if(topic.getId()==null) {
			topicService.createTopic(topic);
		} else {
			topicService.updateTopic(topic);
		}
		return Collections.singletonMap("id", topic.getId());
	}
}
