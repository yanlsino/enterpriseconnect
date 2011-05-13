package org.osforce.connect.task.discussion;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 3:12:47 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class DiscussionAspect {
	private static final String TEMPLATE_FORUM_UPDATE = "activity/forum_update.ftl";
	private static final String TEMPLATE_TOPIC_UPDATE = "activity/topic_update.ftl";
	private static final String TEMPLATE_REPLY_UPDATE = "activity/reply_update.ftl";

	private Task topicViewCountTask;
	private Task forumActivityStreamTask;
	private Task topicActivityStreamTask;
	private Task replyActivityStreamTask;
	private Task replyCreateEmailTask;

	public DiscussionAspect() {
	}

	@Autowired
	@Qualifier("topicViewCountTask")
	public void setTopicViewCountTask(Task topicViewCountTask) {
		this.topicViewCountTask = topicViewCountTask;
	}

	@Autowired
	@Qualifier("forumActivityStreamTask")
	public void setForumActivityStreamTask(Task forumActivityStreamTask) {
		this.forumActivityStreamTask = forumActivityStreamTask;
	}

	@Autowired
	@Qualifier("topicActivityStreamTask")
	public void setTopicActivityStreamTask(Task topicActivityStreamTask) {
		this.topicActivityStreamTask = topicActivityStreamTask;
	}

	@Autowired
	@Qualifier("replyActivityStreamTask")
	public void setReplyActivityStreamTask(Task replyActivityStreamTask) {
		this.replyActivityStreamTask = replyActivityStreamTask;
	}

	@Autowired
	@Qualifier("replyCreateEmailTask")
	public void setTopicReplyEmailTask(Task replyCreateEmailTask) {
		this.replyCreateEmailTask = replyCreateEmailTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.discussion.TopicService.viewTopic(..))")
	public void viewTopic(JoinPoint jp) {
		Long topicId = (Long) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("topicId", topicId);
		topicViewCountTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.discussion.ForumService.createForum(..)) ||"
			+ "execution(* org.osforce.connect.service.discussion.ForumService.updateForum(..))")
	public void updateForum(JoinPoint jp) {
		Forum forum = (Forum) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("forumId", forum.getId());
		context.put("template", TEMPLATE_FORUM_UPDATE);
		forumActivityStreamTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.discussion.TopicService.createTopic(..)) ||"
			+ "execution(* org.osforce.connect.service.discussion.TopicService.updateTopic(..))")
	public void updateTopic(JoinPoint jp) {
		Topic topic = (Topic) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("topicId", topic.getId());
		context.put("template", TEMPLATE_TOPIC_UPDATE);
		topicActivityStreamTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.discussion.ReplyService.createReply(..)) ||"
			+ "execution(* org.osforce.connect.service.discussion.ReplyService.updateReply(..))")
	public void updateReply(JoinPoint jp) {
		Reply reply = (Reply) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("replyId", reply.getId());
		context.put("template", TEMPLATE_REPLY_UPDATE);
		replyActivityStreamTask.doAsyncTask(context);
		//
		replyCreateEmailTask.doAsyncTask(context);
	}

}
