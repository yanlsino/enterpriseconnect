package org.osforce.e2.service.discussion;

import org.osforce.e2.entity.discussion.Reply;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:50:58 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ReplyService {

	Reply getReply(Long replyId);
	
	void createReply(Reply reply);
	
	void updateReply(Reply reply);
	
	void deleteReply(Long replyId);

	Page<Reply> getReplyPage(Page<Reply> page, Long topicId);
}
