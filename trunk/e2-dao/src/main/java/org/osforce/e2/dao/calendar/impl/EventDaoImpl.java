package org.osforce.e2.dao.calendar.impl;

import org.osforce.e2.dao.calendar.EventDao;
import org.osforce.e2.entity.calendar.Event;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 11:04:06 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class EventDaoImpl extends AbstractDao<Event> 
		implements EventDao {
	
	public EventDaoImpl() {
		super(Event.class);
	}
}
