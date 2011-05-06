package org.osforce.connect.task.document;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.osforce.connect.entity.document.FileItem;
import org.osforce.connect.entity.document.Folder;
import org.osforce.platform.task.support.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 3:24:52 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Aspect
@Component
public class DocumentAspect {

	private static final String TEMPLATE_FILE_UPDATE = "activity/file_update.ftl";
	private static final String TEMPLATE_FOLDER_UPDATE = "activity/folder_update.ftl";

	private Task fileActivityStreamTask;
	private Task folderActivityStreamTask;

	public DocumentAspect() {
	}

	@Autowired
	@Qualifier("fileActivityStreamTask")
	public void setFileActivityStreamTask(Task fileActivityStreamTask) {
		this.fileActivityStreamTask = fileActivityStreamTask;
	}

	@Autowired
	@Qualifier("folderActivityStreamTask")
	public void setFolderActivityStreamTask(Task folderActivityStreamTask) {
		this.folderActivityStreamTask = folderActivityStreamTask;
	}

	@AfterReturning("execution(* org.osforce.connect.service.document.FileItemService.createFileItem(..)) ||"
			+ "execution(* org.osforce.connect.service.document.FileItemService.updateFileItem(..))")
	public void updateFile(JoinPoint jp) {
		FileItem fileItem = (FileItem) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("fileItemId", fileItem.getId());
		context.put("template", TEMPLATE_FILE_UPDATE);
		fileActivityStreamTask.doAsyncTask(context);
	}

	@AfterReturning("execution(* org.osforce.connect.service.document.FolderService.createFolder(..)) ||"
			+ "execution(* org.osforce.connect.service.document.FolderService.updateFolder(..))")
	public void updateFolder(JoinPoint jp) {
		Folder folder = (Folder) jp.getArgs()[0];
		Map<Object, Object> context = new HashMap<Object, Object>();
		context.put("folderId", folder.getId());
		context.put("template", TEMPLATE_FOLDER_UPDATE);
		folderActivityStreamTask.doAsyncTask(context);
	}

}
