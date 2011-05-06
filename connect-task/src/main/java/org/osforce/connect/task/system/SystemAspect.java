package org.osforce.connect.task.system;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.Site;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 5, 2011 - 3:34:48 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class SystemAspect {

	private Task userRegisterEmailTask;
	private Task siteBackupTask;
	private Task siteRestoreTask;
	private Task resourceSyncTask;

	public SystemAspect() {
	}

	@Autowired
	@Qualifier("userRegisterEmailTask")
	public void setUserRegisterEmailTask(Task userRegisterEmailTask) {
		this.userRegisterEmailTask = userRegisterEmailTask;
	}

	@Autowired
	@Qualifier("siteBackupTask")
	public void setSiteBackupTask(Task siteBackupTask) {
		this.siteBackupTask = siteBackupTask;
	}

	@Autowired
	@Qualifier("siteRestoreTask")
	public void setSiteRestoreTask(Task siteRestoreTask) {
		this.siteRestoreTask = siteRestoreTask;
	}

	@Autowired
	@Qualifier("resourceSyncTask")
	public void setResourceSyncTask(Task resourceSyncTask) {
		this.resourceSyncTask = resourceSyncTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.system.UserService.registerUser(..))")
	public void createProject(JoinPoint jp) {
		//User user = (User) jp.getArgs()[0];
		Project project = (Project) jp.getArgs()[1];
		if(StringUtils.equals(project.getCategory().getCode(), "people")) {
			Map<Object, Object> context = new HashMap<Object, Object>();
			context.put("siteId", project.getCategory().getSiteId());
			context.put("projectId", project.getId());
			context.put("userId", project.getEnteredId());
			userRegisterEmailTask.doAsyncTask(context);
		}
	}

	@AfterReturning("execution(* org.osforce.connect.service.system.SiteService.backupSite(..))")
	public void backupSite(JoinPoint jp) {
		Site site = (Site) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("siteId", site.getId());
		siteBackupTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.system.SiteService.restoreSite(..))")
	public void restoreSite(JoinPoint jp) {
		Site site = (Site) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("siteId", site.getId());
		siteRestoreTask.doAsyncTask(context);
	}

	@SuppressWarnings("unchecked")
	@AfterReturning("execution(* org.osforce.connect.service.system.ResourceService.syncResource())")
	public void syncResource(JoinPoint jp) {
		resourceSyncTask.doSyncTask(Collections.EMPTY_MAP);
	}

}
