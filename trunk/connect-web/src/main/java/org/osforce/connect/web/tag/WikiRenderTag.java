package org.osforce.connect.web.tag;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.wikimodel.wem.IWemListener;
import org.wikimodel.wem.IWikiParser;
import org.wikimodel.wem.WikiParserException;
import org.wikimodel.wem.WikiPrinter;
import org.wikimodel.wem.mediawiki.MediaWikiParser;
import org.wikimodel.wem.xhtml.PrintListener;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 3:55:30 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class WikiRenderTag extends TagSupport {
	private static final long serialVersionUID = -3631195862047765162L;

	private String text;
	
	public WikiRenderTag() {
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public int doEndTag() throws JspException {
		if(StringUtils.isNotBlank(text)) {
			try {
				pageContext.getOut().write(render(text));
			} catch (WikiParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return EVAL_PAGE;
	}
	
	public String render(String text) throws WikiParserException {
		StringBuffer buffer = new StringBuffer();
		IWikiParser parser = new MediaWikiParser();
		IWemListener listener = new PrintListener(new WikiPrinter(buffer));
		parser.parse(new StringReader(text), listener);
		return buffer.toString();
	}
	
}
