/**
 *
 */
package org.osforce.connect.task.vblog;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.commons.Activity;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 11:23:21 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class VBlogAspect {

	private Task vBlogSynchronizeTask;

	public VBlogAspect() {
	}

	@Autowired
	@Qualifier("vBlogSynchronizeTask")
	public void setvBlogSynchronizeTask(Task vBlogSynchronizeTask) {
		this.vBlogSynchronizeTask = vBlogSynchronizeTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.vblog.ActivityService.createActivity(..))")
	public void createActivity(JoinPoint jp) {
		Activity activity = (Activity) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("activityId", activity.getId());
		context.put("targets", activity.getTargets());
		vBlogSynchronizeTask.doAsyncTask(context);
	}

}
