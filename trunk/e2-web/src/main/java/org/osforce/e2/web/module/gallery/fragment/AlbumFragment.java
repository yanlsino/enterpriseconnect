package org.osforce.e2.web.module.gallery.fragment;

import org.osforce.e2.entity.gallery.Album;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.gallery.AlbumService;
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
 * @create Mar 24, 2011 - 12:00:59 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class AlbumFragment {

	private AlbumService albumService;
	
	public AlbumFragment() {
	}
	
	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	public String doListView(Page<Album> page, 
			Project project, FragmentContext context) {
		page = albumService.getAlbumPage(page, project);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "gallery/albums_list";
	}
	
	public String doFormView(@Param Long albumId, 
			Project project, User user, FragmentContext context) {
		Album album = new Album();
		album.setEnteredBy(user);
		album.setModifiedBy(user);
		album.setProject(project);
		if(albumId!=null) {
			album = albumService.getAlbum(albumId);
		}
		context.putRequestData(AttributeKeys.ALBUM_KEY_READABLE, album);
		return "gallery/album_form";
	}
	
}
