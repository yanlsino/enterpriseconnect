package org.osforce.connect.web.module.system.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:19:03 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class SystemFragment {
	
	public SystemFragment() {
	}
	
	public String doWelcomeView() {
		return "system/welcome";
	}
	
	public String doActionsView() {
		return "system/actions";
	}
	
}
