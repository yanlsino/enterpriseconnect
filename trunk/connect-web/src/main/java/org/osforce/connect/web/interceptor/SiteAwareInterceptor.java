package org.osforce.connect.web.interceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.service.system.SiteService;
import org.osforce.connect.web.AttributeKeys;
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
		String domain = request.getServerName();
		Site site = siteService.getSite(domain);
		if(site==null) {
			// site always is null, build a default site use default theme
			site = buildDefaultSite(request);
		}
		// Site will always bind to current request 
		request.setAttribute(AttributeKeys.SITE_KEY, site);
		request.setAttribute(AttributeKeys.SITE_KEY_READABLE, site);
		//
		Theme theme = site.getTheme()!=null?site.getTheme():new Theme("default");
		request.setAttribute(AttributeKeys.THEME_KEY, theme);
		request.setAttribute(AttributeKeys.THEME_KEY_READABLE, theme);
		return super.preHandle(request, response, handler);
	}
	
	// TODO
	protected Site buildDefaultSite(HttpServletRequest request) throws UnknownHostException {
		String domain = request.getServerName();
		for(InetAddress addr : InetAddress.getAllByName("localhost")) {
			if(StringUtils.equals(addr.getHostAddress(), request.getServerName())) {
				domain = "localhost";
			}
		}
		Site site = new Site(
				"Enterprise Connect", 
				null, 
				"Open Source, Socail Business Software, SBS, Social Networking Service, SNS ", 
				domain, 
				true);
		return site;
	}
	
}
