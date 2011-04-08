<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<div class="actions">
			<div>网站自定义</div>
			<ul>
				<li>
					<a href="${base}/system/sites">站点管理</a>
					(<a href="${base}/system/sites/site/form">添加</a>)
					(<a href="${base}/process/system/index-sync">重建索引</a>)
				</li>
				<li>
					<a href="${base}/system/themes">主题管理</a>
					(<a href="${base}/system/themes/theme/form">添加</a>)
				</li>
				<li class="last">
					<a href="${base}/system/mail_settings">邮件设置</a>
					(<a href="${base}/system/mail_settings/mail_settings/form">添加</a>)
				</li>
			</ul>
			<c:if test="${not empty param.siteId}">
			<div>一般管理</div>
			<ul>
				<li>
					<a href="${base}/system/categories?siteId=${param.siteId}">菜单及分类管理</a>
					(<a href="${base}/system/categories/category/form?siteId=${param.siteId}">添加</a>)
				</li>
				<li>
					<a href="${base}/system/templates?siteId=${param.siteId}">模板管理</a>
					(<a href="${base}/system/templates/template/form?siteId=${param.siteId}">添加</a>)
				</li>
				<li class="last">
					<a href="${base}/system/links?siteId=${param.siteId}">友情链接管理</a>
					(<a href="${base}/system/links/link/form?siteId=${param.siteId}">添加</a>)
				</li>
			</ul>	
			<div>安全管理</div>
			<ul>
				<li><a href="${base}/system/users?siteId=${param.siteId}">用户管理</a></li>
				<li>
					<a href="${base}/system/roles?siteId=${param.siteId}">角色管理</a>
					(<a href="${base}/system/roles/role/form?siteId=${param.siteId}&categoryId=${param.categoryId}">添加</a>)
				</li>
				<li>
					<a href="${base}/system/resources?siteId=${param.siteId}">资源管理</a>
					<!-- (<a href="${base}/system/resources/resource/form?siteId=${param.siteId}">添加</a>) -->
					(<a href="${base}/process/system/resources/synchronize?siteId=${param.siteId}">同步</a>)
				</li>
				<li class="last">
					<a href="${base}/system/permissions?siteId=${param.siteId}">权限管理</a>
					(<a href="${base}/system/permissions/permission/form?siteId=${param.siteId}&multiple=true">添加</a>)
				</li>
			</ul>
			</c:if>
		</div>
	</div>
</div>