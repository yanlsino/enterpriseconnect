<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form id="siteSearchForm" action="${base}/search" commandName="searchBean" method="GET">
	<div class="search-scope">
		<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id" id="main-search-category" cssClass="search-category"/>
	</div>
	
	<script id="search-style" type="linkedin/control" cssClass="li-control"> 
 		/* 设置搜索的下拉列表样式 */ 
 		LI.Controls.addControl('search-style', 'StyledDropdown', { name: 'universal-search-selector', align: 'right' });
 	</script>
 	
 	<span id="search-autocomplete-container" title="Tip: You can also search by keyword, company, school..." class="/typeahead">
 		<form:input path="keywords" id="main-search-box" cssClass="search-term" autocomplete="off"/>
 	</span>
	<input name="search" value="Search" class="search-go" type="submit">
</form:form>

  <script type="text/javascript">
 LI_WCT([
 "search-style",
  ]); 
 </script>
 
 

 




 
