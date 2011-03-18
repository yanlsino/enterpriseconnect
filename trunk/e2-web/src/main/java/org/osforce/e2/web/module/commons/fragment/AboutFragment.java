package org.osforce.e2.web.module.commons.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:34:44 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AboutFragment {

	public String doAboutView() {
		return "commons/about";
	}
}
