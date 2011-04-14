<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="searchForm" action="${base}/search" commandName="searchBean" method="GET">
			<fieldset>
				<div>
					<form:input path="keywords" cssClass="keywords"/>
				</div>
				<div>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
					<input class="button" type="submit" value='<fmt:message key="search.search_form.submit"/>'>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>