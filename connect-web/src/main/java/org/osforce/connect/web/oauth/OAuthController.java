package org.osforce.connect.web.oauth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.osforce.connect.entity.oauth.Authorization;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.service.oauth.AuthorizationService;
import org.osforce.connect.web.AttributeKeys;
import org.osforce.platform.social.api.service.ApiService;
import org.osforce.platform.web.framework.annotation.Param;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author gavin
 * @since 1.0.3
 * @create May 9, 2011 - 9:03:14 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("oauth")
public class OAuthController implements ApplicationContextAware {

	private Map<Token, OAuthService> oAuthServices = new HashMap<Token, OAuthService>();
	private Map<String, Token> requestTokens = new HashMap<String, Token>();

	private ApplicationContext appContext;
	private AuthorizationService authorizationService;

	public OAuthController() {
	}

	@Autowired
	public void setAuthorizationService(
			AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
	}

	@RequestMapping(value="authorized", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> isAuthorized(
    		@Param String target, HttpSession session) {
		User user = (User) session.getAttribute(AttributeKeys.USER_KEY);
		Authorization authorization = authorizationService.getAuthorization(target, user.getId());
		//
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("authorized", authorization!=null);
    	return model;
    }

	@RequestMapping(value="authorizationUrl", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAuthUrl(
			@Param String target, WebRequest request) {
		Site site = (Site) request.getAttribute(AttributeKeys.SITE_KEY, WebRequest.SCOPE_REQUEST);
		String callback = site.getHomeURL()+ "/process/oauth/callback/" + target;
		String beanId = target + ApiService.class.getSimpleName();
		ApiService apiService = appContext.getBean(beanId, ApiService.class);
		OAuthService oAuthService = apiService.getOAuthService(callback);
		Token requestToken = oAuthService.getRequestToken();
		oAuthServices.put(requestToken, oAuthService);
		requestTokens.put(requestToken.getToken(), requestToken);
		String authUrl = oAuthService.getAuthorizationUrl(requestToken);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("authUrl", authUrl);
		return model;
	}

	@RequestMapping(value="callback/{target}")
	public ResponseEntity<String> callback(@PathVariable String target,
			@Param String oauth_token, @Param String oauth_verifier, HttpSession session) {
		User user = (User) session.getAttribute(AttributeKeys.USER_KEY);
		Token requestToken = requestTokens.get(oauth_token);
		Verifier verifier = new Verifier(oauth_verifier);
		OAuthService oAuthService = oAuthServices.get(requestToken);
		Token accessToken = oAuthService.getAccessToken(requestToken, verifier);
		Authorization authorization = new Authorization(
										target,
										accessToken.getToken(),
										accessToken.getSecret(),
										user);
		authorizationService.createAuthorization(authorization);
		requestTokens.remove(oauth_token);
		oAuthServices.remove(requestToken);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_HTML) ;
		return new ResponseEntity<String>(
				"<script type=\"text/javascript\">window.close();</script>",
				responseHeaders, HttpStatus.OK);
	}

}
