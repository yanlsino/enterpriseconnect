<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form id="site-search-form" action="${base}/search" 
	commandName="searchBean" method="GET" cssClass="inline">
	<form:select path="categoryId" >
		<option value="">全站</option>
		<form:options items="${categories}" itemLabel="label" itemValue="id"/>
	</form:select>
 	<form:input path="keywords"/>
	<input type="submit" value="搜索"/>
</form:form> 