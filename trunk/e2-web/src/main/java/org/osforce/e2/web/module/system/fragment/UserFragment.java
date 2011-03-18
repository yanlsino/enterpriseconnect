package org.osforce.e2.web.module.system.fragment;

import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.system.UserService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 19, 2011 - 3:53:42 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class UserFragment {

	private UserService userService;
	
	public UserFragment() {
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String doListView(@Param Long siteId, 
			Page<User> page, FragmentContext context) {
		if(siteId==null) {
			page = userService.getUserPage(page);
		} else {
			page = userService.getUserPage(page, siteId);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/users_list";
	}
	
	public String doFormView(@Param Long userId, FragmentContext context) {
		User user = userService.getUser(userId);
		context.putRequestData(AttributeKeys.USER_KEY_READABLE, user);
		return "system/user_form";
	}
}
