package org.osforce.e2.web.module.gallery.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:10:54 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class GalleryFragment {

	public GalleryFragment() {
	}
	
	public String doActionsView() {
		return "gallery/actions";
	}
}
