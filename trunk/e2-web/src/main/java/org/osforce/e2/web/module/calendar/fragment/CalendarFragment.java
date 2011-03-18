package org.osforce.e2.web.module.calendar.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 8, 2011 - 11:58:08 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class CalendarFragment {

	public CalendarFragment() {
	}
	
	public String doActionsView() {
		return "calendar/actions";
	}
}
