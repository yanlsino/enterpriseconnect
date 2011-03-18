package org.osforce.e2.web.module.document.fragment;

import java.util.ArrayList;
import java.util.List;

import org.osforce.e2.entity.document.Folder;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.service.document.FolderService;
import org.osforce.e2.web.AttributeKeys;
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
	
	public  String doTreeView(FragmentContext context) {
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
		context.putRequestData(AttributeKeys.FOLDER_LIST_KEY_READABLE, parentFolders);
		context.putRequestData(AttributeKeys.FOLDER_KEY_READABLE, folder);
		return "document/folder_form";
	}
	
}
