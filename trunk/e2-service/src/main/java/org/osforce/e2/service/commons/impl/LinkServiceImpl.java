package org.osforce.e2.service.commons.impl;

import org.osforce.e2.dao.commons.LinkDao;
import org.osforce.e2.dao.system.ProjectDao;
import org.osforce.e2.entity.commons.Link;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.service.commons.LinkService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.dao.support.QueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:15:57 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

	private LinkDao linkDao;
	private ProjectDao projectDao;
	
	public LinkServiceImpl() {
	}
	
	@Autowired
	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public Link getLink(Long linkId) {
		return linkDao.get(linkId);
	}
	
	public Link getLink(Long fromId, Long toId, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("link.from.id", fromId).equal("link.toId", toId)
			.equal("link.entity", entity);
		return linkDao.findUnique(appender);
	}
	
	public void createLink(Link link) {
		updateLink(link);
	}

	public void updateLink(Link link) {
		if(link.getFromId()!=null) {
			Project from = projectDao.get(link.getFromId());
			link.setFrom(from);
		}
		if(link.getId()==null) {
			linkDao.save(link);
		} else {
			linkDao.update(link);
		}
	}

	public void deleteLink(Long linkId) {
		linkDao.delete(linkId);
	}

	public Page<Link> getLinkFromPage(Page<Link> page, Long fromId, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("link.from.id", fromId).equal("link.entity", entity);
		return linkDao.findPage(page, appender);
	}
	
	public Page<Link> getLinkToPage(Page<Link> page, Long toId, String entity) {
		QueryAppender appender = new QueryAppender();
		appender.equal("link.toId", toId).equal("link.entity", entity);
		return linkDao.findPage(page, appender);
	}
}
