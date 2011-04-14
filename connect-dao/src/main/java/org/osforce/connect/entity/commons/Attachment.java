package org.osforce.connect.entity.commons;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.osforce.platform.entity.support.IdEntity;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 23, 2011 - 4:33:49 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="attachments")
@Cacheable
public class Attachment extends IdEntity {
	
	private static final long serialVersionUID = -3205724773878038583L;
	
	private String fileName;
	private String contentType;
	private Long size = 0L;
	private Date entered;
	// helper
	private byte[] bytes;
	private String dimension;
	
	public Attachment() {
	}

	@Column(nullable=false)
	public String getFileName() {
		return fileName;
	}
	
	@Transient
	public String getName() {
		return StringUtils.substringBefore(fileName, ".");
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(nullable=false)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	public Date getEntered() {
		return entered;
	}
	
	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
	@Transient
	public String getSuffix() {
		return "."+StringUtils.substringAfterLast(fileName, ".");
	}
	
	@Transient
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	@Transient
	public String getDimension() {
		return dimension;
	}
	
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
}
