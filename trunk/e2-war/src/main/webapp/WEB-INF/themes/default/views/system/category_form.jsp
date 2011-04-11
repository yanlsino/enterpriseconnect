<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<form:form id="project-category-form${id}" cssClass="project-category-form" 
			action="${base}/process/system/category" commandName="category">
				<div>
					<label for="label">显示名:</label>
					<br/>
					<form:input path="label" cssClass="text"/>
				</div>
				<div>
					<label for="code">编码:</label>
					<br/>
					<form:input path="code" cssClass="text"/>
				</div>
				<div>
					<label for="level">排序值:</label>
					<form:input path="level"/>
				</div>
				<c:if test="${not empty categories}">
				<div>
					<label for="parentId">父分类:</label>
					<form:select path="parentId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				</c:if>
				<div>
					<label for="enabled">启用:</label>
					<form:checkbox path="enabled" value="true"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
					<form:hidden path="siteId"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var categoryForm = Y.one('#project-category-form${id}');
	categoryForm.on('submit', function(e){
		Y.on('io:complete', function(id, o){
			try {
				var category = Y.JSON.parse(o.responseText);
				window.location.href='?categoryId='+category.id+'&siteId='+category.siteId;
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