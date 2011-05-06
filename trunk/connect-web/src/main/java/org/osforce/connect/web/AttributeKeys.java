package org.osforce.connect.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.lucene.messages.Message;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.osforce.connect.dao.search.SearchBean;
import org.osforce.connect.entity.blog.BlogCategory;
import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.commons.Attachment;
import org.osforce.connect.entity.commons.Link;
import org.osforce.connect.entity.commons.Template;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.system.MailSettings;
import org.osforce.connect.entity.system.Permission;
import org.osforce.connect.entity.system.Project;
import org.osforce.connect.entity.system.ProjectCategory;
import org.osforce.connect.entity.system.ProjectFeature;
import org.osforce.connect.entity.system.Resource;
import org.osforce.connect.entity.system.Role;
import org.osforce.connect.entity.system.Site;
import org.osforce.connect.entity.system.SiteLink;
import org.osforce.connect.entity.system.Theme;
import org.osforce.connect.entity.system.User;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.platform.dao.support.Page;

import freemarker.core.Comment;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 6:56:03 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public  abstract  class AttributeKeys {

	public static final String BASE_KEY_READABLE = "base";
	public static final String FEATURE_CODE_KEY_READABLE = "featureCode";
	public static final String CUSTOM_ATTRIBUTES_KEY_READABLE = "customAttributes";
	public static final String TOTAL_COUNT_KEY_READABLE = "totalCount";
	public static final String PARENT_KEY_READABLE = "parent";
	public static final String CHILDREN_KEY_READABLE = "children";
	
	public static final String SEARCH_BEAN_KEY = "_" + SearchBean.class.getName();
	public static final String SEARCH_BEAN_KEY_READABLE = "searchBean";
	
	public static final String SITE_KEY = "_" + Site.class.getName();
	public static final String SITE_KEY_READABLE = "site";
	
	public static final String THEME_KEY = "_" + Theme.class.getName();
	public static final String THEME_KEY_READABLE = "theme";
	public static final String THEME_LIST_KEY_READABLE = "themes";
	
	public static final String PAGE_KEY = "_" + Page.class.getName();
	public static final String PAGE_KEY_READABLE = "page";
	
	public static final String PROJECT_KEY = "_" + Project.class.getName();
	public static final String PROJECT_KEY_READABLE = "project";
	
	public static final String PROJECT_FEATURE_KEY = "_" + ProjectFeature.class.getName();
	public static final String PROJECT_FEATURE_KEY_READABLE = "feature";
	public static final String PROJECT_FEATURE_LIST_KEY_READABLE = "features";
	
	public static final String PROFILE_KEY = "_" + Profile.class.getName();
	public static final String PROFILE_KEY_READABLE = "profile";
	
	public static final String PROJECT_CATEGORY_KEY = "_" + ProjectCategory.class.getName();
	public static final String PROJECT_CATEGORY_KEY_READABLE = "category";
	public static final String PROJECT_CATEGORY_LIST_KEY_READABLE = "categories";
	
	public static final String MAIL_SETTINGS_KEY = "_" + MailSettings.class.getName();
	public static final String MAIL_SETTINGS_KEY_READABLE = "mailSettings";
	public static final String MAIL_SETTINGS_LIST_KEY_READABLE = "mailSettingsList";
	
	public static final String USER_KEY = "_" + User.class.getName();
	public static final String USER_KEY_READABLE = "user";
	
	public static final String ROLE_KEY = "_" + Role.class.getName();
	public static final String ROLE_KEY_READABLE = "role";
	public static final String ROLE_LIST_KEY_READABLE = "roles";
	
	public static final String SITE_LINK_KEY = "_" + SiteLink.class.getName();
	public static final String SITE_LINK_KEY_READABLE = "siteLink";
	public static final String SITE_LINK_LIST_KEY_READABLE = "siteLinks";
	
	public static final String PERMISSION_KEY = "_" + Permission.class.getName();
	public static final String PERMISSION_KEY_READABLE = "permission";
	public static final String PERMISSION_LIST_KEY_READABLE = "permissions";
	
	public static final String RESOURCE_KEY = "_" + Resource.class.getName();
	public static final String RESOURCE_KEY_READABLE = "resource";
	public static final String RESOURCE_LIST_KEY_READABLE = "resources";
	
	public static final String TEMPLATE_KEY = "_" + Template.class.getName(); 
	public static final String TEMPLATE_KEY_READABLE = "template";
	
	public static final String EVENT_KEY = "_" + Event.class.getName();
	public static final String EVENT_KEY_READABLE = "event";
	public static final String EVENT_LIST_KEY_READABLE = "events";
	
	public static final String BLOG_POST_KEY  =  "_" + BlogPost.class.getName();
	public static final String BLOG_POST_KEY_READABLE = "post";
	
	public static final String ALBUM_KEY = "_" + Album.class.getName();
	public static final String ALBUM_KEY_READABLE = "album";
	public static final String ALBUM_LIST_KEY_READABLE = "albums";
	
	public static final String PHOTO_KEY = "_" + Photo.class.getName();
	public static final String PHOTO_KEY_READABLE = "photo";
	public static final String PHOTO_LIST_KEY_READABLE = "photos";
	
	public static final String QUESTION_KEY = "_" + Question.class.getName();
	public static final String QUESTION_KEY_READABLE = "question";
	public static final String QUESTION_LIST_KEY_READABLE = "questions";
	
	public static final String ANSWER_KEY = "_" + Answer.class.getName();
	public static final String ANSWER_KEY_READABLE = "answer";
	public static final String ANSWER_LIST_KEY_READABLE = "answers";
	
	public static final String BLOG_CATEGORY_KEY = "_" + BlogCategory.class.getName();
	public static final String BLOG_CATEGORY_KEY_READABLE = "category";
	public static final String BLOG_CATEGORY_LIST_KEY_READABLE = "categories";
	
	public static final String ACTIVITY_KEY = "_" + Activity.class.getName();
	public static final String ACTIVITY_KEY_READABLE = "activity";
	
	public static final String COMMENT_KEY = "_" + Comment.class.getName();
	public static final String COMMENT_KEY_READABLE = "comment";
	public static final String COMMENT_LIST_KEY_READABLE = "comments";
	
	public static final String ATTACHMENT_KEY = "_" + Attachment.class.getName();
	public static final String ATTACHMENT_KEY_READABLE = "attachment";
	public static final String ATTACHMENT_LIST_KEY_READABLE = "attachments";
	
	public static final String LINK_KEY = "_" + Link.class.getName();
	public static final String LINK_KEY_READABLE = "link";
	
	public static final String FORUM_KEY = "_" + Forum.class.getName();
	public static final String FORUM_KEY_READABLE = "forum";
	public static final String FORUM_LIST_KEY_READABLE = "forums";
	
	public static final String TOPIC_KEY = "_" + Topic.class.getName();
	public static final String TOPIC_KEY_READABLE = "topic";
	public static final String TOPIC_LIST_KEY_READABLE = "topics";
	
	public static final String REPLY_KEY = "_" + Reply.class.getName();
	public static final String REPLY_KEY_READABLE = "reply";
	
	public static final String MESSAGE_KEY = "_" + Message.class.getName();
	public static final String MESSAGE_KEY_READABLE = "message";
	
	public static final String TEAM_MEMBER_KEY = "_" + TeamMember.class.getName();
	public static final String TEAM_MEMBER_KEY_READABLE = "member";
	
	public static final String FOLDER_KEY = "_" + Folder.class.getName();
	public static final String FOLDER_KEY_READABLE = "folder";
	public static final String FOLDER_TREE_KEY_READABLE = "folderTree";
	public static final String FOLDER_LIST_KEY_READABLE = "folders";
	
	public static final String FILE_ITEM_KEY = "_" + FileItem.class.getName();
	public static final String FILE_ITEM_KEY_READABLE = "fileItem";
	public static final String FILE_ITEM_LIST_KEY_READABLE = "fileItems";
}
