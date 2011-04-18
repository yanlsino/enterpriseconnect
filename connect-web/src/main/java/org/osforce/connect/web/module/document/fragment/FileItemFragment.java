package org.osforce.connect.web.module.document.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONSerializer;

import org.osforce.connect.entity.document.FileItem;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.document.FileItemService;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:25:09 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class FileItemFragment {

	private FileItemService fileItemService;
	private FolderService folderService;
	
	public FileItemFragment() {
	}
	
	@Autowired
	public void setFileItemService(FileItemService fileItemService) {
		this.fileItemService = fileItemService;
	}
	
	@Autowired
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	
	public String doListView(@Param Long folderId,
			Project project, FragmentContext context) {
		// 
		if(folderId==null) {
			List<Folder> folders = folderService.getRootForlders(project.getId());
			if(folders.size() > 0) {
				folderId = folders.get(0).getId();
			}
		}
		//
		List<FileItem> fileItems = Collections.emptyList();
		if(folderId!=null) {
			fileItems = fileItemService.getFileItemList(folderId);
			Folder folder = folderService.getFolder(folderId);
			context.putRequestData(AttributeKeys.FOLDER_KEY_READABLE, folder);
			List<Folder> pathFolders = new ArrayList<Folder>();
			do {
				pathFolders.add(0, folder);
			} while ((folder = folder.getParent())!=null);
			context.putRequestData(AttributeKeys.FOLDER_LIST_KEY_READABLE, pathFolders);
		}
		context.putRequestData(AttributeKeys.FILE_ITEM_LIST_KEY_READABLE, fileItems);
		return "document/files_list";
	}
	
	public String doFormView(@Param Long folderId, @Param Long fileId, 
			Project project, User user, FragmentContext context) {
		Folder folder = null;
		if(folderId!=null) {
			folder = folderService.getFolder(folderId);
		} else {
			List<Folder> folders = folderService.getRootForlders(project.getId());
			if(!folders.isEmpty()) {
				folder = folders.get(0);
			}
		}
		FileItem fileItem = new FileItem();
		fileItem.setFolder(folder);
		fileItem.setEnteredBy(user);
		fileItem.setModifiedBy(user);
		if(fileId!=null) {
			fileItem = fileItemService.getFileItem(fileId);
		}
		//
		List<Folder> parentFolders = folderService.getRootForlders(project.getId());
		List<Map<String, Object>> folderList = new ArrayList<Map<String, Object>>();
		walkFolderTree(parentFolders, folderList);
		String treeData = JSONSerializer.toJSON(folderList).toString();
		context.putRequestData(AttributeKeys.FOLDER_TREE_KEY_READABLE, treeData);
		context.putRequestData(AttributeKeys.FILE_ITEM_KEY_READABLE, fileItem);
		return "document/file_form";
	}
	
	private void walkFolderTree(List<Folder> folders, List<Map<String, Object>> folderList) {
		for(Folder folder : folders) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("id", folder.getId());
			if(folder.getParentId()!=null) {
				model.put("pId", folder.getParentId());
			}
			model.put("name", folder.getName());
			model.put("icon", "");
			model.put("open", true);
			model.put("isParent", true);
			folderList.add(model);
			if(folder.getChildren()!=null) {
				walkFolderTree(folder.getChildren(), folderList);
			}
		}
	}
}
