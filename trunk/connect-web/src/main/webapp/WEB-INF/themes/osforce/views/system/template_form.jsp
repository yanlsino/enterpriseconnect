<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="templateForm" action="${base}/process/system/template" commandName="template">
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
					<label for="name">名称:</label>
					<form:input path="name"/>
				</div>
				<div>
					<label for="code">编码:</label>
					<form:input path="code"/>
				</div>
				<div>
					<label for="content">内容:</label>
					<form:textarea path="content"/>
				</div>
				<div>
					<label for="category">分类:</label>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="enabled">启用:</label>
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
	$('#templateForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(template){
					window.location.href="?templateId="+template.id+"&siteId=${param.siteId}";
				}
			});	
			return false;
		},
		meta: "validate"
	});
});
</script>