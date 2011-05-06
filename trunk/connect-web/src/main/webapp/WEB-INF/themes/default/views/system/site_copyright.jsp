<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="copyright">
<c:choose>
	<c:when test="${empty site.copyright}">
	&copy; Enterprise Connect 2010-2011 
	<a>${site.title}</a> 
	Powered By 
	<a>Enterprise Connect</a>
	</c:when>
	<c:otherwise>
	${site.copyright}
	</c:otherwise>
</c:choose>
</div>