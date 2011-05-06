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
			commandName="category" cssClass="blog-category-form">
				<div>
					<label for="label"><fmt:message key="blog.category_form.label"/></label>
					<form:input path="label"/>
				</div>
				<div>
					<label for="level"><fmt:message key="blog.category_form.level"/></label>
					<form:input path="level" size="2"/>
				</div>
				<div>
					<label for="enabled"><fmt:message key="blog.category_form.enabled"/></label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<button type="submit" class="button">
						<fmt:message key="blog.category_form.submit"/>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="projectId"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#blog-category-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var label = $.trim(formData[0].value);
			if(label=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(category){
			setTimeout(function(){
				window.location.href='?categoryId=' + category.id;				
			}, 500);
		}
	});
});
</script>