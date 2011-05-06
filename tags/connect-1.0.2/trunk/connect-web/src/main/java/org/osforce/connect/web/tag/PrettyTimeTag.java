package org.osforce.connect.web.tag;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.support.RequestContextUtils;

import com.ocpsoft.pretty.time.PrettyTime;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 28, 2011 - 6:00:05 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class PrettyTimeTag extends TagSupport {
	private static final long serialVersionUID = 7460803074894353195L;

	private Date date;
	
	public PrettyTimeTag() {
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public int doEndTag() throws JspException {
		Locale locale = RequestContextUtils.getLocale((HttpServletRequest)pageContext.getRequest());
		PrettyTime prettyTime = new PrettyTime(locale);
		try {
			pageContext.getOut().write(prettyTime.format(date));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
