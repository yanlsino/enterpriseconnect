package org.osforce.e2.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.castor.CastorUtil;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.Theme;
import org.osforce.e2.service.system.SiteService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.e2.web.custom.urlrewrite.ExtraUrlRewriterConfLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 12:31:09 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 *  
 *  @see ExtraUrlRewriterConfLoader
 */
public class SiteAwareInterceptor extends HandlerInterceptorAdapter {

	private SiteService siteService;
	
	public SiteAwareInterceptor() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// site id bond by ExtraUrlRewriterConfLoader 
		String siteIdStr = (String) request.getAttribute("siteId");
		String key = AttributeKeys.SITE_KEY + "_" +siteIdStr;
		Site site = (Site) request.getSession().getAttribute(key);
		// FIXME 
		if(site==null && StringUtils.isNotBlank(siteIdStr)) {
			Long siteId = CastorUtil.castTo(siteIdStr, Long.class);
			site = siteService.getSite(siteId);
		}
		if(site==null) {
			// site always is null, build a default site use default theme
			site = buildDefaultSite();
			request.getSession().setAttribute(key, site);
		}
		// Site will always bind to current request 
		request.setAttribute(AttributeKeys.SITE_KEY, site);
		request.setAttribute(AttributeKeys.SITE_KEY_READABLE, site);
		request.setAttribute(AttributeKeys.THEME_KEY, site.getTheme());
		request.setAttribute(AttributeKeys.THEME_KEY_READABLE, site.getTheme());
		return super.preHandle(request, response, handler);
	}
	
	protected Site buildDefaultSite() {
		Site site = new Site("Open Source Force", null, "Open, Share", "www.osforce.org", true);
		Theme theme = new Theme("osforce", true);
		site.setTheme(theme);
		return site;
	}
}
