package org.osforce.connect.web.tag;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class TextRenderTag extends TagSupport {
	private static final long serialVersionUID = 3841163609059895187L;
	
	private String facesBase;
	private String text;
	
	public TextRenderTag() {
	}
	
	public String getFacesBase() {
		return facesBase;
	}
	
	public void setFacesBase(String facesBase) {
		this.facesBase = facesBase;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public int doEndTag() throws JspException {
		parse();
		try {
			pageContext.getOut().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	
	private void parse() {
		parseFace();
		parseImage();
		parseLink();
		parseSpecialChar();
	}
	
	static final Pattern facePattern = Pattern.compile("\\[face:\\s*(.+?)\\s*\\]");
	public void parseFace() {
		// faces
		String tmp = text;
		Matcher faceMatcher = facePattern.matcher(tmp);
		while(faceMatcher.find()) {
			String face = faceMatcher.group(1);
			String faceBlock = StringUtils.substring(text, faceMatcher.start(), faceMatcher.end());
			tmp = StringUtils.replaceOnce(tmp, faceBlock, "<img src=\""+facesBase+"/face-"+face+".png\"/>");
		}
		text = tmp;
	}
	
	static final Pattern imgPattern = Pattern.compile("\\[img:\\s*(.+?)\\s*\\]");
	private void parseImage() {
		String tmp = text;
		Matcher imgMatcher = imgPattern.matcher(tmp);
		while(imgMatcher.find()) {
			String imgUrl = imgMatcher.group(1);
			String imgBlock = StringUtils.substring(text, imgMatcher.start(), imgMatcher.end());
			tmp = StringUtils.replaceOnce(tmp, imgBlock, "<img src=\""+ imgUrl +"\">");
		}
		text = tmp;
	}
	
	static final Pattern linkPattern = Pattern.compile("\\[link:\\s*(.+?)\\s*\\]");
	private void parseLink() {
		String tmp = text;
		Matcher linkMatcher = linkPattern.matcher(tmp);
		while(linkMatcher.find()) {
			String linkUrl = linkMatcher.group(1);
			String linkBlock = StringUtils.substring(text, linkMatcher.start(), linkMatcher.end());
			tmp = StringUtils.replaceOnce(tmp, linkBlock, "<a href=\""+ linkUrl +"\" target=\"_blank\">"+linkUrl+"</a>");
		}
		text = tmp;
	}
	
	private void parseSpecialChar() {
		StringUtils.replace(text, "\n", "<br/>");
	}
	
}
