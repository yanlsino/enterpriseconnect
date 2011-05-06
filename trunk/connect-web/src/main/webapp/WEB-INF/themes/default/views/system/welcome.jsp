<%@page pageEncoding="UTF-8" %>
<%@page import="org.osforce.connect.Application" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<fieldset>
			<label>平台名称</label>
			<p><%=Application.PRODUCT_NAME %></p>
			<label>当前版本</label>
			<p><%=Application.VERSION %></p>
			<label>开源协议</label>
			<p><%=Application.LICENSE %></p>
		</fieldset>
	</div>
</div>