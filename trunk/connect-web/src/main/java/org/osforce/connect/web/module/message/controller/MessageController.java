package org.osforce.connect.web.module.message.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.message.Message;
import org.osforce.connect.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 16, 2011 - 6:59:32 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
public class MessageController {
    
	private MessageService messageService;
	
	public MessageController() {
	}
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping(value="/message/message")
	public @ResponseBody Map<String, Long> update(Message message) {
		if(message.getId()==null) {
			messageService.createMessage(message);
		} else {
			messageService.updateMessage(message);
		}
		return Collections.singletonMap("id", message.getId());
	}
}
