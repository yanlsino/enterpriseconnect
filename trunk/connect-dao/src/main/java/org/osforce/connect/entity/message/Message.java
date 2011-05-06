package org.osforce.connect.entity.message;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.osforce.commons.lang.StringUtil;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 16, 2010 - 4:21:08 PM <a
 *         href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name = "messages")
@Cacheable
public class Message extends IdEntity {

	private static final long serialVersionUID = 4017023602812569994L;

	private String subject;
	private String content;
	private Date entered;
	private Boolean read = false;
	// helper
	private Long fromId;
	private Long toId;
	private Long enteredId;
	private Boolean reply = false;
	// refer
	private Project from;
	private Project to;
	private User enteredBy;
	private User readBy;

	public Message() {
	}

	@Column(nullable=false)
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Lob@Column(nullable=false)
	@Type(type="org.hibernate.type.StringClobType")
	public String getContent() {
		return content;
	}
	
	@Transient
	public String getShortContent() {
		return StringUtil.substringMaxLength(content, 100);
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getEntered() {
		return entered;
	}
	
	public void setEntered(Date entered) {
		this.entered = entered;
	}

	public Boolean getRead() {
		return read;
	}
	
	public void setRead(Boolean read) {
		this.read = read;
	}
	
	@Transient
	public Long getFromId() {
		if(fromId==null && from!=null) {
			fromId = from.getId();
		}
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	@Transient
	public Long getToId() {
		if(toId==null && to!=null) {
			toId = to.getId();
		}
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
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
	public Boolean getReply() {
		return reply;
	}
	
	public void setReply(Boolean reply) {
		this.reply = reply;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="from_id")
	public Project getFrom() {
		return from;
	}

	public void setFrom(Project from) {
		this.from = from;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="to_id")
	public Project getTo() {
		return to;
	}
	
	public void setTo(Project to) {
		this.to = to;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="entered_by_id")
	public User getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(User enteredBy) {
		this.enteredBy = enteredBy;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="read_by_id")
	public User getReadBy() {
		return readBy;
	}

	public void setReadBy(User readBy) {
		this.readBy = readBy;
	}
	
}
