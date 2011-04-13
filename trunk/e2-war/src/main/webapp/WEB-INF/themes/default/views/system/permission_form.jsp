<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
<!--
select[multiple] {
	height: 200px;
	width: 100px;
} 
-->
</style>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="permission-form${id}" cssClass="permission-form" 
			action="${base}/process/system/permission" commandName="permission">
				<div>
					<label for="categoryId">分类名称:</label>
					<br/>
					<form:select path="categoryId" id="select-category${id}" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="roleId">角色名称:</label>
					<br/>
					<form:select path="roleId" id="select-role${id}" items="${roles}" itemLabel="name" itemValue="id"/>
				</div>
				<c:choose>
					<c:when test="${param.multiple}">
					<div>
						<label for="resourceId">资源名称:</label>
						<br/>
						<select id="select1${id}" multiple="multiple">
						<c:forEach var="resource" items="${resources}">
							<option value="${resource.id}">${resource.name}</option>
						</c:forEach>
						</select>
						<a id="addAction${id}" href="#">&gt;&gt;Add</a>
						|
						<a id="removeAction${id}" href="#">Remove&lt;&lt;</a>
						<select id="select2${id}" multiple="multiple">
						<c:forEach var="p" items="${permissions}">
							<c:if test="${p.role.id eq permission.role.id}">
							<option value="${p.resource.id}">${p.resource.name}</option>
							</c:if>
						</c:forEach>
						</select>
					</div> 					
					</c:when>
					<c:otherwise>
					<div>
						<label for="resourceId">资源名称:</label>
						<form:select path="resourceId" items="${resources}" itemLabel="name" itemValue="id"/>
					</div>
					</c:otherwise>
				</c:choose>
				<div>
					<button type="submit">提交</button>
					<button type="reset">重置</button>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<c:choose>
	<c:when test="${param.multiple}">
	<script type="text/javascript">
	YUI().use('io-form', 'json', function(Y){
		Y.one('#select-category${id}').on('change', function(e){
			window.location.href='?siteId=${param.siteId}&multiple=${param.multiple}&categoryId='+e.currentTarget.get('value');
		});
		Y.one('#select-role${id}').on('change', function(e){
			window.location.href='?siteId=${param.siteId}&multiple=${param.multiple}&categoryId=${param.categoryId}&roleId='+e.currentTarget.get('value');
		});
		//
		var select1 = Y.one('#select1${id}');
		var select2 = Y.one('#select2${id}');
		Y.one('#addAction${id}').on('click', function(e){
			select1.all('option').each(function(){
				if(this.get('selected')) {
					select2.insert(this);
				}
			});
			e.halt();
		});
		Y.one('#removeAction${id}').on('click', function(e){
			select2.all('option').each(function(){
				if(this.get('selected')) {
					select1.insert(this);
				}
			})
			e.halt();
		});
		//
		var permissionForm = Y.one('#permission-form${id}');
		permissionForm.on('submit', function(e){
			select2.all('option').each(function(){
				var resourceId = this.get('value');
				Y.io(permissionForm.get('action'), {
					method: 'POST',
					data: 'resourceId='+resourceId,
					form: {
						id: permissionForm
					}
				});
			});
			e.halt();
		});
	});
	</script>
	</c:when>
	<c:otherwise>
	<script type="text/javascript">
	YUI().use('io-form', 'json', function(Y){
		var permissionForm = Y.one('#permission-form${id}');
		permissionForm.on('submit', function(e){
			Y.on('io:complete', function(id, o){
				try {
					var permission = Y.JSON.parse(o.responseText);
					window.location.href='?permissionId='+permission.id+'&siteId=${param.siteId}&categoryId=${param.categoryId}';
				} catch(e) {
					// TODO alert message username or password invalid
				}
			});
			Y.io(permissionForm.get('action'), {
				method: 'POST',
				form: {
					id: permissionForm
				}
			});
			e.halt();
		});
	});
	</script>
	</c:otherwise>
</c:choose>