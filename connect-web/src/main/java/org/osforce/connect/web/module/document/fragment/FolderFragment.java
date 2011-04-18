package org.osforce.connect.web.module.document.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONSerializer;

import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
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
 * @create Mar 9, 2011 - 8:15:06 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class FolderFragment {

	private FolderService folderService;
	
	public FolderFragment() {
	}
	
	@Autowired
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	
	public  String doTreeView(Project project, FragmentContext context) {
		List<Folder> parentFolders =  folderService.getRootForlders(project.getId());
		List<Map<String, Object>> folderList = new ArrayList<Map<String, Object>>();
		walkFolderTree(parentFolders, folderList);
		String treeData = JSONSerializer.toJSON(folderList).toString();
		context.putRequestData(AttributeKeys.FOLDER_TREE_KEY_READABLE, treeData);
		return "document/folders_tree";
	}
	
	public String doFormView(@Param Long folderId, @Param Long parentFolderId,
			Project project, User user, FragmentContext context) {
		Folder folder = new Folder();
		folder.setModifiedBy(user);
		folder.setEnteredBy(user);
		folder.setProject(project);
		if(folderId!=null) {
			folder = folderService.getFolder(folderId);
		}
		Folder parentFolder = null;
		if(parentFolderId!=null) {
			parentFolder = folderService.getFolder(parentFolderId);
			folder.setParent(parentFolder);
		}
		List<Folder> parentFolders = new ArrayList<Folder>();
		if(parentFolder!=null && parentFolder.getParent()!=null) {
			parentFolders = parentFolder.getParent().getChildren();
		} else {
			parentFolders = folderService.getRootForlders(project.getId());
		}
		//
		List<Map<String, Object>> folderList = new ArrayList<Map<String, Object>>();
		walkFolderTree(parentFolders, folderList);
		String treeData = JSONSerializer.toJSON(folderList).toString();
		context.putRequestData(AttributeKeys.FOLDER_TREE_KEY_READABLE, treeData);
		//
		context.putRequestData(AttributeKeys.FOLDER_LIST_KEY_READABLE, parentFolders);
		context.putRequestData(AttributeKeys.FOLDER_KEY_READABLE, folder);
		return "document/folder_form";
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
