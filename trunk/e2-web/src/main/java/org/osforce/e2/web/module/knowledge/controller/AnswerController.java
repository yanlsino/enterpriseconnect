package org.osforce.e2.web.module.knowledge.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.knowledge.Answer;
import org.osforce.e2.service.knowledge.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:19:40 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/knowledge")
public class AnswerController {

	private AnswerService answerService;
	
	public AnswerController() {
	}
	
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	@RequestMapping(value="/answer", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Answer answer) {
		if(answer.getId()==null) {
			answerService.createAnswer(answer);
		} else {
			answerService.updateAnswer(answer);
		}
		return Collections.singletonMap("id", answer.getId());
	}
}
