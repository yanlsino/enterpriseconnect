package org.osforce.connect.dao.commons.impl;

import org.osforce.connect.dao.commons.TemplateDao;
import org.osforce.connect.entity.commons.Template;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 10:59:36 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class TemplateDaoImpl extends AbstractDao<Template>  
	implements TemplateDao {

	public TemplateDaoImpl() {
		super(Template.class);
	}
}
