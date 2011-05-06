package org.osforce.connect.dao.team.impl;

import org.osforce.connect.dao.team.MemberDao;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.platform.dao.support.AbstractDao;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 8:27:23 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Repository
public class MemberDaoImpl extends AbstractDao<TeamMember>
	implements MemberDao {

	public MemberDaoImpl() {
		super(TeamMember.class);
	}
}
