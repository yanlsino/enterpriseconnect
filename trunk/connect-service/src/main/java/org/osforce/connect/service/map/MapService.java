package org.osforce.connect.service.map;

import org.osforce.connect.entity.map.Map;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:49:13 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface MapService {

	Map getMap(Long mapId);
	
	void createMap(Map map);
	
	void updateMap(Map map);
	
	void deleteMap(Long mapId);
}
