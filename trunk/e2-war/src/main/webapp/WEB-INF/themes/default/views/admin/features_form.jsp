<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<table>
			<thead>
				<tr>
					<th>显示名</th>
					<th>编码</th>
					<th>排序值</th>
					<th>状态</th>
					<th>权限</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="feature" items="${features}" varStatus="status">
				<tr>
				<c:set var="feature" value="${feature}" scope="request"/>
				<form:form id="feature-form${status.count}" cssClass="feature-form" 
					action="${base}/process/admin/feature" commandName="feature">
					<td>
						<form:input path="label" cssClass="{validate:{required:true, messages:{required:'显示名不能为空！'}}}"/>
					</td>
					<td>
						<form:input path="code" readonly="true"/>
					</td>
					<td>
						<form:input path="level" size="2"/>
					</td>
					<td>
						<form:checkbox path="show"/>
					</td>
					<td>
						<form:select path="roleId" items="${roles}" itemLabel="name" itemValue="id"/>
						<form:hidden path="id"/>
						<form:hidden path="projectId"/>
					</td>
				</form:form>
				</tr>
				</c:forEach>
			</tbody>		
			<tfoot>
				<tr>
					<td>
					<button id="submit${id}" class="button">
						<span id="status1${id}">
							提交
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<div>
		</div>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var featureForms = Y.all('.feature-form');
	Y.one('#submit${id}').on('click', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
    	//
    	featureForms.each(function(e){
    		Y.io(this.get('action'), {
    			method: 'POST',
    			form: {
    				id: this
    			}
    		});
    	});
    	//
    	setTimeout('window.location.reload()', 500);
		e.halt();
	});
});
</script>

<%-- 
<script type="text/javascript">
$(document).ready(function(){
	$('#${fragmentConfig.id} form').submit(function(){
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
		$('#${fragmentConfig.id} form').each(function(){
			$(this).submit();
		});
		setTimeout('window.location.reload()', 500);
	});
});
</script>
--%>