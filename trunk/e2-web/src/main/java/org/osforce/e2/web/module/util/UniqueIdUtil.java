package org.osforce.e2.web.module.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 3:27:50 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public abstract class UniqueIdUtil {

	public static String generateByEmail(String email) {
		email = StringUtils.lowerCase(email);
		String uniqueId = StringUtils.substringBefore(email, "@");
		StringBuffer buffer = new StringBuffer();
		for(char c : uniqueId.toCharArray()) {
			if((c>='0' && c<='9') || (c>='a' && c<='z') || c=='_' || c=='-') {
				buffer.append(c);
			} else {
				buffer.append('-');
			}
		}
		return buffer.toString();
	}
	
}
