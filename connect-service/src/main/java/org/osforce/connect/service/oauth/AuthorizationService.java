/**
 *
 */
package org.osforce.connect.service.oauth;

import org.osforce.connect.entity.oauth.Authorization;

/**
 * @author gavin
 * @since 1.0.3
 * @create May 10, 2011 - 3:58:46 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface AuthorizationService {

	Authorization getAuthorization(Long authorizationId);

	Authorization getAuthorization(String target, Long userId);

	void createAuthorization(Authorization authorization);

	void updateAuthorization(Authorization authorization);

	void deleteAuthorization(Long authorizationId);

}
