package org.osforce.e2.web.module.gallery.controller;

import org.osforce.e2.entity.gallery.Photo;
import org.osforce.e2.service.gallery.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 24, 2011 - 12:01:35 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/gallery")
public class PhotoController {

	private PhotoService photoService;
	
	public PhotoController() {
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@RequestMapping(value="/photo", method=RequestMethod.POST)
	public @ResponseBody String update(@RequestParam Long attachmentId, 
			@RequestParam Long albumId, @RequestParam Long enteredId, 
			@RequestParam Long modifiedId, Photo photo) {
		photo.setRealFileId(attachmentId);
		photo.setAlbumId(albumId);
		photo.setEnteredId(enteredId);
		photo.setModifiedId(modifiedId);
		if(photo.getId()==null) {
			photoService.createPhoto(photo);
		} else {
			photoService.updatePhoto(photo);
		}
		return "success";
	}
	
}
