package org.osforce.connect.service.commons;

import java.util.List;

import org.osforce.connect.entity.commons.Tag;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:18:09 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface TagService {

	Tag getTag(Long tagId);
	
	Tag getTag(String name, Long linkedId, String entity);
	
	void createTag(Tag tag);
	
	void updateTag(Tag tag);
	
	void deleteTag(Long tagId);
	
	void deleteTag(String name, Long linkdedId, String entity);
	
	List<Tag> getTagList(Long linkedId, String entity);

	Page<Tag> getTagPage(Page<Tag> page);
}
