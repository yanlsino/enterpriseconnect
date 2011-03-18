package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.system.Role;
import org.osforce.e2.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 19, 2011 - 4:47:46 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class RoleController {

	private RoleService roleService;
	
	public RoleController() {
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@RequestMapping(value="/role", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Role role) {
		if(role.getId()==null) {
			roleService.createRole(role);
		} else {
			roleService.updateRole(role);
		}
		return Collections.singletonMap("id", role.getId());
	}
}
