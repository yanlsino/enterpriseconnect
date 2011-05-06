package org.osforce.connect.entity.discussion;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Nov 13, 2010 - 11:17:44 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="topics")
@Cacheable
public class Topic extends IdEntity {
	private static final long serialVersionUID = -7618558296116783870L;
	
	public static final String NAME = Topic.class.getSimpleName();
	
	private String subject;
	private String content;
	private Date entered;
	private Date modified;
	private Boolean enabled = true;
	private Boolean question = false;
	// helper
	private Long forumId;
	private Long categoryId;
	private Long  answerId;
	private Long enteredId;
	private Long modifiedId;
	private Long views = 0L;
	// refer
	private Forum forum;
	private TopicCategory category;
	private Reply answer;
	private User enteredBy;
	private User modifiedBy;
	
	public Topic() {
	}

	@Column(nullable=false)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(nullable=false)
	public String getContent() {
		return content;
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

	@Column(nullable=false)
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
	

	public Boolean getQuestion() {
		return question;
	}
	
	public void setQuestion(Boolean question) {
		this.question = question;
	}

	@Transient
	public Long getForumId() {
		if(forumId==null && forum!=null) {
			forumId = forum.getId();
		}
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	@Transient
	public Long getCategoryId() {
		if(categoryId==null && category!=null) {
			categoryId = category.getId();
		}
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Transient
	public Long getAnswerId() {
		if(answerId==null && answer!=null) {
			answerId = answer.getId();
		}
		return answerId;
	}
	
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
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
	public Long getViews() {
		return views;
	}
	
	public void setViews(Long views) {
		this.views = views;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="forum_id")
	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	public TopicCategory getCategory() {
		return category;
	}

	public void setCategory(TopicCategory category) {
		this.category = category;
	}

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
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
	@JoinColumn(name="answer_id")
	public Reply getAnswer() {
		return answer;
	}
	
	public void setAnswer(Reply answer) {
		this.answer = answer;
	}
	
}
