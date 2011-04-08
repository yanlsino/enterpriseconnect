package org.osforce.e2.web.module.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.system.UserService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 7:32:05 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class UserController {

	private UserService userService;
	
	public UserController() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/user/exist")
	public @ResponseBody Map<String, Object> exist(@RequestParam String username) {
		User user = userService.getUser(username);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("code", 200);
		model.put("exist", user!=null);
		return model;
	}
	
	@RequestMapping(value="/user/active")
	public String active(@RequestParam String username, 
			@RequestParam String token, WebRequest request) {
		User user = userService.getUser(username);
		if(user!=null && StringUtils.equals(token, user.getToken())) {
			user.setEnabled(true);
			userService.updateUser(user);
			request.setAttribute(AttributeKeys.USER_KEY, user, WebRequest.SCOPE_SESSION);
			request.setAttribute(AttributeKeys.USER_KEY_READABLE, user, WebRequest.SCOPE_SESSION);
			return "redirect:/" + user.getProject().getUniqueId() + "/profile";
		}
		return "redirect:/";
 	}
	
	@RequestMapping(value="/users/auto", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> autoComplete(
			@RequestParam String query) {
		Page<User> page = new Page<User>(10);
		page = userService.getUserPage(page, query);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("query", query);
		model.put("data", "");
		List<String> suggestions = new ArrayList<String>();
		for(User user : page.getResult()) {
			suggestions.add(user.getUsername());
		}
		model.put("suggestions", suggestions);
		return model;
	}
	
}
