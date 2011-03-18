package org.osforce.e2.service.discussion;

import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.entity.system.Project;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:50:55 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TopicService {

	Topic viewTopic(Long topicId);
	
	Topic getTopic(Long topicId);
	
	void createTopic(Topic topic);
	
	void updateTopic(Topic topic);
	
	void deleteTopic(Long topicId);

	Page<Topic> getTopicPage(Page<Topic> page);
	
	Page<Topic> getTopicPage(Page<Topic> page, Long forumId);

	Page<Topic> getTopicPage(Page<Topic> page, Project project);

}
