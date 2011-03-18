<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="nav">
	<c:if test="${fragmentConfig.preferences['showHome']}">
		<li id="nav-primary-home" class="tab">
			<a href="${base}/" title='<fmt:message key="system.main_menu.home"/>'> 
				<span><fmt:message key="system.main_menu.home" /></span> 
			</a>
		</li>
	</c:if>

	<c:if test="${fragmentConfig.preferences['showMe'] and not empty user}">
		<li id="nav-primary-profile" class="tab">
			<a href="${base}/${user.project.uniqueId}/profile" title='<fmt:message key="system.main_menu.me"/>'> 
				<span><fmt:message key="system.main_menu.me" /></span> 
			</a>
			<ul class="menu">
				<c:forEach var="feature" items="${user.project.features}">
				<li>
					<a href="${base}/${user.project.uniqueId}/${feature.code}">
						${feature.label}
					</a>
				</li>
				</c:forEach>
			</ul>
		</li>
	</c:if>

	<c:forEach var="category" items="${categories}">
		<li id="nav-primary-profile" class="tab">
			<a href="${base}/${category.code}" title='<fmt:message key="system.main_menu.${category.code}"/>'> 
				<span><fmt:message key="system.main_menu.${category.code}" /></span>
			</a>
			<c:if test="${not empty category.children}">
			<ul class="menu">
				<c:forEach var="subCategory" items="${category.children}">
				<li>
					<a href="${base}/${category.code}/${subCategory.code}">${subCategory.label}</a>
				</li>				
				</c:forEach>
			</ul>
			</c:if>
		</li>
	</c:forEach>
</ul>

<script id="navi-style" type="linkedin/control" class="li-control">
	/* 设置导航菜单下拉列表 */
 	LI.Controls.addControl('navi-style', 'NavigationMenu', {

 	});
 </script>

<script type="text/javascript">
 LI_WCT([
 "navi-style",
 ]); 
 </script>