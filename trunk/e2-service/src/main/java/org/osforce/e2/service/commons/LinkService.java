package org.osforce.e2.service.commons;

import org.osforce.e2.entity.commons.Link;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 22, 2011 - 2:14:34 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface LinkService {

	Link getLink(Long linkId);
	
	Link getLink(Long fromId, Long toId, String entity);
	
	void createLink(Link link);
	
	void updateLink(Link link);
	
	void deleteLink(Long linkId);

	Page<Link> getLinkFromPage(Page<Link> page, Long id, String entity);

	Page<Link> getLinkToPage(Page<Link> page, Long id, String entity);

}
