package org.osforce.e2.web.module.commons.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.osforce.commons.date.DateUtil;
import org.osforce.commons.image.ImageUtil;
import org.osforce.commons.lang.StringUtil;
import org.osforce.e2.entity.commons.Attachment;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 21, 2011 - 10:01:01 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public final class AttachmentUtil {
	
	private static final String basePath = SystemUtils.getUserHome() + "/.e2/attachments";
	
	public static void read(Attachment attachment) throws IOException {
		File targetFile = getTargetFile(attachment);
		if(StringUtils.isNotBlank(attachment.getDimension())) {
			File thumnailFile = getThumnailFile(attachment);
			if(!thumnailFile.exists()) {
				ImageUtil.resize(targetFile, thumnailFile, attachment.getDimension());
			}
			targetFile = thumnailFile;
		}
		attachment.setBytes(FileUtils.readFileToByteArray(targetFile));
	}
	
	public static void write(Attachment attachment) throws IOException {
		File targetFile = getTargetFile(attachment);
		FileUtils.writeByteArrayToFile(targetFile, attachment.getBytes());
	}
	
	private static File getThumnailFile(Attachment attachment) {
		String path = StringUtil.buildPath(basePath, DateUtil.format(attachment.getEntered(), "yyyy"),
				DateUtil.format(attachment.getEntered(), "MMdd"));
		String name = attachment.getName() + "_" + attachment.getId() + "." +attachment.getDimension();
		return new File(path, name);
	}
	
	private static File getTargetFile(Attachment attachment) {
		String path = StringUtil.buildPath(basePath, DateUtil.format(attachment.getEntered(), "yyyy"),
				DateUtil.format(attachment.getEntered(), "MMdd"));
		String name =  attachment.getName() + "_" + attachment.getId() + attachment.getSuffix();
		return new File(path, name);
	}
	
}
