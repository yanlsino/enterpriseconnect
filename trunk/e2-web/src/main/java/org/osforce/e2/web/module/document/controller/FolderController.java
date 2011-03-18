package org.osforce.e2.web.module.document.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osforce.e2.entity.document.Folder;
import org.osforce.e2.service.document.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 8:43:37 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/document")
public class FolderController {

	private FolderService folderService;
	
	public FolderController() {
	}
	
	@Autowired
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	
	@RequestMapping(value="/folder", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Folder folder) {
		if(folder.getId()!=null) {
			folderService.createFolder(folder);
		} else {
			folderService.updateFolder(folder);
		}
		return Collections.singletonMap("id", folder.getId());
	}
	
	@RequestMapping(value="/folders")
	public @ResponseBody List<Map<String, Object>> list(
			@RequestParam Long projectId, @RequestParam(required=false) Long id,
			@RequestParam(required=false) boolean tree) {
		List<Map<String, Object>> folderList = new ArrayList<Map<String, Object>>();
		List<Folder> tmp = Collections.emptyList();
		if(id==null) {
			tmp = folderService.getRootForlders(projectId);
		} else {
			Folder folder = folderService.getFolder(id);
			tmp = folder.getChildren();
		}
		walkFolderTree(tmp, folderList, tree);
		return folderList;
	}
	
	private void walkFolderTree(List<Folder> folders, List<Map<String, Object>> folderList, boolean tree) {
		for(Folder folder : folders) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("id", folder.getId());
			if(folder.getParentId()!=null) {
				model.put("pId", folder.getParentId());
			}
			model.put("name", folder.getName());
			model.put("icon", "");
			model.put("isParent", true);
			folderList.add(model);
			if(tree && folder.getChildren()!=null) {
				walkFolderTree(folder.getChildren(), folderList, tree);
			}
		}
	}
}
