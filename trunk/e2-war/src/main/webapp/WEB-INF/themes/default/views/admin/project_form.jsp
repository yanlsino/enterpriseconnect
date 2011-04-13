<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="project-form${id}" action="${base}/process/admin/project" 
			commandName="project" cssClass="project-form">
				<p>
					<label for="title" class="title">名称:</label>
					<br/>
					<form:input path="title" cssClass="text {validate:{required:true, messages:{required:'名称不能为空！'}}}"/>
				</p>
				<p>
					<label for="uniqueId" class="title">唯一编码:([a-z] - _)</label>
					<br/>
					<form:input path="uniqueId" cssClass="text {validate:{required:true, messages:{required:'唯一编码不能为空！'}}}"/>
				</p>
				<p>
					<label for="category" class="title">分类:</label>
					<br/>
					<form:input path="category.label" readonly="true" cssClass="text"/>
				</p>
				<c:if test="${not empty subCategories1}">
				<p>
					<label for="subCategory1" class="title">子分类:</label>
					<br/>
					<form:select path="subCategoryId1">
						<form:option value="" label="无"/>
						<form:options items="${subCategories1}" itemLabel="label" itemValue="id"/>
					</form:select>
				</p>
				</c:if>
				<p>
					<button type="submit" class="button">
						<span id="status1${id}">
							提交
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="categoryId"/>
					<input type="hidden" name="entered" value='<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</p>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var projectForm = Y.one('#project-form${id}');
	projectForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();;
		//
		Y.on('io:complete', function(id, o){
			try {
				var project = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?projectId='+project.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(projectForm.get('action'), {
			method: 'POST',
			form: {
				id: projectForm
			}
		});
		e.halt();
	});
});
</script>