package org.osforce.e2.web.module.commons.fragment;

import java.util.List;

import org.osforce.e2.entity.commons.Comment;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.commons.CommentService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 15, 2011 - 2:05:31 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class CommentFragment {

	private CommentService commentService;

	public CommentFragment() {
	}

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public String doListView(@Param Long linkedId, @Pref String entity,
			User user, Project project, FragmentContext context) {
		Comment comment = new Comment();
		comment.setLinkedId(linkedId);
		comment.setEnteredBy(user);
		comment.setModifiedBy(user);
		comment.setEntity(entity);
		List<Comment> comments = commentService.getCommentList(linkedId, entity);
		context.putRequestData(AttributeKeys.COMMENT_KEY_READABLE, comment);
		context.putRequestData(AttributeKeys.COMMENT_LIST_KEY_READABLE, comments);
		return "commons/comments_list";
	}
}
