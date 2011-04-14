<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Default Theme</title>
</head>
<body>
	<div class="container">
		<div id="body">
			<%-- caculate main columns --%>
			<c:set var="mainCols" value="24"/>
			<c:set var="leftColumnExist" value="false"/>
			<c:set var="rightColumnExist" value="false"/>
			<osf:ifPositionExist name="leftColumn">
				<c:set var="mainCols" value="${mainCols-5}"/>
				<c:set var="leftColumnExist" value="true"/>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="rightColumn">
				<c:set var="mainCols" value="${mainCols-8}"/>
				<c:set var="rightColumnExist" value="true"/>
			</osf:ifPositionExist>
			<%-- render --%>
			<osf:ifPositionExist name="leftColumn">
			<div class="span-5">
				<osf:positionRender name="leftColumn"/>
			</div>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="mainColumn">
			<div class='span-${mainCols} <c:if test="${!rightColumnExist}">last</c:if>'>
				<osf:positionRender name="mainColumn"/>
			</div>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="rightColumn">
			<div class="span-8 last">
				<osf:positionRender name="rightColumn"/>
			</div>
			</osf:ifPositionExist>
			<br class="clear"/>
		</div>
	</div>
</body>
</html>