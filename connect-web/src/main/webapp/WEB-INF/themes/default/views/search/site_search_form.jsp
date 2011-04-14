<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form id="site-search-form" action="${base}/search" 
	commandName="searchBean" method="GET" cssClass="inline">
	<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
 	<form:input path="keywords"/>
	<input type="submit" name="search" value="搜索"/>
</form:form> 