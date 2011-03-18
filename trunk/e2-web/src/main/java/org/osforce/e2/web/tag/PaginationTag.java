package org.osforce.e2.web.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 23, 2011 - 9:42:27 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class PaginationTag extends TagSupport {
	private static final long serialVersionUID = -8376141363325628092L;

	private Page<Object> page;
	private String link;
	
	public PaginationTag() {
	}
	
	public void setPage(Page<Object> page) {
		this.page = page;
	}
	
	public Page<Object> getPage() {
		return page;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}
	
	@Override
	public int doEndTag() throws JspException {
		@SuppressWarnings("unchecked")
		Page<Object> page = (Page<Object>) pageContext.getRequest().getAttribute(
				AttributeKeys.PAGE_KEY_READABLE);
		Writer out = pageContext.getOut();
		try {
			out.append("<div id=\"osf_pagination\">");
			if(page.isHasPre()) {
				out.append("<a href="+link+1+">首页</a>");
				out.append("<a href="+link+page.getPrePage()+">上一页</a>");
			} else {
				out.append("<span class=\"disabled_osf_pagination\">首页</span>");
				out.append("<span class=\"disabled_osf_pagination\">上一页</span>");
			}
			int pageStart = 1;
			int pageEnd = (int) page.getTotalPages();
			if((page.getPageNo()-3)>1) {
				pageStart = page.getPageNo() - 3;
			}
			if(page.getPageNo() + 3 <= page.getTotalPages()) {
				pageEnd = page.getPageNo() + 3;
			}
			for(int pageNo=pageStart; pageNo<=pageEnd; pageNo++) {
				if(pageNo==page.getPageNo()) {
					out.append("<span class=\"active_osf_link\">"+pageNo+"</span>");
				} else {
					out.append("<a href="+link+pageNo+">"+pageNo+"</a>");
				}
			}
			if(page.isHasNext()) {
				out.append("<a href="+link+page.getNextPage()+">下一页</a>");
				out.append("<a href="+link+page.getTotalPages()+">尾页</a>");
			} else {
				out.append("<span class=\"disabled_osf_pagination\">下一页</span>");
				out.append("<span class=\"disabled_osf_pagination\">尾页</span>");
			}
			out.append("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
}
