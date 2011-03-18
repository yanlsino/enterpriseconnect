package org.osforce.e2.web.module.commons.controller;

import java.util.Collections;
import java.util.Map;

import org.osforce.e2.entity.commons.Tag;
import org.osforce.e2.service.commons.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 9:24:52 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/commons")
public class TagController {

	private TagService tagService;
	
	public TagController() {
	}
	
	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	@RequestMapping(value="/tag", method=RequestMethod.POST)
	public @ResponseBody Map<String, Long> update(Tag tag) {
		Tag t = tagService.getTag(tag.getName(), tag.getLinkedId(), tag.getEntity());
		if(t==null) {
			tagService.createTag(tag);
		}
		return Collections.singletonMap("id", tag.getId());
	}
	
	@RequestMapping(value="/tag", method=RequestMethod.GET)
	public void delete(Tag tag) {
		tagService.deleteTag(tag.getName(), tag.getLinkedId(), tag.getEntity());
	}
	
	/*@RequestMapping(value="/tags", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> list() {
		List<Map<String, Object>> tagList = new ArrayList<Map<String, Object>>();
		Map<String, Object> model = new HashMap<String, Object>();
		return tagList;
	}*/
	
}
