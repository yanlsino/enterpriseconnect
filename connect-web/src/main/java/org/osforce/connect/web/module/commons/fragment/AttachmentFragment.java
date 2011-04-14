package org.osforce.connect.web.module.commons.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 20, 2011 - 10:12:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AttachmentFragment {

	public AttachmentFragment() {
	}
	
	public String doFormView() {
		return "commons/attachment_form";
	}
}
