package org.osforce.connect.web.portal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.lang.StringUtil;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.web.AttributeKeys;
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
public class PortalController {

	private static final String DEFAULT_VIEWS = "default/views/";
	private static final String VIEWS = "views";
	private static final String VIEW_PREFIX = "/WEB-INF/themes";

	private ConfigFactory configFactory;
	private FragmentInvoker fragmentInvoker;

	private Map<String, String> viewCache = new HashMap<String, String>();

	public PortalController() {
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
			@RequestParam(required=false) String popup, ServletRequest request) {
		Site site = (Site) request.getAttribute(AttributeKeys.SITE_KEY);
		Theme theme = (Theme) request.getAttribute(AttributeKeys.THEME_KEY);
		Project project = (Project) request.getAttribute(AttributeKeys.PROJECT_KEY);
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
			request.setAttribute("pageConfig", pageConfig);
			String layout = StringUtil.buildPath(theme.getName(), "layout");
			if(StringUtils.isNotBlank(popup)) {
				layout = StringUtil.buildPath(theme.getName(), "layout-popup");
			}
			return layout;
		}
		WebApplicationContext webAppContext = RequestContextUtils.getWebApplicationContext(request);
		return getView(webAppContext, site, theme, viewName);
	}

	@RequestMapping(value = "/fragment/{fragmentId}", method = RequestMethod.GET)
	public String renderFragment(@PathVariable String fragmentId,
			HttpServletRequest request, HttpServletResponse response) {
		Site site = (Site) request.getAttribute(AttributeKeys.SITE_KEY);
		Theme theme = (Theme) request.getAttribute(AttributeKeys.THEME_KEY);
		FragmentConfig fragmentConfig = (FragmentConfig) request.getAttribute(FragmentConfig.NAME);
		if(fragmentConfig==null) {
			fragmentConfig = configFactory.getFragmentConfig(fragmentId);
		}
		 FragmentContext fragmentContext = new FragmentContext(
				 request, response, fragmentConfig);
		 request.setAttribute("fragmentConfig", fragmentConfig);
		String viewName =  fragmentInvoker.invoke(fragmentContext);
		WebApplicationContext webAppContext = RequestContextUtils.getWebApplicationContext(request);
		return getView(webAppContext, site, theme, viewName);
	}

	protected String getView(WebApplicationContext webAppContext, Site site, Theme theme, String viewName) {
		if(StringUtils.isBlank(viewName)) {
			return null;
		}
		String view = viewCache.get(viewName);
		if(view==null) {
			String viewPath = StringUtil.buildPath(VIEW_PREFIX, theme, VIEWS, viewName, ".jsp");
			Resource resource = webAppContext.getResource(viewPath);
			if(resource.exists()) {
				view = StringUtil.buildPath(theme.getName(), VIEWS, viewName);
			} else {
				 view = StringUtil.buildPath(DEFAULT_VIEWS, viewName);
			}
			viewCache.put(viewName, view);
		}
		return view;
	}

}
