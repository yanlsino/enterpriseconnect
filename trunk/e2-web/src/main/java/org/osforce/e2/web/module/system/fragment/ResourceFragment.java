package org.osforce.e2.web.module.system.fragment;

import org.osforce.e2.entity.system.Resource;
import org.osforce.e2.service.system.ResourceService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 25, 2011 - 11:06:10 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ResourceFragment {

	private ResourceService resourceService;
	
	public ResourceFragment() {
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	public String doListView(Page<Resource> page, FragmentContext context) {
		page = resourceService.getResourcePage(page);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "system/resources_list";
	}
	
	public String doFormView(@Param Long resourceId, FragmentContext context) {
		Resource resource = new Resource();
		if(resourceId!=null) {
			resource = resourceService.getResource(resourceId);
		}
		context.putRequestData(AttributeKeys.RESOURCE_KEY_READABLE, resource);
		return "system/resource_form";
	}
	
}
