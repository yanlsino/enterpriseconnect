package org.osforce.e2.web.module.profile.fragment;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.osforce.commons.custom.CustomForm;
import org.osforce.commons.custom.CustomFormUtil;
import org.osforce.e2.entity.commons.Link;
import org.osforce.e2.entity.commons.Statistic;
import org.osforce.e2.entity.commons.Template;
import org.osforce.e2.entity.profile.Profile;
import org.osforce.e2.entity.system.Project;
import org.osforce.e2.entity.system.ProjectCategory;
import org.osforce.e2.entity.system.Role;
import org.osforce.e2.entity.system.Site;
import org.osforce.e2.entity.system.User;
import org.osforce.e2.entity.team.TeamMember;
import org.osforce.e2.service.commons.LinkService;
import org.osforce.e2.service.commons.StatisticService;
import org.osforce.e2.service.commons.TemplateService;
import org.osforce.e2.service.profile.ProfileService;
import org.osforce.e2.service.system.ProjectCategoryService;
import org.osforce.e2.service.system.RoleService;
import org.osforce.e2.web.AttributeKeys;
import org.osforce.platform.dao.support.Page;
import org.osforce.platform.web.framework.annotation.Param;
import org.osforce.platform.web.framework.annotation.Pref;
import org.osforce.platform.web.framework.core.FragmentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 9:54:05 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Component
public class ProfileFragment {

	private LinkService linkService;
	private RoleService roleService;
	private ProfileService profileService;
	private StatisticService statisticService;
	private TemplateService templateService;
	private ProjectCategoryService projectCategoryService;
	
	public ProfileFragment() {
	}
	
	@Autowired
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	@Autowired
	public void setProjectCategoryService(
			ProjectCategoryService projectCategoryService) {
		this.projectCategoryService = projectCategoryService;
	}
	
	public String doActionsView(@Pref("50") Integer roleLevel, 
			User user, Project project, FragmentContext context) {
		if(user==null || project==null ||
				NumberUtils.compare(project.getEnteredBy().getId(), user.getId())==0) {
			return "commons/blank";
		}
		//
		Link link = linkService.getLink(user.getProject().getId(), 
				project.getId(), Project.class.getSimpleName());
		context.putRequestData(AttributeKeys.LINK_KEY_READABLE, link);
		//
		Role role = roleService.getRole(project.getCategoryId(), roleLevel);
		TeamMember member = new TeamMember();
		member.setRole(role);
		context.putSessionData(AttributeKeys.TEAM_MEMBER_KEY, member);
		return "profile/actions";
	}
	
	public String doInfoView() {
		return "profile/profile_info";
	}
	
	public String doTopView(@Pref String categoryCode, 
			Page<Statistic> page, Site site, FragmentContext context) {
		ProjectCategory category = projectCategoryService
				.getProjectCategory(site, categoryCode);
		page = statisticService.getTopStatisticPage(page, category, Profile.NAME);
		for(Statistic statistic : page.getResult()) {
			Object linkedEntity = profileService.getProfile(statistic.getLinkedId());
			statistic.setLinkedEntity(linkedEntity);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "profile/profiles_top";
	}
	
	public String doRecentView(@Pref String categoryCode, 
			Page<Profile> page, Site site, FragmentContext context) {
		ProjectCategory category = projectCategoryService
		.getProjectCategory(site, categoryCode);
		page = profileService.getProfilePage(page, category);
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "profile/profiles_recent";
	}
	
	public String doListView(@Pref String categoryCode, 
			@Param Long subCategoryId1, @Param String type, 
			Page<Profile> page, User user, Site site, FragmentContext context) {
		ProjectCategory category = projectCategoryService
				.getProjectCategory(site, categoryCode);
		if(StringUtils.equals("concerned", type)) {
			page = profileService.getConcernedProfilePage(page, category, user);
		} else if(StringUtils.equals("all", type)) {
			page = profileService.getProfilePage(page, category);
		} else if(user!=null) {
			page =  profileService.getConcernedProfilePage(page, category, user);
		} else {
			page =profileService.getProfilePage(page, category);
		}
		context.putRequestData(AttributeKeys.PAGE_KEY_READABLE, page);
		return "profile/profiles_list";
	}
	
	public String doProfileView(Site site, Project project, 
			User user, FragmentContext context) {
		Profile profile = profileService.viewProfile(project, user);
		if(profile==null) {
			profile = new Profile();
			profile.setTitle(project.getTitle());
			profile.setProject(project);
			profile.setEnteredBy(project.getEnteredBy());
			profile.setModifiedBy(project.getModifiedBy());
			profileService.createProfile(profile);
		}
		if(StringUtils.isNotBlank(profile.getAttributes())) {
			String templateCode = project.getCategory().getCode() + "-attributes";
			Template template = templateService.getTemplate(project.getCategoryId(), templateCode);
			CustomForm customForm = CustomFormUtil.parse(
					template.getContent(), profile.getAttributes());
			context.putRequestData(AttributeKeys.CUSTOM_ATTRIBUTES_KEY_READABLE, 
					customForm.getAllHasValues());
		}
		context.putRequestData(AttributeKeys.PROFILE_KEY_READABLE, profile);
		return "/profile/profile";
	}
	
	public String doFormView(@Param Long profileId, 
			User user, Site site, Project project, FragmentContext context) {
		Profile profile = profileService.getProfile(profileId);
		String templateCode = project.getCategory().getCode() + "-attributes";
		profile.setAttributesTemplateCode(templateCode);
		Template template = templateService.getTemplate(project.getCategoryId(), templateCode);
		CustomForm customForm = CustomFormUtil.parse(
				template.getContent(), profile.getAttributes());
		context.putRequestData(AttributeKeys.CUSTOM_ATTRIBUTES_KEY_READABLE, customForm.getAll());
		context.putRequestData(AttributeKeys.PROFILE_KEY_READABLE, profile);
		return "/profile/profile_form";
	}
}
