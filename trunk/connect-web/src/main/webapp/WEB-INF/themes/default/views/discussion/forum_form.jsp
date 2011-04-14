<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<set var="id" value="${fragmentConfig.id}"/>
<set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="forum-form${id}" action="${base}/process/discussion/forum" 
			commandName="forum" class="forum-form">
				<div>
					<label for="name" class="title"><fmt:message key="discussion.forum_form.name"/></label>
					<br/>
					<form:input path="name" cssClass="text {validate:{required:true, messages:{required:'版块名不能为空！'}}}"/>
				</div>
				<div>
					<label for="discription" class="title"><fmt:message key="discussion.forum_form.description"/></label>
					<br/>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="level" class="title"><fmt:message key="discussion.forum_form.level"/></label>
					<br/>
					<form:input path="level" size="2"/>
				</div>
				<div>
					<button type="submit" class="button">
						<span id="status1${id}">
							<fmt:message key="discussion.forum_form.submit"/>
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="projectId"/>
					<c:if test="${not empty forum.entered}">
					<input type="hidden" name="entered" value='<fmt:formatDate value="${forum.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/> 
					</c:if>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var forumForm = Y.one('#forum-form${id}');
	forumForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			try {
				var forum = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?forumId='+forum.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(forumForm.get('action'), {
			method: 'POST',
			form: {
				id: forumForm
			}
		});
		e.halt();
	});
});
</script>