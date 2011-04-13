<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<set var="id" value="${fragmentConfig.id}"/>
<set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<c:choose>
		<c:when test="${empty forums}">
			<div class="notice">当前无话题版块，因此无法发表话题.</div>
		</c:when>
		<c:otherwise>
		<form:form id="topic-form${id}" action="${base}/process/discussion/topic" 
			commandName="topic" cssClass="topic-form">
				<p>
					<label for="forumId" class="title"><fmt:message key="discussion.topic_form.forum"/></label>
					<br/>
					<form:select path="forumId" items="${forums}" itemLabel="name" itemValue="id"/>
				</p>
				<p>
					<label for="subject" class="title"><fmt:message key="discussion.topic_form.subject"/></label>
					<br/>
					<form:input path="subject" cssClass="title {validate:{required:true, messages:{required:'主题不能为空！'}}}"/>		
				</p>
				<p>
					<label for="content" class="title"><fmt:message key="discussion.topic_form.content"/></label>
					<br/>
					<form:textarea path="content" cssClass="text {validate:{required:true, messages:{required:'内容不能为空！'}}}"/>
				</p>
				<p>
					<button type="submit" class="button">
						<span id="status1${id}">
							<fmt:message key="discussion.topic_form.submit"/>
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="categoryId"/>
					<form:hidden path="forumId"/>
					<form:hidden path="answerId"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<c:if test="${not empty topic.entered}">
					<input type="hidden" name="entered" value='<fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
					</c:if>
				</p>
		</form:form>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var topicForm = Y.one('#topic-form${id}');
	topicForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.on('io:complete', function(id, o){
			try {
				var topic = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?topicId='+topic.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(topicForm.get('action'), {
			method: 'POST',
			form: {
				id: topicForm
			}
		});
		e.halt();
	});
});
</script>