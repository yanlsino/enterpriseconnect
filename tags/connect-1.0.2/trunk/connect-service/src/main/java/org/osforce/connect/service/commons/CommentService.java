package org.osforce.connect.service.commons;

import java.util.List;

import org.osforce.connect.entity.commons.Comment;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 3:15:45 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface CommentService {

	Comment getComment(Long commentId);
	
	void createComment(Comment comment);
	
	void updateComment(Comment comment);
	
	void deleteComment(Long commentId);

	List<Comment> getCommentList(Long linkedId, String entity);

	Long countComment(Long linkedId, String entity);
}
