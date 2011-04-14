package org.osforce.connect.web.module.commons.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osforce.connect.entity.commons.Comment;
import org.osforce.connect.service.commons.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocpsoft.pretty.time.PrettyTime;

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
	public @ResponseBody List<Map<String, Object>> list(
			@RequestParam Long linkedId, @RequestParam String entity) {
		List<Comment> comments = commentService.getCommentList(linkedId, entity);
		List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
		for(Comment comment : comments) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("id", comment.getId());
			model.put("linkedId", comment.getLinkedId());
			model.put("content", comment.getContent());
			model.put("entered_pretty", new PrettyTime().format(comment.getEntered()));
			model.put("enteredBy_project_profile_logo_id", comment.getEnteredBy().getProject().getProfile().getLogoId());
			model.put("enteredBy_project_category_code", comment.getEnteredBy().getProject().getCategory().getCode());
			model.put("enteredBy_project_uniqueId", comment.getEnteredBy().getProject().getUniqueId());
			commentList.add(model);
		}
		return commentList;
	}
	
}