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
		<form:form id="resource-form${id}" cssClass="resource-form" 
			action="${base}/process/system/resource" commandName="resource">
			<fieldset>
				<div>
					<label for="name">名称:</label>
					<form:input path="name"/>
				</div>
				<div>
					<label for="code">编码:</label>
					<form:input path="code"/>
				</div>
				<div>
					<label for="description">描述:</label>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="enabled">启用:</label>
					<form:checkbox path="enabled" value="true"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<input type="reset" value=" 重置 "/>
					<form:hidden path="id"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var resourceForm = Y.one('#resource-form${id}');
	resourceForm.on('submit', function(e){
		Y.on('io:complete', function(id, o){
			try {
				var resource = Y.JSON.parse(o.responseText);
				window.location.href="?resourceId="+resource.id;
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(resourceForm.get('action'), {
			method: 'POST',
			form: {
				id: resourceForm
			}
		});
		e.halt();
	});
});
</script>