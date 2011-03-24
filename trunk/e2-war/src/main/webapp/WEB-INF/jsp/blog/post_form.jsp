<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="postForm" action="${base}/process/blog/post" commandName="post">
			<div>
				<label for="title"><fmt:message key="blog.post_form.title"/></label>
				<c:set var="titleRequired"><fmt:message key='blog.post_form.titleRequired'/></c:set>
				<form:input path="title" cssClass="{validate:{required:true, messages:{required:'${titleRequired}'}}}"/>
				<span><fmt:message key="blog.post_form.classify"><fmt:param value="${fn:length(categories)}"/></fmt:message></span>
				<form:select path="categoryId" itemLabel="label" itemValue="id" items="${categories}" />
			</div>
			<div>
				<label for="content"><fmt:message key="blog.post_form.content"/></label>
				<c:set var="contentRequired"><fmt:message key='blog.post_form.contentRequired'/></c:set>
				<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'${contentRequired}'}}}"/>
			</div>
			<div>
				<label for="keywords"><fmt:message key="blog.post_form.keywords"/></label>
				<form:input path="keywords"/>
			</div>
			<div>
				<input class="button" type="submit" value="<fmt:message key="blog.post_form.submit"/>">
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
	KE.show({
		id : 'content',
		imageUploadJson: '${base}/process/commons/kindeditor'
	});
	
	$('#postForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(post){
					setTimeout('window.location.href="?postId='+post.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>