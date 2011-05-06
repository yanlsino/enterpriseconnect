package org.osforce.connect.web.module.commons.fragment;

import org.osforce.connect.entity.system.User;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 3:35:36 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ActionFragment {

	public ActionFragment() {
	}
	
	public String doActionsView(User user) {
		return "commons/actions";
	}
}
