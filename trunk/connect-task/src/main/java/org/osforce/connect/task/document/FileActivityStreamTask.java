package org.osforce.connect.task.document;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.document.FileItem;
import org.osforce.connect.service.document.FileItemService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 3:25:55 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class FileActivityStreamTask extends AbstractTask {

	private ActivityService activityService;
	private FileItemService fileItemService;

	public FileActivityStreamTask() {
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Autowired
	public void setFileItemService(FileItemService fileItemService) {
		this.fileItemService = fileItemService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long fileItemId = (Long) context.get("fileItemId");
		FileItem fileItem = fileItemService.getFileItem(fileItemId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(fileItemId);
		activity.setEntity(FileItem.NAME);
		activity.setType(FileItem.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(fileItem.getFolder().getProjectId());
		activity.setEnteredId(fileItem.getModifiedId());
		activityService.createActivity(activity);
	}

}
