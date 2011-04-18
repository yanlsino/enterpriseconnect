<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="blog-post-form${id}" action="${base}/process/blog/post" 
			commandName="post" cssClass="blog-post-form">
			<div>
				<label for="title" class="title"><fmt:message key="blog.post_form.title"/></label>
				<br/>
				<form:input path="title" cssClass="title"/>
				<span><fmt:message key="blog.post_form.classify"><fmt:param value="${fn:length(categories)}"/></fmt:message></span>
				<form:select path="categoryId" itemLabel="label" itemValue="id" items="${categories}" />
			</div>
			<div>
				<label for="content" class="title"><fmt:message key="blog.post_form.content"/></label>
				<br/>
				<form:textarea path="content" id="editor${id}" cssClass="text"/>
			</div>
			<div>
				<label for="keywords"><fmt:message key="blog.post_form.keywords"/></label>
				<br/>
				<form:input path="keywords" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">
					<fmt:message key="blog.post_form.submit"/>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
				<c:if test="${not empty post.entered}">
				<input type="hidden" name="entered" value='<fmt:formatDate value="${post.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</c:if>
			</div>
		</form:form>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$('#blog-post-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var title = $.trim(formData[0].value);
			var content = $.trim($('#editor${id}').val());
			if(title=='' || content=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(post){
			setTimeout(function(){
				window.location.href='?postId=' + post.id;
			}, 500);
		}
	});
	//
	$('#editor${id}').htmlarea(settings.simple);
});
</script>