package org.osforce.connect.service.commons;

import org.osforce.connect.entity.commons.Attachment;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 20, 2011 - 9:55:29 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AttachmentService {

	Attachment getAttachment(Long attachmentId);
	
	void createAttachment(Attachment attachment);
	
	void updateAttachment(Attachment attachment);
	
	void deleteAttachment(Long attachmentId);
}
