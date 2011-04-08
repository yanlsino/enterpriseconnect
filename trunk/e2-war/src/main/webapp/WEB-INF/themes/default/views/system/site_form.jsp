<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="siteForm" action="${base}/process/system/site" commandName="site">
			<fieldset>
				<!-- 
				<legend>
					<c:choose>
						<c:when test="${empty site.id}">添加网站信息</c:when>
						<c:otherwise>修改网站信息</c:otherwise>			
					</c:choose>
				</legend>
				 -->
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
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#siteForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(site){
					window.location.href="?siteId="+site.id;
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>