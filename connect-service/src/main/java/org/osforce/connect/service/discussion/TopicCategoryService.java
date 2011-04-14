package org.osforce.connect.service.discussion;

import org.osforce.connect.entity.discussion.TopicCategory;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:51:15 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TopicCategoryService {

	TopicCategory getTopicCategory(Long categoryId);
	
	void createTopicCategory(TopicCategory category);
	
	void updateTopicCategory(TopicCategory category);
	
	void deleteTopicCategory(Long categoryId);
}
