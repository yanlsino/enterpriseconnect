package org.osforce.connect.web.module.knowledge.controller;

import java.util.HashMap;
import java.util.Map;

import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.service.knowledge.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:18:49 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/knowledge")
public class QuestionController {

	private QuestionService questionService;
	
	public QuestionController() {
	}
	
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@RequestMapping(value="/question", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(Question question) {
		if(question.getId()==null) {
			questionService.createQuestion(question);
		} else {
			questionService.updateQuestion(question);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", question.getId());
		model.put("title", question.getTitle());
		return model;
	}
}
