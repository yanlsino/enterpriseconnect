package org.osforce.e2.web.module.knowledge.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.knowledge.Question;
import org.osforce.e2.service.knowledge.QuestionService;
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
	public @ResponseBody Map<String, Long> update(Question question) {
		if(question.getId()==null) {
			questionService.createQuestion(question);
		} else {
			questionService.updateQuestion(question);
		}
		return Collections.singletonMap("id", question.getId());
	}
}
