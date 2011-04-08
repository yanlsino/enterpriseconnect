<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="forumForm" action="${base}/process/discussion/forum" commandName="forum">
			<fieldset>
				<div>
					<label for="name"><fmt:message key="discussion.forum_form.name"/></label>
					<form:input path="name" cssClass="{validate:{required:true, messages:{required:'版块名不能为空！'}}}"/>
				</div>
				<div>
					<label for="discription"><fmt:message key="discussion.forum_form.description"/></label>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="level"><fmt:message key="discussion.forum_form.level"/></label>
					<form:input path="level" size="2"/>
				</div>
				<div>
					<input type="submit" value='<fmt:message key="discussion.forum_form.submit"/>'/>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="projectId"/>
					<c:if test="${not empty forum.entered}">
					<input type="hidden" name="entered" value='<fmt:formatDate value="${forum.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/> 
					</c:if>
				</div>
			</fieldset>	
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#forumForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
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