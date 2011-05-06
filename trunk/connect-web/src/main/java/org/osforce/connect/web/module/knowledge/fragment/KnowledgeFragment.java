package org.osforce.connect.web.module.knowledge.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:16:37 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class KnowledgeFragment {

	public KnowledgeFragment() {
	}
	
	public String doActionsView() {
		return "knowledge/actions";
	}
}
