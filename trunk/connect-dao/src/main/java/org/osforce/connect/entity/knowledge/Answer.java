package org.osforce.connect.entity.knowledge;

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
import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 8:58:50 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="answers")
@Cacheable
public class Answer extends IdEntity {
	private static final long serialVersionUID = 497817015598556275L;

	public static final String NAME = Answer.class.getSimpleName();

	private String content;
	private Date entered;
	private Date modified;
	// helper
	private Long enteredId;
	private Long modifiedId;
	private Long questionId;
	// refer
	private User enteredBy;
	private User modifiedBy;
	private Question question;

	public Answer() {
	}

	@Lob@Column(nullable=false)
	@Type(type="org.hibernate.type.StringClobType")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	public Long getQuestionId() {
		if(questionId==null && question!=null) {
			questionId = question.getId();
		}
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="question_id")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
