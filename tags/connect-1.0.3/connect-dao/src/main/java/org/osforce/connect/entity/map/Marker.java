package org.osforce.connect.entity.map;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:18:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="markers")
@Cacheable
public class Marker extends IdEntity {
	private static final long serialVersionUID = 3814360453356353792L;

	private String latitude;
	private String longitude;
	private String description;
	private Boolean draggable = false;
	private Boolean enabled = true;
	// helper
	private Long mapId;
	// refer
	private Map map;
	private Attachment icon;
	
	public Marker() {
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getDraggable() {
		return draggable;
	}
	
	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public Long getMapId() {
		if(mapId==null && map!=null) {
			mapId = map.getId();
		}
		return mapId;
	}
	
	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="map_id")
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="icon_id")
	public Attachment getIcon() {
		return icon;
	}
	
	public void setIcon(Attachment icon) {
		this.icon = icon;
	}
	
}
