package org.osforce.e2.web.module.gallery.fragment;

import java.util.List;

import org.osforce.e2.entity.gallery.Album;
import org.osforce.e2.entity.gallery.Photo;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.gallery.AlbumService;
import org.osforce.e2.service.gallery.PhotoService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:00:30 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class PhotoFragment {

	private PhotoService photoService;
	private AlbumService albumService;
	
	public PhotoFragment() {
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	public String doListView(@Param Long albumId, @Param Long photoId,
			Page<Photo> page, FragmentContext context) {
		if(albumId!=null) {
			page = photoService.getPhotoPage(page, albumId);
			context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
			//
			Photo photo = page.getResult().isEmpty() ? null:page.getResult().get(0);
			if(photoId!=null) {
				photo = photoService.getPhoto(photoId);
			}
			context.putRequestData(AttributeKeys.PHOTO_KEY_READABLE, photo);
		}
		return "gallery/photos_list";
	}
	
	public String doFormView(@Param Long photoId, @Param Long albumId, 
			Project project, User user, FragmentContext context) {
		Photo photo = new Photo();
		photo.setAlbumId(albumId);
		photo.setEnteredId(user.getId());
		photo.setModifiedId(user.getId());
		context.putRequestData(AttributeKeys.PHOTO_KEY_READABLE, photo);
		//
		List<Album> albums = albumService.getAlbumList(project);
		context.putRequestData(AttributeKeys.ALBUM_LIST_KEY_READABLE, albums);
		return "gallery/photo_form";
	}
}
