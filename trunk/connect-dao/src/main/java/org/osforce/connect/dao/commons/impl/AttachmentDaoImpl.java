package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.AttachmentDao;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:51:45 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class AttachmentDaoImpl extends AbstractDao<Attachment> 
	implements AttachmentDao {

	public AttachmentDaoImpl() {
		super(Attachment.class);
	}
}
