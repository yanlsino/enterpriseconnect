/**
 *
 */
package org.osforce.connect.entity.oauth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.osforce.connect.entity.system.User;
import org.osforce.platform.entity.support.IdEntity;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 3:29:41 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Entity
@Table(name="authorizations")
public class Authorization extends IdEntity {

	private static final long serialVersionUID = 1333210925870631235L;

	private String token;
	private String secret;
	private String target;
	// helper
	private Long userId;
	// refer
	private User user;

	public Authorization() {
	}

	public Authorization(String target, String token, String secret, User user) {
		this.target = target;
		this.token = token;
		this.secret = secret;
		this.user = user;
	}

	@Column(nullable=false)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(nullable=false)
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(nullable=false)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Transient
	public Long getUserId() {
		if(userId==null && user!=null) {
			userId = user.getId();
		}
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
