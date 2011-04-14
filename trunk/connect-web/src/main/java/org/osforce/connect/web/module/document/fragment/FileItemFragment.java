package org.osforce.connect.web.module.document.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		JSONArray folderTree = buildTree(parentFolders);
		context.putRequestData(AttributeKeys.FOLDER_TREE_KEY_READABLE, folderTree.toString());
		context.putRequestData(AttributeKeys.FILE_ITEM_KEY_READABLE, fileItem);
		return "document/file_form";
	}
	
	// TODO merge @see FolderFragment
	private JSONArray buildTree(List<Folder> folders) {
		JSONArray jsonArray = new JSONArray();
		for(Folder folder : folders) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", folder.getId());
			jsonObject.put("label", folder.getName());
			jsonObject.put("type", "text");
			jsonObject.put("expanded", true);
			if(!folder.getChildren().isEmpty()) {
				walkTree(jsonObject, folder.getChildren());
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	private void walkTree(JSONObject jsonObject, List<Folder> folders) {
		JSONArray jsonArray = new JSONArray();
		for(Folder folder : folders) {
			JSONObject subJsonObject = new JSONObject();
			subJsonObject.put("id", folder.getId());
			subJsonObject.put("label", folder.getName());
			subJsonObject.put("type", "text");
			subJsonObject.put("expanded", true);
			if(!folder.getChildren().isEmpty()) {
				walkTree(subJsonObject, folder.getChildren());
			}
			jsonArray.add(subJsonObject);
		}
		jsonObject.put("children", jsonArray);
	}
}
