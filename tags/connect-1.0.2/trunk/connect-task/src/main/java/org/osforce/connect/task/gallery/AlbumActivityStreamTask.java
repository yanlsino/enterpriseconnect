package org.osforce.connect.task.gallery;

import java.util.Map;

import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.connect.service.vblog.ActivityService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 2:22:47 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AlbumActivityStreamTask extends AbstractTask {

	private AlbumService albumService;
	private ActivityService activityService;

	public AlbumActivityStreamTask() {
	}

	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	@Autowired
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		Long albumId = (Long) context.get("albumId");
		Album album = albumService.getAlbum(albumId);
		String template = (String) context.get("template");
		Activity activity = new Activity();
		activity.setLinkedId(albumId);
		activity.setEntity(Album.NAME);
		activity.setType(Album.NAME);
		activity.setDescription(template);
		activity.setFormat(Activity.FORMAT_FTL);
		activity.setProjectId(album.getProjectId());
		activity.setEnteredId(album.getModifiedId());
		activityService.createActivity(activity);
	}

}
