<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<form:form id="site-form${id}" cssClass="site-form" 
			action="${base}/process/system/site" commandName="site">
				<div>
					<label for="domain">网站域名:</label>
					<form:input path="domain"/>
				</div>
				<div>
					<label for="contextPath">上下文</label>
					<form:input path="contextPath"/>
				</div>
				<div>
					<label for="port">端口</label>
					<form:input path="port"/>
				</div>
				<div>
					<label for="ssl">SSL</label>
					<form:checkbox path="ssl"/>
				</div>
				<div>
					<label for="title">网站标题:</label>
					<form:input path="title"/>
				</div>
				<div>
					<label for="description">网站描述:</label>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="keywords">网站关键词:</label>
					<form:input path="keywords"/>
				</div>
				<div>
					<label for="copyright">网站版权:</label>
					<form:textarea path="copyright"/>
				</div>
				<div>
					<label for="theme">网站主题:</label>
					<c:if test="${fn:length(themes) gt 0}">
						<form:select path="themeId" items="${themes}" itemLabel="name" itemValue="id"/>
					</c:if>
				</div>
				<div>
					<label for="mailSettings">邮件配置:</label>
					<form:select path="mailSettingsId" items="${mailSettingsList}" itemLabel="host" itemValue="id"/>
				</div>
				<div>
					<label for="enabled">启用</label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('lang', 'io-form', 'json', function(Y){
	var siteForm = Y.one('#site-form${id}');
	siteForm.on('submit', function(e){
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			try {
				var site = Y.JSON.parse(o.responseText);
				window.location.href='?siteId='+site.id;
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(siteForm.get('action'), {
			method: 'POST',
			form: {
				id: siteForm
			}
		});
		e.halt();
	});
});
</script>