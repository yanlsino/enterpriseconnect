package org.osforce.connect.service.discussion.impl;

import org.osforce.connect.dao.discussion.TopicCategoryDao;
import org.osforce.connect.entity.discussion.TopicCategory;
import org.osforce.connect.service.discussion.TopicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 10:00:02 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class TopicCategoryServiceImpl implements TopicCategoryService {

	private TopicCategoryDao topicCategoryDao;
	
	public TopicCategoryServiceImpl() {
	}
	
	@Autowired
	public void setTopicCategoryDao(TopicCategoryDao topicCategoryDao) {
		this.topicCategoryDao = topicCategoryDao;
	}

	public TopicCategory getTopicCategory(Long categoryId) {
		return topicCategoryDao.get(categoryId);
	}

	public void createTopicCategory(TopicCategory category) {
		updateTopicCategory(category);
	}

	public void updateTopicCategory(TopicCategory category) {
		if(category.getId()==null) { 
			topicCategoryDao.save(category);
		} else {
			topicCategoryDao.update(category);
		}
	}

	public void deleteTopicCategory(Long categoryId) {
		topicCategoryDao.delete(categoryId);
	}
}
