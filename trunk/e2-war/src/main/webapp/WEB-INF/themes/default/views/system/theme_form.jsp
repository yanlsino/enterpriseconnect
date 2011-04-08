<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="themeForm" action="${base}/process/system/theme" commandName="theme">
			<fieldset>
				<legend></legend>
				<div>
					<label for="name">名称:</label>
					<form:input path="name"/>
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
	$('#themeForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(theme){
					window.location.href="?themeId="+theme.id;
				}
			});	
			return false;
		},
		meta: "validate"
	});
});
</script>