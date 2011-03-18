package org.osforce.e2.web.module.system.controller;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.system.UserService;
import org.osforce.e2.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public @ResponseBody Boolean exist(@RequestParam String username) {
		User user = userService.getUser(username);
		return user!=null;
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
}
