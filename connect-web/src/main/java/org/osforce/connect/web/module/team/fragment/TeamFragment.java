package org.osforce.connect.web.module.team.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 10:15:06 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class TeamFragment {

	public TeamFragment() {
	}
	
	public String doActionsView() {
		return "team/actions";
	}
}
