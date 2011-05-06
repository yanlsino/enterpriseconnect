package org.osforce.connect.dao.search;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 9:09:15 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class SearchBean {

	private String keywords;
	private Long categoryId;
	private Long siteId;
	
	public SearchBean() {
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	
}
