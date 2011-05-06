package org.osforce.connect.service.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ThemeDao;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.service.system.ThemeService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 28, 2011 - 4:28:45 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ThemeServiceImpl implements ThemeService {

	private ThemeDao themeDao;
	
	public ThemeServiceImpl() {
	}
	
	@Autowired
	public void setThemeDao(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}
	
	public Theme getTheme(Long themeId) {
		return themeDao.get(themeId);
	}

	public void createTheme(Theme theme) {
		updateTheme(theme);
	}

	public void updateTheme(Theme theme) {
		if(theme.getId()==null) {
			themeDao.save(theme);
		} else {
			themeDao.update(theme);
		}
	}
	
	public Page<Theme> getThemePage(Page<Theme> page) {
		return themeDao.findPage(page, new QueryAppender());
	}
	
	public List<Theme> getThemeList() {
		QueryAppender appender = new QueryAppender();
		return themeDao.find(appender);
	}

}
