package org.osforce.connect.dao.system.impl;

import org.osforce.connect.dao.system.MailSettingsDao;
import org.osforce.connect.entity.system.MailSettings;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 3, 2011 - 4:48:15 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MailSettingsDaoImpl extends AbstractDao<MailSettings> 
	implements MailSettingsDao {

	public MailSettingsDaoImpl() {
		super(MailSettings.class);
	}
	
}
