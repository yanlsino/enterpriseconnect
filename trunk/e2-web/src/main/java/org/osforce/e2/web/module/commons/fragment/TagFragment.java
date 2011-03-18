package org.osforce.e2.web.module.commons.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.commons.castor.CastorUtil;
import org.osforce.e2.entity.blog.BlogPost;
import org.osforce.e2.entity.commons.Tag;
import org.osforce.e2.entity.discussion.Topic;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.service.blog.BlogPostService;
import org.osforce.e2.service.commons.TagService;
import org.osforce.e2.service.discussion.TopicService;
import org.osforce.e2.service.profile.ProfileService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 9, 2011 - 10:30:49 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class TagFragment {

	private TagService tagService;
	private TopicService topicService;
	private ProfileService profileService;
	private BlogPostService blogPostService;
	
	public TagFragment() {
	}
	
	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
	public void setBlogPostService(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}
	
	public String doCloudView(Page<Tag> page, FragmentContext context) {
		page = tagService.getTagPage(page);
		if(page.getResult().isEmpty()) {
			return "commons/blank";
		}
		for(Tag tag : page.getResult()) {
			Object linkedEntity = null;
			if(StringUtils.equals(Profile.NAME, tag.getEntity())) {
				linkedEntity = profileService.getProfile(tag.getLinkedId());
			} else if(StringUtils.equals(Topic.NAME, tag.getEntity())) {
				linkedEntity = topicService.getTopic(tag.getLinkedId());
			} else if(StringUtils.equals(BlogPost.NAME, tag.getEntity())) {
				linkedEntity = blogPostService.getBlogPost(tag.getLinkedId());
			}
			tag.setLinkedEntity(linkedEntity);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "commons/tags_cloud";
	}
	
	public String doFormView(@Pref String paramName, 
			@Pref String entity, Project project, FragmentContext context) {
		String paramValue = context.getParameterData(paramName);
		Long linkedId = CastorUtil.castTo(paramValue, Long.class);
		if(linkedId==null) {
			if(StringUtils.equals(entity, Profile.NAME) && project!=null) {
				linkedId = project.getProfileId();
			}
		}
		if(linkedId!=null && StringUtils.isNotBlank(entity)) {
			List<Tag> tagList = tagService.getTagList(linkedId, entity);
			List<String> tagNameList = new ArrayList<String>();
			for(Tag tag : tagList) {
				tagNameList.add("'"+tag.getName()+"'");
			}
			context.putRequestData("tags", StringUtils.join(tagNameList, ","));
			context.putRequestData("linkedId", linkedId);
			context.putRequestData("entity", entity);
			return "commons/tag_form";
		}
		return "commons/blank";
	}
}
