package org.osforce.connect.web.security.util;

import org.apache.commons.lang.math.NumberUtils;
import org.osforce.connect.entity.system.Permission;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 11:55:47 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public abstract class PermissionUtil {

	public static boolean hasPermission(Permission permission, Project project,
			User user, TeamMember member) {
		if(permission!=null &&
				NumberUtils.compare(permission.getRole().getLevel(), 100)>=0) {
			return true;
		}
		if(project!=null && user!=null && (
				NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
				NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0)) {
			return true;
		}
		if(permission!=null && member!=null && member.getEnabled() &&
				NumberUtils.compare(permission.getRole().getLevel(), member.getRole().getLevel())>0) {
			return true;
		}
		return false;
	}

	public static boolean hasPermission(ProjectFeature feature, User user, TeamMember member) {
		Project project = feature.getProject();
		if(project!=null && user!=null && (
				NumberUtils.compare(project.getId(), user.getProject().getId())==0 ||
				NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0)) {
			return true;
		}
		if(member!=null && feature!=null && member.getEnabled() &&
				NumberUtils.compare(feature.getRole().getLevel(), member.getRole().getLevel())>=0) {
			return true;
		}
		if(NumberUtils.compare(feature.getRole().getLevel(), 50)>0 ) {
			return true;
		}
		return false;
	}

}
