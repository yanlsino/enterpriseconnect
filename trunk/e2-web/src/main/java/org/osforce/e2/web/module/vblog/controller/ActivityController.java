package org.osforce.e2.web.module.vblog.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.commons.Activity;
import org.osforce.e2.service.vblog.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 14, 2011 - 12:03:10 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/vblog")
public class ActivityController {

	private ActivityService activityService;
	 
	public ActivityController() {
	}
	
	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@RequestMapping(value="/activity", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Activity activity) {
		if(activity.getId()==null) {
			activityService.createActivity(activity);
		} else {
			activityService.updateActivity(activity);
		}
		return Collections.singletonMap("id", activity.getId());
	}
	
}
