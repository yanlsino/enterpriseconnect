package org.osforce.e2.dao.document.impl;

import org.osforce.e2.dao.document.FileItemDao;
import org.osforce.e2.entity.document.FileItem;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:18:23 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class FileItemDaoImpl extends AbstractDao<FileItem>
	implements FileItemDao {

	public FileItemDaoImpl() {
		super(FileItem.class);
	}
}
