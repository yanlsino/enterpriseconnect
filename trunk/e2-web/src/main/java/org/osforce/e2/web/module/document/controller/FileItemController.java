package org.osforce.e2.web.module.document.controller;

import org.osforce.e2.entity.commons.Attachment;
import org.osforce.e2.entity.document.FileItem;
import org.osforce.e2.service.commons.AttachmentService;
import org.osforce.e2.service.document.FileItemService;
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
 * @create Mar 10, 2011 - 11:03:41 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/document")
public class FileItemController {

	private FileItemService fileItemService;
	private AttachmentService attachmentService;
	
	public FileItemController() {
	}
	
	@Autowired
	public void setFileItemService(FileItemService fileItemService) {
		this.fileItemService = fileItemService;
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping(value="/file", method=RequestMethod.POST)
	public @ResponseBody String update(@RequestParam Long attachmentId,
			@RequestParam Long folderId, @RequestParam Long enteredId, 
			@RequestParam Long modifiedId) {
		Attachment realFile = attachmentService.getAttachment(attachmentId);
		FileItem fileItem = new FileItem();
		fileItem.setFolderId(folderId);
		fileItem.setEnteredId(enteredId);
		fileItem.setModifiedId(modifiedId);
		fileItem.setRealFile(realFile);
		fileItem.setEnabled(true);
		fileItemService.createFileItem(fileItem);
		return "success";
	}

	@RequestMapping(value="/file", method=RequestMethod.GET)
	public String get(@RequestParam Long fileId) {
		FileItem fileItem = fileItemService.getFileItem(fileId);
		return "forward:/commons/download?attachmentId="+fileItem.getRealFileId();
	}
}
