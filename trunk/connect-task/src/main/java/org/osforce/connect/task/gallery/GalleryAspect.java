package org.osforce.connect.task.gallery;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GalleryAspect {

	private static final String TEMPLATE_ALBUM_UPDATE = "activity/album_update.ftl";
	private static final String TEMPLATE_PHOTO_UPDATE = "activity/photo_update.ftl";

	private Task albumActivityStreamTask;
	private Task photoActivityStreamTask;

	public GalleryAspect() {
	}

	@Autowired
	@Qualifier("albumActivityStreamTask")
	public void setAlbumActivityStreamTask(Task albumActivityStreamTask) {
		this.albumActivityStreamTask = albumActivityStreamTask;
	}

	@Autowired
	@Qualifier("photoActivityStreamTask")
	public void setPhotoActivityStreamTask(Task photoActivityStreamTask) {
		this.photoActivityStreamTask = photoActivityStreamTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.gallery.AlbumService.createAlbum(..)) ||" +
	"execution(* org.osforce.connect.service.gallery.AlbumService.updateAlbum(..))")
	public void updateAlbum(JoinPoint jp) {
		Album album = (Album) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("albumId", album.getId());
		context.put("template", TEMPLATE_ALBUM_UPDATE);
		albumActivityStreamTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.gallery.PhotoService.createPhoto(..)) ||" +
	"execution(* org.osforce.connect.service.gallery.PhotoService.updatePhoto(..))")
	public void updatePhoto(JoinPoint jp) {
		Photo photo = (Photo) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("photoId", photo.getId());
		context.put("template", TEMPLATE_PHOTO_UPDATE);
		photoActivityStreamTask.doAsyncTask(context);
	}

}
