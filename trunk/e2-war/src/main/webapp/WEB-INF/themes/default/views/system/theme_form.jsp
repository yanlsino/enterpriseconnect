<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="theme-form${id}" cssClass="theme-form" 
			action="${base}/process/system/theme" commandName="theme">
				<div>
					<label for="name">名称:</label>
					<form:input path="name"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var themeForm = Y.one('#theme-form${id}');
	themeForm.on('submit', function(e){
		Y.on('io:complete', function(id, o){
			try {
				var theme = Y.JSON.parse(o.responseText);
				window.location.href='?themeId='+theme.id;
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(themeForm.get('action'), {
			method: 'POST',
			form: {
				id: themeForm
			}
		});
		e.halt();
	});
});
</script>