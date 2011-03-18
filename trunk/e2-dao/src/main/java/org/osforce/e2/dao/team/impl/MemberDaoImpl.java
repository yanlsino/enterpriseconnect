package org.osforce.e2.dao.team.impl;

import org.osforce.e2.dao.team.MemberDao;
import org.osforce.e2.entity.team.TeamMember;
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
