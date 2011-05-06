package org.osforce.connect.service.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ProjectCategoryDao;
import org.osforce.connect.dao.system.RoleDao;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.service.system.RoleService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:13:20 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao;
	private ProjectCategoryDao projectCategoryDao;
	
	public RoleServiceImpl() {
	}
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Autowired
	public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
		this.projectCategoryDao = projectCategoryDao;
	}

	public Role getRole(Long roleId) {
		return roleDao.get(roleId);
	}
	
	public Role getRole(Long categoryId, Integer roleLevel) {
		QueryAppender appender = new QueryAppender();
		appender.equal("role.category.id", categoryId)
				.lessThanOrEqual("role.level", roleLevel)
				.desc("role.level");
		List<Role> roles = roleDao.find(appender);
		return roles.get(0);
	}
	
	public Role getRole(String code, Long categoryId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("role.code", code).equal("role.category.id", categoryId);
		return roleDao.findUnique(appender);
	}

	public void createRole(Role role) {
		updateRole(role);
	}

	public void updateRole(Role role) {
		if(role.getCategoryId()!=null) {
			ProjectCategory category = projectCategoryDao.get(role.getCategoryId());
			role.setCategory(category);
		}
		if(role.getId()==null) {
			roleDao.save(role);
		} else {
			roleDao.update(role);
		}
	}

	public void delete(Long roleId) {
		roleDao.delete(roleId);
	}
	
	public Page<Role> getRolePage(Page<Role> page, Long siteId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("role.category.site.id", siteId).asc("role.level");
		return roleDao.findPage(page, appender);
	}
	
	public List<Role> getRoleList(Long categoryId) {
		QueryAppender appender = new QueryAppender();
		appender.equal("role.category.id", categoryId).asc("role.level");
		return roleDao.find(appender);
	}
	
	public List<Role> getRoleList(Site site) {
		QueryAppender appender = new QueryAppender()
				.equal("role.category.site.id", site.getId());
		return roleDao.find(appender);
	}
}
