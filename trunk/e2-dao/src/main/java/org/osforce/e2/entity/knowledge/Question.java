package org.osforce.e2.entity.knowledge;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 22, 2011 - 8:53:53 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="questions")
@Cacheable
public class Question extends IdEntity {
	private static final long serialVersionUID = -6312216658696004801L;
	
	public static final String NAME = Question.class.getSimpleName();
	
	private String title;
	private String content;
	private Date entered;
	private Date modified;
	// helper
	private Long enteredId;
	private Long modifiedId;
	private Long projectId;
	private Long answerId;
	private Long views;
	// refer
	private User enteredBy;
	private User modifiedBy;
	private Project project;
	private Answer answer;
	private List<Answer> answers = Collections.emptyList();
	
	public Question() {
	}
	
	@Column(nullable=false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable=false)
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
	public Long getProjectId() {
		if(projectId==null && project!=null) {
			projectId = project.getId();
		}
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
	public Long getViews() {
		return views;
	}
	
	public void setViews(Long views) {
		this.views = views;
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
	@JoinColumn(name="project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="answer_id")
	public Answer getAnswer() {
		return answer;
	}
	
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="question")
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
