package org.osforce.connect.service.document;

import java.util.List;

import org.osforce.connect.entity.document.Folder;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:37:14 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface FolderService {

	Folder getFolder(Long folderId);
	
	void createFolder(Folder folder);
	
	void updateFolder(Folder folder);
	
	void deleteFolder(Long folderId);

	List<Folder> getRootForlders(Long projectId);
}
