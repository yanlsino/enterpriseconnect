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
		<form:form id="project-form${id}" cssClass="project-form" 
			action="${base}/process/system/project" commandName="project">
				<div>
					<label for="title">名称: <span class="required">*</span></label>
					<br/>
					<form:input path="title" id="title${id}" cssClass="text"/>
				</div>
				<div>
					<label for="uniqueId">唯一编码: <span class="required">*</span>([a-z] - _)</label>
					<br/>
					<form:input path="uniqueId" id="uniqueId${id}" cssClass="text"/>
				</div>
				<div>
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
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var projectForm = Y.one('#project-form${id}');
	projectForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		if(validateForm()) {
			Y.on('io:complete', function(id, o){
				try {
					var project = Y.JSON.parse(o.responseText);
					window.location.href='${base}/'+project.uniqueId+'/profile';
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
		}
		e.halt();
	});
	
	function validateForm() {
		var title = Y.one('#title${id}').get('value');
		var uniqueId = Y.one('#uniqueId${id}').get('value');
		if(title.trim()=='' || uniqueId.trim()=='') {
			return false;
		}
		if(uniqueId.match(/^[\w_-]+$/)==null) {
			return false;
		}
		return true;
	}
});
</script>