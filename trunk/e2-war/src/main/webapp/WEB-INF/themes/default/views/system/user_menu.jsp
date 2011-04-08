<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<ul>
		<c:choose>
			<c:when test="${empty user}">
			<li>
				<a href="${base}/login"><fmt:message key="user_menu.login"/></a>
			</li>
			<li class="last">
				<a href="${base}/register"><fmt:message key="user_menu.register"/></a>
			</li>
			</c:when>
			<c:otherwise>
			<li>
				<a href="${base}/${user.project.uniqueId}/profile">${user.nickname}</a>
			</li>
			<li class="last">
				<a href="${base}/logout"><fmt:message key="user_menu.logout"/></a>
			</li>
			</c:otherwise>
		</c:choose>
	</ul>



