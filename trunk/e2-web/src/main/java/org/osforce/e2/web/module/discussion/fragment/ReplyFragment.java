package org.osforce.e2.web.module.discussion.fragment;

import org.osforce.e2.entity.discussion.Reply;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.discussion.ReplyService;
import org.osforce.e2.service.discussion.TopicService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 16, 2011 - 2:21:26 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ReplyFragment {

	private TopicService topicService;
	private ReplyService replyService;
	
	public ReplyFragment() {
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	public String doListView(@Param Long topicId, 
			Page<Reply> page, FragmentContext context) {
		Topic topic = topicService.viewTopic(topicId);
		page = replyService.getReplyPage(page, topicId);
		context.putRequestData(AttributeKeys.TOPIC_KEY_READABLE, topic);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "discussion/replies_list";
	}
	
	public String doFormView(@Param Long replyId, @Param Long topicId,
			User user, FragmentContext context) {
		Topic topic = topicService.getTopic(topicId);
		Reply reply = new Reply();
		reply.setEnteredBy(user);
		reply.setModifiedBy(user);
		reply.setTopic(topic);
		if(replyId!=null) {
			reply = replyService.getReply(replyId);
		}
		context.putRequestData(AttributeKeys.REPLY_KEY_READABLE, reply);
		return "discussion/reply_form";
	}
}
