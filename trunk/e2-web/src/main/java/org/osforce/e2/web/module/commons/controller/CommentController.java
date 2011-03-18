package org.osforce.e2.web.module.commons.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osforce.e2.entity.commons.Comment;
import org.osforce.e2.service.commons.CommentService;
import org.osforce.e2.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 9:31:44 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/commons")
public class CommentController {

	private CommentService commentService;
	
	public CommentController() {
	}
	
	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(Comment comment) {
		if(comment.getId()==null) {
			commentService.createComment(comment);
		} else {
			commentService.updateComment(comment);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", comment.getId());
		model.put("linkedId", comment.getLinkedId());
		model.put("content", comment.getContent());
		return model;
	}
	
	@RequestMapping(value="/comments", method=RequestMethod.GET)
	public String list(@RequestParam Long linkedId, 
			@RequestParam String entity, Model model) {
		List<Comment> commentList = commentService.getCommentList(linkedId, entity);
		model.addAttribute(AttributeKeys.COMMENT_LIST_KEY_READABLE, commentList);
		return "commons/comments_list_ajax";
	}
	
}