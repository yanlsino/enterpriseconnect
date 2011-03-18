package org.osforce.e2.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.lang.StringUtil;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.web.framework.config.ConfigFactory;
import org.osforce.platform.web.framework.config.FragmentConfig;
import org.osforce.platform.web.framework.config.PageConfig;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.osforce.platform.web.framework.core.FragmentInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
 * Core controller for this application
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 12:11:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
public class FragmentController {

	private ConfigFactory configFactory;
	private FragmentInvoker fragmentInvoker;
	
	public FragmentController() {
	}
	
	@Autowired
	public void setFragmentInvoker(FragmentInvoker fragmentInvoker) {
		this.fragmentInvoker = fragmentInvoker;
	}
	
	@Autowired
	public void setConfigFactory(ConfigFactory configFactory) {
		this.configFactory = configFactory;
	}
	
	@RequestMapping("/page")
	public String renderPage(@RequestParam String viewName, 
			@RequestParam(required=false) String popup, WebRequest request) {
		Site site = (Site) request.getAttribute(AttributeKeys.SITE_KEY, WebRequest.SCOPE_REQUEST);
		/*Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY, WebRequest.SCOPE_REQUEST);
		if(project!=null && StringUtils.equals(viewName, "/profile/profile")) {
			viewName = project.getCategory().getCode() + "/" +viewName;
		}*/
		PageConfig pageConfig = configFactory.getPageConfig(site.getDomain(), viewName);
		request.setAttribute("pageConfig", pageConfig, WebRequest.SCOPE_REQUEST);
		String layout = StringUtil.buildPath("themes", site.getTheme().getName(), "layout");
		if(StringUtils.isNotBlank(popup)) {
			layout = StringUtil.buildPath("themes", site.getTheme().getName(), "layout-popup");
		}
		return layout;
	}

	@RequestMapping(value = "/fragment/{fragmentId}", method = RequestMethod.GET)
	public String renderFragment(@PathVariable String fragmentId,
			HttpServletRequest request, HttpServletResponse response) {
		FragmentConfig fragmentConfig = (FragmentConfig) request.getAttribute(FragmentConfig.NAME);
		if(fragmentConfig==null) {
			fragmentConfig = configFactory.getFragmentConfig(fragmentId);
		}
		 FragmentContext fragmentContext = new FragmentContext(
				 request, response, fragmentConfig);
		 request.setAttribute("fragmentConfig", fragmentConfig);
		return fragmentInvoker.invoke(fragmentContext);
	}
	
}
