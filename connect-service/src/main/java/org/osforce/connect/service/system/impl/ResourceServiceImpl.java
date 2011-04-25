package org.osforce.connect.service.system.impl;

import java.util.List;

import org.osforce.connect.dao.system.ResourceDao;
import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.service.system.ResourceService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:03:30 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

	private ResourceDao resourceDao;

	public ResourceServiceImpl() {
	}

	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public Resource getResource(Long resourceId) {
		return resourceDao.get(resourceId);
	}

	public Resource getResource(String code) {
		QueryAppender appender = new QueryAppender()
				.equal("resource.code", code);
		return resourceDao.findUnique(appender);
	}

	public void createResource(Resource resource) {
		updateResource(resource);
	}

	public void updateResource(Resource resource) {
		if(resource.getId()==null) {
			resourceDao.save(resource);
		} else {
			resourceDao.update(resource);
		}
	}

	public void deleteResource(Long resourceId) {
		resourceDao.delete(resourceId);
	}

	public Page<Resource> getResourcePage(Page<Resource> page) {
		QueryAppender appender = new QueryAppender();
		appender.asc("resource.code");
		return resourceDao.findPage(page, appender);
	}

	public List<Resource> getResourceList() {
		QueryAppender appender = new QueryAppender();
		appender.asc("resource.code");
		return resourceDao.find(appender);
	}

	/**
	 * intercept by @code SystemAspect
	 */
	public void syncResource() {}
}
