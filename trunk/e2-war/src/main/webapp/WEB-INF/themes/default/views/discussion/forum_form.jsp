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
				<p>
					<label for="name" class="title"><fmt:message key="discussion.forum_form.name"/></label>
					<br/>
					<form:input path="name" cssClass="text {validate:{required:true, messages:{required:'版块名不能为空！'}}}"/>
				</p>
				<p>
					<label for="discription" class="title"><fmt:message key="discussion.forum_form.description"/></label>
					<br/>
					<form:textarea path="description"/>
				</p>
				<p>
					<label for="level" class="title"><fmt:message key="discussion.forum_form.level"/></label>
					<br/>
					<form:input path="level" size="2"/>
				</p>
				<p>
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
				</p>
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

<%--
<script type="text/javascript">
$(document).ready(function(){
	$('#forum-form').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '<div class="notice">正在处理...</div>',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(forum){
					setTimeout('window.location.href="?forumId='+forum.id+'"', 1000);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>
--%>