package org.osforce.connect.web.module.gallery.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

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
	public @ResponseBody Map<String, Long> update(Photo photo, WebRequest request) {
		Attachment attach = (Attachment) request.getAttribute(
				AttributeKeys.ATTACHMENT_KEY, WebRequest.SCOPE_REQUEST);
		photo.setName(attach.getName());
		photo.setRealFileId(attach.getId());
		if(photo.getId()==null) {
			photoService.createPhoto(photo);
		} else {
			photoService.updatePhoto(photo);
		}
		return Collections.singletonMap("id", photo.getId());
	}
	
}
