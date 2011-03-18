package org.osforce.e2.service.message;

import org.osforce.e2.entity.message.Message;
import org.osforce.e2.entity.system.Project;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:21:13 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MessageService {

	Message getMessage(Long messageId);
	
	void createMessage(Message message);
	
	void updateMessage(Message message);
	
	void deleteMessage(Long messageId);

	Page<Message> getMessagePage(Page<Message> page, 
			Project project, String box);

	Long countUnreadMessage(Project project);
}
