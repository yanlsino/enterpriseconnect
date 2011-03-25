package org.osforce.e2.web.module.gallery.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.gallery.Album;
import org.osforce.e2.service.gallery.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:02:46 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/gallery")
public class AlbumController {

	private AlbumService albumService;
	
	public AlbumController() {
	}
	
	@Autowired
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@RequestMapping(value="/album")
	public @ResponseBody Map<String, Long> update(Album album) {
		if(album.getId()==null) {
			albumService.createAlbum(album);
		} else {
			albumService.updateAlbum(album);
		}
		return Collections.singletonMap("id", album.getId());
	}
}
