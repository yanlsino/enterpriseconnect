package org.osforce.connect.web.module.document.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.service.document.FolderService;
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
	
	@Deprecated
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
		//walkFolderTree(tmp, folderList);
		return folderList;
	}
	
}
