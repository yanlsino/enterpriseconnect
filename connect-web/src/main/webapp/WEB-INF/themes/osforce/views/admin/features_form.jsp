<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<c:forEach var="feature" items="${features}" varStatus="status">
		<c:set var="feature" value="${feature}" scope="request"/>
		<form:form id="feature${status.count}" cssClass="featuresForm" action="${base}/process/admin/features" commandName="feature">
			<div>
				<label>${feature.label}</label>
				<form:input path="label" cssClass="{validate:{required:true, messages:{required:'显示名不能为空！'}}}"/>
				<form:input path="code" readonly="true"/>
				<form:input path="level" size="2"/>
				<form:checkbox path="show"/>
				<form:select path="roleId" items="${roles}" itemLabel="name" itemValue="id"/>
				<form:hidden path="id"/>
				<form:hidden path="projectId"/>
			</div>
		</form:form>
		</c:forEach>
		<div>
			<button class="button">提交</button>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('.featuresForm').submit(function(){
		$(this).ajaxSubmit({
			dataType: 'json',
			success: function(feature){
			}
		});
		return false;
	});
	$('.button').click(function(){
		$('#${fragmentConfig.id}').block({ 
			message: '正在处理...',
			overlayCSS: { backgroundColor: '#EEE' }
		});
		$('.featuresForm').each(function(){
			$(this).submit();
		});
		setTimeout('window.location.reload()', 500);
	});
});
</script>