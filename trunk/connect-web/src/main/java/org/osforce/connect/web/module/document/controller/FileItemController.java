package org.osforce.connect.web.module.document.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.document.FileItem;
import org.osforce.connect.service.document.FileItemService;
import org.osforce.connect.web.AttributeKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 10, 2011 - 11:03:41 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/document")
public class FileItemController {

	private FileItemService fileItemService;
	
	public FileItemController() {
	}
	
	@Autowired
	public void setFileItemService(FileItemService fileItemService) {
		this.fileItemService = fileItemService;
	}
	
	@RequestMapping(value="/file", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(FileItem fileItem, WebRequest request) {
		Attachment attach = (Attachment) request.getAttribute(
				AttributeKeys.ATTACHMENT_KEY, WebRequest.SCOPE_REQUEST);
		fileItem.setRealFile(attach);
		fileItem.setEnabled(true);
		fileItemService.createFileItem(fileItem);
		return Collections.singletonMap("id", fileItem.getId());
	}

	@RequestMapping(value="/file", method=RequestMethod.GET)
	public String get(@RequestParam Long fileId) {
		FileItem fileItem = fileItemService.getFileItem(fileId);
		return "forward:/commons/download?attachmentId="+fileItem.getRealFileId();
	}
}
