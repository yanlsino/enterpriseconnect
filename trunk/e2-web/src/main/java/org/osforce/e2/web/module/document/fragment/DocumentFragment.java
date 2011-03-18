package org.osforce.e2.web.module.document.fragment;

import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 8:30:29 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class DocumentFragment {

	public DocumentFragment() {
	}
	
	
	public String doActionsView(FragmentContext context) {
		return "document/actions";
	}
}
