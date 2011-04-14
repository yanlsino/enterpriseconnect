package org.osforce.connect.entity.document;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 10, 2010 - 3:48:59 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="file_items")
@Cacheable
public class FileItem extends IdEntity {

	private static final long serialVersionUID = -4517309340701439012L;

	private String displayName;
	private Date entered;
	private Date modified;
	private Boolean enabled;
	// helper
	private Long modifiedId;
	private Long enteredId;
	private Long folderId;
	private Long views;
	private Long realFileId;
	// refer
	private User enteredBy;
	private User modifiedBy;
	private Folder folder;
	private Attachment realFile;
	
	public FileItem() {
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getEntered() {
		return entered;
	}
	
	public void setEntered(Date entered) {
		this.entered = entered;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getModified() {
		return modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public Long getEnteredId() {
		if(enteredId==null && enteredBy!=null) {
			enteredId = enteredBy.getId();
		}
		return enteredId;
	}

	public void setEnteredId(Long enteredId) {
		this.enteredId = enteredId;
	}

	@Transient
	public Long getModifiedId() {
		if(modifiedId==null && modifiedBy!=null) {
			modifiedId = modifiedBy.getId();
		}
		return modifiedId;
	}
	
	public void setModifiedId(Long modifiedId) {
		this.modifiedId = modifiedId;
	}
	
	@Transient
	public Long getFolderId() {
		if(folderId==null && folder!=null) {
			folderId = folder.getId();
		}
		return folderId;
	}
	
	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}
	
	@Transient
	public Long getViews() {
		return views;
	}
	
	public void setViews(Long views) {
		this.views = views;
	}
	
	@Transient
	public Long getRealFileId() {
		if(realFileId==null && realFile!=null) {
			realFileId = realFile.getId();
		}
		return realFileId;
	}
	
	public void setRealFileId(Long realFileId) {
		this.realFileId = realFileId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}
	
	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="modified_by_id")
	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="folder_id")
	public Folder getFolder() {
		return folder;
	}
	
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="real_file_id")
	public Attachment getRealFile() {
		return realFile;
	}
	
	public void setRealFile(Attachment realFile) {
		this.realFile = realFile;
	}
	
}
