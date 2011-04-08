package org.osforce.e2.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.lang.StringUtil;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.web.framework.config.ConfigFactory;
import org.osforce.platform.web.framework.config.FragmentConfig;
import org.osforce.platform.web.framework.config.PageConfig;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.osforce.platform.web.framework.core.FragmentInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

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

	private static final String DEFAULT_LAYOUT = "default/layout";
	private static final String DEFAULT_LAYOUT_POPUP = "default/layout-popup";
	private static final String DEFAULT_VIEWS = "default/views/";
	private static final String VIEWS = "views";
	private static final String VIEW_PREFIX = "/WEB-INF/themes";
	private static final String VIEW_SUFFIX_PATTERN = ".*";
	
	private ConfigFactory configFactory;
	private FragmentInvoker fragmentInvoker;
	
	private Map<String, Boolean> viewCache = new HashMap<String, Boolean>();
	
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
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY, WebRequest.SCOPE_REQUEST);
		if(project!=null && !viewName.contains("categoryCode")) {
			if(viewName.contains("T")) {
				viewName += "_categoryCode=" + project.getCategory().getCode(); 
			} else {
				viewName += "TcategoryCode=" + project.getCategory().getCode();
			}
		}
		//
		PageConfig pageConfig = configFactory.getPageConfig(site.getDomain(), viewName);
		if(pageConfig!=null) {
			request.setAttribute("pageConfig", pageConfig, WebRequest.SCOPE_REQUEST);
			String layout = StringUtils.isBlank(site.getTheme().getLayout())
					?DEFAULT_LAYOUT: StringUtil.buildPath(site.getTheme().getName(), site.getTheme().getLayout()) ;
			if(StringUtils.isNotBlank(popup)) {
				layout = StringUtils.isBlank(site.getTheme().getLayoutPopup())
						?DEFAULT_LAYOUT_POPUP: StringUtil.buildPath(site.getTheme().getName(), site.getTheme().getLayoutPopup()) ;
			}
			return layout;
		}
		return "commons/error404";
	}

	@RequestMapping(value = "/fragment/{fragmentId}", method = RequestMethod.GET)
	public String renderFragment(@PathVariable String fragmentId,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Site site = (Site) request.getAttribute(AttributeKeys.SITE_KEY);
		FragmentConfig fragmentConfig = (FragmentConfig) request.getAttribute(FragmentConfig.NAME);
		if(fragmentConfig==null) {
			fragmentConfig = configFactory.getFragmentConfig(fragmentId);
		}
		 FragmentContext fragmentContext = new FragmentContext(
				 request, response, fragmentConfig);
		 request.setAttribute("fragmentConfig", fragmentConfig);
		String viewName =  fragmentInvoker.invoke(fragmentContext);
		if(StringUtils.isBlank(viewName)) {
			return viewName;
		}
		// TODO  need refactor
		Boolean flag = viewCache.get(viewName);
		if(flag==null) {
			WebApplicationContext webAppContext = RequestContextUtils.getWebApplicationContext(request);
			String location = StringUtil.buildPath(VIEW_PREFIX, site.getTheme().getName(), VIEWS, viewName);
			String locationPattern = location + VIEW_SUFFIX_PATTERN;
			Resource[] resources = webAppContext.getResources(locationPattern);
			if(resources!=null) {
				for(Resource resource : resources) {
					if(resource.exists()) {
						flag = true;
					}
				}
			}
			viewCache.put(viewName, flag==null?false:flag);
		}
		if(flag==null || !flag) {
			viewName = DEFAULT_VIEWS + viewName;
		} else {
			viewName = StringUtil.buildPath(site.getTheme().getName(), VIEWS, viewName);
		}
		return viewName;
	}
	
}
