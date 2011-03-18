package org.osforce.e2.service.document;

import java.util.List;

import org.osforce.e2.entity.document.FileItem;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:37:10 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface FileItemService {

	FileItem getFileItem(Long fileItemId);
	
	void createFileItem(FileItem fileItem);
	
	void updateFileItem(FileItem fileItem);
	
	void deleteFileItem(Long fileItemId);

	List<FileItem> getFileItemList(Long folderId);
}
