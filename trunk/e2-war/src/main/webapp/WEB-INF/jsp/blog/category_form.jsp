<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<s:form id="blogCategoryForm" action="${base}/process/blog/category" commandName="category">
			<fieldset>
				<div>
					<label for="label"><fmt:message key="blog.category_form.label"/></label>
					<s:input path="label" cssClass="{validate:{required:true, messages:{required:'显示名不能为空！'}}}"/>
				</div>
				<div>
					<label for="level"><fmt:message key="blog.category_form.level"/></label>
					<s:input path="level" size="2"/>
				</div>
				<div>
					<label for="enabled"><fmt:message key="blog.category_form.enabled"/></label>
					<s:checkbox path="enabled"/>
				</div>
				<div>
					<input type="submit" value='<fmt:message key="blog.category_form.submit"/>'/>
					<s:hidden path="id"/>
					<s:hidden path="projectId"/>
				</div>
			</fieldset>
		</s:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#blogCategoryForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			}); 
			$(form).ajaxSubmit({
				success:function(category){
					setTimeout('window.location.href="?categoryId='+category.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>