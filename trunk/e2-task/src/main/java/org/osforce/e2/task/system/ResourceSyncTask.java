package org.osforce.e2.task.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.xml.XMLUtil;
import org.osforce.e2.entity.system.Resource;
import org.osforce.e2.service.system.ResourceService;
import org.osforce.platform.task.support.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 16, 2011 - 4:23:39 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ResourceSyncTask extends AbstractTask
	implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	private ResourceService resourceService;
	
	public ResourceSyncTask() {
	}
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
		org.springframework.core.io.Resource resource = resourceLoader
				.getResource("/WEB-INF/resources.xml");
		Element resourcesEle = XMLUtil.parseToElement(resource.getFile());
		syncResources(resourcesEle);
	}
	
	protected void syncResources(Element resourcesEle) {
		List<Element> resourceEles = XMLUtil.getChildElements(resourcesEle, "resource");
		for(Element resourceEle : resourceEles) {
			String code = XMLUtil.getAttribute(resourceEle, "code");
			Resource resource = resourceService.getResource(code);
			if(resource==null) {
				resource = new Resource();
			}
			resource.setCode(code);
			resource.setName(XMLUtil.getAttribute(resourceEle, "name"));
			resource.setDescription(XMLUtil.getAttribute(resourceEle, "description"));
			Boolean enabled = StringUtils.equals(XMLUtil.getAttribute(resourceEle, "enabled"), "true");
			resource.setEnabled(enabled);
			resourceService.updateResource(resource);
		}
	}

}
