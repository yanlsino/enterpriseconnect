package org.osforce.connect.web.module.map.fragment;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:11:31 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class MapFragment {

	public MapFragment() {
	}
	
	public String doMapView() {
		return "map/map";
	}
	
	public String doFormView() {
		return "map/map_form";
	}
}
