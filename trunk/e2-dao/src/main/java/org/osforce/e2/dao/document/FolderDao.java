package org.osforce.e2.dao.document;

import java.util.List;

import org.osforce.e2.entity.document.Folder;
import org.osforce.platform.dao.BaseDao;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:19:44 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface FolderDao extends BaseDao<Folder> {

	List<Folder> findRoot(Long projectId);

}
