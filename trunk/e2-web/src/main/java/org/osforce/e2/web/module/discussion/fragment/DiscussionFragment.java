package org.osforce.e2.web.module.discussion.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 15, 2011 - 7:09:38 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class DiscussionFragment {

	public DiscussionFragment() {
	}
	
	public String doActionsView() {
		return "discussion/actions";
	}
}
