package org.osforce.connect.service.commons.impl;

import java.util.Date;

import org.osforce.connect.dao.commons.AttachmentDao;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.service.commons.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 20, 2011 - 9:56:56 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

	private AttachmentDao attachmentDao;
	
	public AttachmentServiceImpl() {
	}
	
	@Autowired
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	public Attachment getAttachment(Long attachmentId) {
		return attachmentDao.get(attachmentId);
	}

	public void createAttachment(Attachment attachment) {
		updateAttachment(attachment);
	}

	public void updateAttachment(Attachment attachment) {
		if(attachment.getId()==null) {
			attachment.setEntered(new Date());
			attachmentDao.save(attachment);
		} else {
			attachmentDao.update(attachment);
		}
	}

	public void deleteAttachment(Long attachmentId) {
		attachmentDao.delete(attachmentId);
	}

}
