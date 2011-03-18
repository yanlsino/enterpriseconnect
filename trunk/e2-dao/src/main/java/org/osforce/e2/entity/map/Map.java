package org.osforce.e2.entity.map;

import java.util.Collections;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.e2.entity.system.Project;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 17, 2011 - 3:17:40 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="maps")
@Cacheable
public class Map extends IdEntity {
	private static final long serialVersionUID = -2935065337706228081L;

	private String latitude;
	private String longitude;
	private Integer depth;
	private Boolean scrollable = true;
	private Boolean showSearch = false;
	private Boolean showStreet = false;
	private Boolean showTraffic = false;
	// helper
	private Long projectId;
	// refer
	private Project project;
	private List<Marker> markers = Collections.emptyList();
	
	public Map() {
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

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Boolean getScrollable() {
		return scrollable;
	}

	public void setScrollable(Boolean scrollable) {
		this.scrollable = scrollable;
	}

	public Boolean getShowSearch() {
		return showSearch;
	}

	public void setShowSearch(Boolean showSearch) {
		this.showSearch = showSearch;
	}

	public Boolean getShowStreet() {
		return showStreet;
	}

	public void setShowStreet(Boolean showStreet) {
		this.showStreet = showStreet;
	}

	public Boolean getShowTraffic() {
		return showTraffic;
	}

	public void setShowTraffic(Boolean showTraffic) {
		this.showTraffic = showTraffic;
	}

	@Transient
	public Long getProjectId() {
		if(projectId==null && project!=null) {
			projectId = project.getId();
		}
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="map")
	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}
	
}
