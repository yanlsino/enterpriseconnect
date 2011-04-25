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
					<label for="domain">网站域名 <span class="required">*</span></label>
					<br/>
					<form:input path="domain"/>
				</div>
				<div>
					<label for="contextPath">上下文 <span class="required">*</span></label>
					<br/>
					<form:input path="contextPath"/>
				</div>
				<div>
					<label for="port">端口 <span class="required">*</span></label>
					<br/>
					<form:input path="port"/>
				</div>
				<div>
					<label for="ssl">SSL</label>
					<br/>
					<form:checkbox path="ssl"/>
				</div>
				<div>
					<label for="title">网站标题 <span class="required">*</span></label>
					<br/>
					<form:input path="title"/>
				</div>
				<div>
					<label for="description">网站描述</label>
					<br/>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="keywords">网站关键词</label>
					<br/>
					<form:input path="keywords"/>
				</div>
				<div>
					<label for="copyright">网站版权</label>
					<br/>
					<form:textarea path="copyright"/>
				</div>
				<div>
					<label for="theme">网站主题</label>
					<br/>
					<form:select path="themeId">
						<form:option value="" label="default"/>
						<form:options items="${themes}" itemLabel="name" itemValue="id"/>
					</form:select>
				</div>

				<div>
					<label for="mailSettings">邮件配置</label>
					<br/>
					<form:select path="mailSettingsId" items="${mailSettingsList}" itemLabel="host" itemValue="id"/>
				</div>
				<div>
					<label for="enabled">启用</label>
					<br/>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<button type="submit" class="button">提交</button>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#site-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			var domain = $.trim(formData[0].value);
			var contextPath = $.trim(formData[1].value);
			var port = $.trim(formData[2].value);
			var title = $.trim(formData[4].value);
			if(domain=='' || contextPath=='' || port=='' || title=='') {
				return false;
			}
		},
		success: function(site){
			window.location.href='?siteId='+site.id;
		}
	});
});
</script>