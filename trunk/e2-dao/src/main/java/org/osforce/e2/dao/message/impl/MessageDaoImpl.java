package org.osforce.e2.dao.message.impl;

import org.osforce.e2.dao.message.MessageDao;
import org.osforce.e2.entity.message.Message;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:23:34 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MessageDaoImpl extends AbstractDao<Message>
	implements MessageDao {

	public MessageDaoImpl() {
		super(Message.class);
	}
}
