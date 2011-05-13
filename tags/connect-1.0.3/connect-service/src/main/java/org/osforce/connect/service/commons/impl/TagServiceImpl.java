package org.osforce.connect.service.commons.impl;

import java.util.List;

import org.osforce.connect.dao.commons.TagDao;
import org.osforce.connect.entity.commons.Tag;
import org.osforce.connect.service.commons.TagService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:19:15 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

	private TagDao tagDao;
	
	public TagServiceImpl() {
	}
	
	@Autowired
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}
	
	public Tag getTag(Long tagId) {
		return tagDao.get(tagId);
	}
	
	public Tag getTag(String name, Long linkedId, String entity) {
		QueryAppender appender = new QueryAppender()
				.equal("tag.name", name)
				.equal("tag.linkedId", linkedId)
				.equal("tag.entity", entity);
		return tagDao.findUnique(appender);
	}

	public void createTag(Tag tag) {
		updateTag(tag);
	}

	public void updateTag(Tag tag) {
		if(tag.getId()==null) {
			tagDao.save(tag);
		} else {
			tagDao.update(tag);
		}
	}

	public void deleteTag(Long tagId) {
		tagDao.delete(tagId);
	}
	
	public void deleteTag(String name, Long linkedId, String entity) {
		Tag tag = getTag(name, linkedId, entity);
		tagDao.delete(tag.getId());
	}
	
	public List<Tag> getTagList(Long linkedId, String entity) {
		QueryAppender appender = new QueryAppender()
				.equal("tag.linkedId", linkedId)
				.equal("tag.entity", entity);
		return tagDao.find(appender);
	}
	
	public Page<Tag> getTagPage(Page<Tag> page) {
		return tagDao.findPage(page, new QueryAppender());
	}
}
