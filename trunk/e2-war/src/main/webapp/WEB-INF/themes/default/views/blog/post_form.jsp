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
			<p>
				<label for="title" class="title"><fmt:message key="blog.post_form.title"/></label>
				<br/>
				<c:set var="titleRequired"><fmt:message key='blog.post_form.titleRequired'/></c:set>
				<form:input path="title" cssClass="title {validate:{required:true, messages:{required:'${titleRequired}'}}}"/>
				<span><fmt:message key="blog.post_form.classify"><fmt:param value="${fn:length(categories)}"/></fmt:message></span>
				<form:select path="categoryId" itemLabel="label" itemValue="id" items="${categories}" />
			</p>
			<p>
				<label for="content" class="title"><fmt:message key="blog.post_form.content"/></label>
				<br/>
				<c:set var="contentRequired"><fmt:message key='blog.post_form.contentRequired'/></c:set>
				<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'${contentRequired}'}}}"/>
			</p>
			<p>
				<label for="keywords"><fmt:message key="blog.post_form.keywords"/></label>
				<br/>
				<form:input path="keywords" cssClass="text"/>
			</p>
			<p>
				<button type="submit" class="button">
					<span id="status1${id}">
						<fmt:message key="blog.post_form.submit"/>
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
				<c:if test="${not empty post.entered}">
				<input type="hidden" name="entered" value='<fmt:formatDate value="${post.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</c:if>
			</p>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var postForm = Y.one('#blog-post-form${id}');
	postForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.on('io:complete', function(id, o){
			try {
				var post = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?postId='+post.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(postForm.get('action'), {
			method: 'POST',
			form: {
				id: postForm
			}
		});
		e.halt();
	});
});
</script>

<%--
<script type="text/javascript">
$(document).ready(function(){
	KE.show({
		id : 'content',
		imageUploadJson: '${base}/process/commons/kindeditor'
	});
	
	$('#post-form').validate({
		submitHandler: function(form) {
			$('#${id}').block({ 
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
 --%>