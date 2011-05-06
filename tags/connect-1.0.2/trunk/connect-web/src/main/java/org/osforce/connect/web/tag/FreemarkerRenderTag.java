package org.osforce.connect.web.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.osforce.connect.entity.blog.BlogPost;
import org.osforce.connect.entity.calendar.Event;
import org.osforce.connect.entity.commons.Activity;
import org.osforce.connect.entity.discussion.Forum;
import org.osforce.connect.entity.discussion.Reply;
import org.osforce.connect.entity.discussion.Topic;
import org.osforce.connect.entity.document.FileItem;
import org.osforce.connect.entity.document.Folder;
import org.osforce.connect.entity.gallery.Album;
import org.osforce.connect.entity.gallery.Photo;
import org.osforce.connect.entity.knowledge.Answer;
import org.osforce.connect.entity.knowledge.Question;
import org.osforce.connect.entity.profile.Profile;
import org.osforce.connect.entity.team.TeamMember;
import org.osforce.connect.service.blog.BlogPostService;
import org.osforce.connect.service.calendar.EventService;
import org.osforce.connect.service.discussion.ForumService;
import org.osforce.connect.service.discussion.ReplyService;
import org.osforce.connect.service.discussion.TopicService;
import org.osforce.connect.service.document.FileItemService;
import org.osforce.connect.service.document.FolderService;
import org.osforce.connect.service.gallery.AlbumService;
import org.osforce.connect.service.gallery.PhotoService;
import org.osforce.connect.service.knowledge.AnswerService;
import org.osforce.connect.service.knowledge.QuestionService;
import org.osforce.connect.service.profile.ProfileService;
import org.osforce.connect.service.team.MemberService;
import org.osforce.connect.task.support.FreemarkerUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * @author gavin
 * @since 1.0.0
 * @create Apr 27, 2011 - 10:59:10 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class FreemarkerRenderTag extends TagSupport {
	private static final long serialVersionUID = 786173550479094745L;
	private ApplicationContext appContext;
	private Activity activity;

	public FreemarkerRenderTag() {
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int doEndTag() throws JspException {
		appContext = RequestContextUtils.getWebApplicationContext(pageContext.getRequest());
		Configuration configuration = appContext.getBean(Configuration.class);
		try {
			Template template = configuration.getTemplate(activity.getDescription());
			Map<Object, Object> model = createModel();
			model.put("base", pageContext.getRequest().getAttribute("base"));
			String result = FreemarkerUtil.render(template, model);
			pageContext.getOut().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private Map<Object, Object> createModel() {
		Map<Object, Object> model = new HashMap<Object, Object>();
		if(StringUtils.equals(Profile.NAME, activity.getEntity())) {
			ProfileService profileService = appContext.getBean(ProfileService.class);
			Profile profile = profileService.getProfile(activity.getLinkedId());
			model.put("profile", profile);
		} else if(StringUtils.equals(BlogPost.NAME, activity.getEntity())) {
			BlogPostService postService = appContext.getBean(BlogPostService.class);
			BlogPost blogPost = postService.getBlogPost(activity.getLinkedId());
			model.put("blogPost", blogPost);
		} else if(StringUtils.equals(Forum.NAME, activity.getEntity())){
			ForumService forumService = appContext.getBean(ForumService.class);
			Forum forum = forumService.getForum(activity.getLinkedId());
			model.put("forum", forum);
		} else if(StringUtils.equals(Topic.NAME, activity.getEntity())) {
			TopicService topicService = appContext.getBean(TopicService.class);
			Topic topic = topicService.getTopic(activity.getLinkedId());
			model.put("topic", topic);
		} else if(StringUtils.equals(Reply.NAME, activity.getEntity())) {
			ReplyService replyService = appContext.getBean(ReplyService.class);
			Reply reply = replyService.getReply(activity.getLinkedId());
			model.put("reply", reply);
		} else if(StringUtils.equals(TeamMember.NAME, activity.getEntity())) {
			MemberService memberService = appContext.getBean(MemberService.class);
			TeamMember teamMember = memberService.getMember(activity.getLinkedId());
			model.put("teamMember", teamMember);
		} else if(StringUtils.equals(Event.NAME, activity.getEntity())) {
			EventService eventService = appContext.getBean(EventService.class);
			Event event = eventService.getEvent(activity.getLinkedId());
			model.put("event", event);
		} else if(StringUtils.equals(Album.NAME, activity.getEntity())) {
			AlbumService albumService = appContext.getBean(AlbumService.class);
			Album album = albumService.getAlbum(activity.getLinkedId());
			model.put("album", album);
		} else if(StringUtils.equals(Photo.NAME, activity.getEntity())) {
			PhotoService photoService = appContext.getBean(PhotoService.class);
			Photo photo = photoService.getPhoto(activity.getLinkedId());
			model.put("photo", photo);
		} else if(StringUtils.equals(Question.NAME, activity.getEntity())) {
			QuestionService questionService = appContext.getBean(QuestionService.class);
			Question question = questionService.getQuestion(activity.getLinkedId());
			model.put("question", question);
		} else if(StringUtils.equals(Answer.NAME, activity.getEntity())) {
			AnswerService answerService = appContext.getBean(AnswerService.class);
			Answer answer = answerService.getAnswer(activity.getLinkedId());
			model.put("answer", answer);
		} else if(StringUtils.equals(FileItem.NAME, activity.getEntity())) {
			FileItemService fileItemService = appContext.getBean(FileItemService.class);
			FileItem fileItem = fileItemService.getFileItem(activity.getLinkedId());
			model.put("fileItem", fileItem);
		} else if(StringUtils.equals(Folder.NAME, activity.getEntity())) {
			FolderService folderService = appContext.getBean(FolderService.class);
			Folder folder = folderService.getFolder(activity.getLinkedId());
			model.put("folder", folder);
		}
		return model;
	}

}

