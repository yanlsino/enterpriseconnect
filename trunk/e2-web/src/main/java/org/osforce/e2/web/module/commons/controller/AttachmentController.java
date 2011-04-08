package org.osforce.e2.web.module.commons.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.osforce.e2.entity.commons.Attachment;
import org.osforce.e2.service.commons.AttachmentService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.e2.web.module.commons.util.AttachmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 20, 2011 - 9:54:03 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/commons")
public class AttachmentController {

	private AttachmentService attachmentService;
	
	public AttachmentController() {
	}

	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping(value="/attachment", method=RequestMethod.POST)
	public String upload(@RequestParam MultipartFile file, 
			@RequestParam String forward, WebRequest request) throws IOException {
		Attachment attach = new Attachment();
		attach.setContentType(file.getContentType());
		attach.setFileName(file.getOriginalFilename());
		attach.setSize(file.getSize());
		attach.setBytes(file.getBytes());
		attachmentService.createAttachment(attach);
		// write attachment content to local file
		AttachmentUtil.write(attach);
		request.setAttribute(AttributeKeys.ATTACHMENT_KEY, 
				attach, WebRequest.SCOPE_REQUEST);
		return "forward:"+forward;
	}
	
	@RequestMapping(value="/kindeditor", method=RequestMethod.POST)
	public String upload(@RequestParam String imgTitle, 
			@RequestParam MultipartFile imgFile, @RequestParam Long imgWidth, 
			@RequestParam Long imgHeight, WebRequest request) throws IOException {
		Attachment attachment = new Attachment();
		attachment.setContentType(imgFile.getContentType());
		attachment.setFileName(imgFile.getOriginalFilename());
		attachment.setSize(imgFile.getSize());
		attachment.setBytes(imgFile.getBytes());
		attachmentService.createAttachment(attachment);
		AttachmentUtil.write(attachment);
		//
		request.setAttribute("imgWidth", imgWidth, WebRequest.SCOPE_REQUEST);
		request.setAttribute("imgHeight", imgHeight, WebRequest.SCOPE_REQUEST);
		request.setAttribute("attachment", attachment, WebRequest.SCOPE_REQUEST);
		return "/commons/attachment_kindeditor_ajax";
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(@RequestParam Long attachmentId, 
			@RequestParam(required=false) String dimension, 
			HttpServletResponse response) throws Exception {
		Attachment attachment = attachmentService.getAttachment(attachmentId);
		attachment.setDimension(dimension);
		// read attachment content from local file
		AttachmentUtil.read(attachment);
		// prepare download
		if(!attachment.getContentType().matches("image/.*")) {
			response.setContentType(attachment.getContentType());
			response.setContentLength(attachment.getSize().intValue());
			response.setHeader("Content-Disposition","attachment; filename=\"" + attachment.getFileName() +"\"");
		}
		response.getOutputStream().write(attachment.getBytes());
	}
	
}
