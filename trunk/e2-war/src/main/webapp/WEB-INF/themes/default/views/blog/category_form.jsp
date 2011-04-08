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
		<form:form id="blog-category-form${id}" action="${base}/process/blog/category" 
			commandName="category" class="blog-category-form">
				<p>
					<label for="label"><fmt:message key="blog.category_form.label"/></label>
					<form:input path="label" cssClass="{validate:{required:true, messages:{required:'显示名不能为空！'}}}"/>
				</p>
				<p>
					<label for="level"><fmt:message key="blog.category_form.level"/></label>
					<form:input path="level" size="2"/>
				</p>
				<p>
					<label for="enabled"><fmt:message key="blog.category_form.enabled"/></label>
					<form:checkbox path="enabled"/>
				</p>
				<p>
					<button type="submit" class="button">
						<span id="status1${id}">
							<fmt:message key="blog.category_form.submit"/>
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="projectId"/>
				</p>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var categoryForm = Y.one('#blog-category-form${id}');
	categoryForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.on('io:complete', function(id, o){
			try {
				var category = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?categoryId='+category.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(categoryForm.get('action'), {
			method: 'POST',
			form: {
				id: categoryForm
			}
		});
		e.halt();
	});
});
</script>

<%-- 
<script type="text/javascript">
$(document).ready(function(){
	$('#blog-category-form').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			}); 
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(category){
					setTimeout('window.location.href="?categoryId='+category.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>
--%>