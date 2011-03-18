package org.osforce.e2.web.custom.urlrewrite;

import org.osforce.e2.entity.system.Site;
import org.osforce.e2.service.system.SiteService;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.urlrewrite.UrlRewriterConfLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.Condition;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.NormalRule;
import org.tuckey.web.filters.urlrewrite.RuleBase;
import org.tuckey.web.filters.urlrewrite.SetAttribute;

/**
 * Load extra url rewiter confuration from else place.
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 7:03:28 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class ExtraUrlRewriterConfLoader implements UrlRewriterConfLoader {

	private SiteService siteService;
	
	public ExtraUrlRewriterConfLoader() {
	}
	
	@Autowired
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}
	
	@SuppressWarnings("unchecked")
	public void loadUrlRewriterConf(Conf conf) {
		int pageNo = 0;
		Page<Site> page = new Page<Site>(100);
		do {
			pageNo++;
			page.setPageNo(pageNo);
			page = siteService.getSitePage(page);
			for(Site site : page.getResult()) {
				NormalRule rule = new NormalRule();
				rule.setEnabled(site.getEnabled());
				rule.setMatchType(RuleBase.DEFAULT_MATCH_TYPE);
				// condition
				Condition condition = new Condition();
				condition.setName("host");
				condition.setOperator("equal");
				condition.setValue(site.getDomain());
				rule.addCondition(condition);
				// set 
				SetAttribute setAttribute = new SetAttribute();
				setAttribute.setName("siteId");
				setAttribute.setValue(String.valueOf(site.getId()));
				rule.addSetAttribute(setAttribute);
				// add rule
				conf.getRules().add(0, rule);
				//conf.addRule(rule);
			}
		} while(page.isHasNext());
	}

}
