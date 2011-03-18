package org.osforce.e2.web.module.blog.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 13, 2011 - 10:31:50 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class BlogFragment {

	public BlogFragment() {
	}
	
	public String doActionsView() {
		return "blog/actions";
	}
}
