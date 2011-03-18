package org.osforce.e2.service.system;

import java.util.List;

import org.osforce.e2.entity.system.Theme;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:23:23 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface ThemeService {
	
	Theme getTheme(Long  themeId);
	
	void createTheme(Theme theme);
	
	void updateTheme(Theme theme);

	Page<Theme> getThemePage(Page<Theme> page);

	List<Theme> getThemeList();
	
}
