package org.osforce.connect.service.map;

import org.osforce.connect.entity.map.Marker;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:48:51 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MarkerService {

	Marker getMarker(Long markerId);
	
	void createMarker(Marker marker);
	
	void updateMarker(Marker marker);
	
	void deleteMarker(Long markerId);
}
