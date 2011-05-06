<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
<!--
.tag-cloud ul {margin: 0;padding: 5px;text-align: center;background: #000 url(bg_tags.gif) repeat-x;}
.tag-cloud li {margin: 0;padding: 0;list-style: none;display: inline;}
.tag-cloud li a {text-decoration: none;color: #fff;padding: 0 2px;}
.tag-cloud li a:hover {color: #cff400;}

.tag1 {font-size: 100%;}
.tag2 {font-size: 120%;}
.tag3 {font-size: 140%;}
.tag4 {font-size: 160%;}
.tag5 {font-size: 180%;}
-->
</style>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<div class="tag-cloud">
			<ul>
			<c:forEach var="tag" items="${page.result}" varStatus="status">
				<c:choose>
					<c:when test="${tag.entity eq 'Profile'}">
						<c:set var="targetUrl" value="${base}/${tag.linkedEntity.project.uniqueId}/profile"/>
					</c:when>
					<c:when test="${tag.entity eq 'BlogPost'}">
						<c:set var="targetUrl" value="${base}/${tag.linkedEntity.project.uniqueId}/blog/post/${tag.linkedEntity.id}"/>
					</c:when>
					<c:when test="${tag.entity eq 'Topic'}">
						<c:set var="targetUrl" value="${base}/${tag.linkedEntity.forum.project.uniqueId}/discussion/topic/${tag.linkedEntity.id}"/>
					</c:when>
				</c:choose>
				<li class="tag${status.count%5}"><a href="${targetUrl}">${tag.name}</a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
</div>