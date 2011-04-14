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
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('yui2-editor', function(Y){
	var YAHOO = Y.YUI2;
	//
	var editor = new YAHOO.widget.Editor('editor${id}', 
	{
	    animate: true,
	    dompath: true,
	    focusAtStart: true,
	    autoHeight: true,
	    collapse: false,
	    toolbar: {
	      collapse: false,
	      draggable: false,
	      buttonType: 'advanced',
	      buttons: [
	          { group: 'textstyle',
	              buttons: [
	                  { type: 'push', label: 'Bold CTRL + SHIFT + B', value: 'bold' },
	                  { type: 'push', label: 'Italic CTRL + SHIFT + I', value: 'italic' },
	                  { type: 'push', label: 'Underline CTRL + SHIFT + U', value: 'underline' },
	              ]
	          },             
	          { type: 'separator' },
	          { group: 'indentlist',
	              buttons: [
	                  { type: 'push', label: 'Indent', value: 'indent', disabled: true },
	                  { type: 'push', label: 'Outdent', value: 'outdent', disabled: true },
	                  { type: 'push', label: 'Create an Unordered List', value: 'insertunorderedlist' },
	                  { type: 'push', label: 'Create an Ordered List', value: 'insertorderedlist' }
	              ]
	          }        
	      ]
	    }
	});
	//
	editor.on('toolbarLoaded', function() {
	    this.on('afterNodeChange', function(o) {
	    	editor.saveHTML();
	    });
	}, editor, true);
	editor.render();
});
</script>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var postForm = Y.one('#blog-post-form${id}');
	postForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.io.header('Content-Type', 'application/json');
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