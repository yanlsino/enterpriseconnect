package org.osforce.connect.web.module.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.jpa.Search;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.system.UserService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:18:51 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	private UserService userService;
	private EntityManager entityManager;
	
	public SystemController() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	// TODO move to @Code UserController 
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> login(WebRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User persisted = userService.getUser(userDetails.getUsername());
		persisted.setLastLogin(new Date());
		userService.updateUser(persisted);
		request.setAttribute(AttributeKeys.USER_KEY, persisted, WebRequest.SCOPE_SESSION);
		request.setAttribute(AttributeKeys.USER_KEY_READABLE, persisted, WebRequest.SCOPE_SESSION);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", persisted.getId());
		model.put("uniqueId", persisted.getProject().getUniqueId());
		return model;
	}
	
	// TODO move to @Code UserController
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(WebRequest request) {
		request.removeAttribute(AttributeKeys.USER_KEY, WebRequest.SCOPE_SESSION);
		request.removeAttribute(AttributeKeys.USER_KEY_READABLE, WebRequest.SCOPE_SESSION);
		return "redirect:/";
	}
	
	// TODO move to @Code UserController
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> register(User user, WebRequest request) {
		if(StringUtils.equals(user.getPassword(), user.getRePassword()) || 
				user.getId()!=null) {
			Project project = (Project) request.getAttribute(
					AttributeKeys.PROJECT_KEY,  WebRequest.SCOPE_SESSION);
			userService.register(user, project);
		}
		//
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", user.getId());
		model.put("uniqueId", user.getProject().getUniqueId());
		return model;
	}

	@RequestMapping(value="index-sync")
	public String sync() {
		Search.getFullTextEntityManager(entityManager).createIndexer().start();
		return "redirect:/system";
	}
}
