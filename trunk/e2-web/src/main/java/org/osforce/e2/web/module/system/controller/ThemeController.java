package org.osforce.e2.web.module.system.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.system.Theme;
import org.osforce.e2.service.system.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 2:03:24 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class ThemeController {

	private ThemeService themeService;
	
	public ThemeController() {
	}
	
	@Autowired
	public void setThemeService(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	@RequestMapping(value="/theme", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Theme theme) {
		if(theme.getId()==null) {
			themeService.createTheme(theme);
		} else {
			themeService.updateTheme(theme);
		}
		return Collections.singletonMap("id", theme.getId());
	}
	
}
