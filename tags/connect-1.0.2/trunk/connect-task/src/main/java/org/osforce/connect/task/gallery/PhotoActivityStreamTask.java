package org.osforce.connect.task.gallery;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 2:34:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class PhotoActivityStreamTask extends AbstractTask {

	private PhotoService photoService;
	private ActivityService activityService;

	public PhotoActivityStreamTask() {
	}

	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long photoId = (Long) context.get("photoId");
		Photo photo = photoService.getPhoto(photoId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(photoId);
		activity.setEntity(Photo.NAME);
		activity.setType(Photo.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(photo.getAlbum().getProjectId());
		activity.setEnteredId(photo.getModifiedId());
		activityService.createActivity(activity);
	}

}
