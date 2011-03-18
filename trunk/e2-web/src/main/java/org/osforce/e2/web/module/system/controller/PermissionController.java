package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.system.Permission;
import org.osforce.e2.service.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 24, 2011 - 10:59:17 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class PermissionController {

	private PermissionService permissionService;
	
	public PermissionController() {
	}
	
	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	@RequestMapping(value="/permission", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Permission permission) {
		if(permission.getId()==null) {
			permissionService.createPermission(permission);
		} else {
			permissionService.updatePermission(permission);
		}
		return Collections.singletonMap("id", permission.getId());
	}
}
