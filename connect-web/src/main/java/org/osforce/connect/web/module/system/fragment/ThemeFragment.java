package org.osforce.connect.web.module.system.fragment;

import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.service.system.ThemeService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 2:13:13 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ThemeFragment {

	private ThemeService themeService;
	
	public ThemeFragment() {
	}
	
	@Autowired
	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	public String doListView(Page<Theme> page, FragmentContext context) {
		page = themeService.getThemePage(page);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/themes_list";
	}
	
	public String doFormView(@Param Long themeId, FragmentContext context) {
		Theme theme = new Theme();
		if(themeId!=null) {
			theme = themeService.getTheme(themeId);
		}
		context.putRequestData(AttributeKeys.THEME_KEY_READABLE, theme);
		return "system/theme_form";
	}
	
}
