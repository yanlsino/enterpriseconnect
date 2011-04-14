package org.osforce.connect.web.module.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.system.ProjectFeature;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 11, 2011 - 2:41:38 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public abstract class ModuleUtil {
	
	public static List<ProjectFeature> parseToModules(String featuresStr) {
		List<ProjectFeature> modules = new ArrayList<ProjectFeature>();
		String[] featureStrs = StringUtils.split(featuresStr, "\r\n");
		for(String featureStr : featureStrs) {
			if(StringUtils.isNotBlank(featureStr)) {
				ProjectFeature module = new ProjectFeature();
				featureStr = StringUtils.substringBetween(featureStr, "[", "]");
				String[] strs = StringUtils.split(featureStr, ","); 
				for(String str : strs) {
					String key = StringUtils.substringBefore(str, "=");
					String value = StringUtils.substringAfter(str, "=");
					if(StringUtils.equals("label", key)) {
						module.setLabel(value);
					} else if(StringUtils.equals("code", key)) {
						module.setCode(value);
					} else if(StringUtils.equals("show", key)) {
						module.setShow(StringUtils.equals("true", value));
					} else if(StringUtils.equals("role", key)) {
						module.setRoleCode(value);
					}
				}
				modules.add(module);
			}
		}
		return modules;
	}
	
	public static void main(String[] args) {
		String str = "[label=个人主页,code=profile,show=true,role=guest]\r\n[label=日历,code=calendar,show=true,role=member]";
		List<ProjectFeature> features = parseToModules(str);
		for(ProjectFeature feature : features) {
			System.out.println(feature.getLabel() + " - " + feature.getCode() + " - " + feature.getRoleCode());
		}
	}
}
