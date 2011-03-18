<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8" />
	<meta name="description" content="开源力量 开放 协作 创新" />
	<meta name="robots" content="index, follow" />
	<title>开源力量</title>
	<%--theme style --%>
	<link rel="stylesheet" type="text/css" href="<c:url value="themes/${theme.name}/css/layout.css"/>"/>
</head>
<body>
<div id="header">
	<osf:ifPositionExist name="mainMenu">
		<osf:positionRender name="mainMenu"/>
	</osf:ifPositionExist>
	<p id="layoutdims"><strong> 开源 </strong> | <strong> 开放 </strong> | <strong> 创新 </strong> | <strong> 协作 </strong></p>
</div>
<div class="colmask threecol">
	<div class="colmid">
		<div class="colleft">
			<osf:ifPositionExist name="mainColumn">
			<div class="col1">
				<osf:positionRender name="mainColumn"/>
			</div>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="leftColumn">
			<div class="col2">
				<osf:positionRender name="leftColumn"/>
			</div>
			</osf:ifPositionExist>				
			<osf:ifPositionExist name="rightColumn">
			<div class="col3">
				<osf:positionRender name="rightColumn"/>
			</div>
			</osf:ifPositionExist>
		</div>
	</div>
</div>
<div id="footer">
	<p>Powered By <a href="#"> Open Source Force </a> </p>
</div>
</body>
</html>