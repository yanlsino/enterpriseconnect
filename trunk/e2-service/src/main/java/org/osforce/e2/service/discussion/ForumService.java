package org.osforce.e2.service.discussion;

import java.util.List;

import org.osforce.e2.entity.discussion.Forum;
import org.osforce.e2.entity.system.Project;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:50:03 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ForumService {

	Forum getForum(Long forumId);
	
	void createForum(Forum forum);
	
	void updateForum(Forum forum);
	
	void deleteForum(Long forumId);

	List<Forum> getForumList(Project project);
}
