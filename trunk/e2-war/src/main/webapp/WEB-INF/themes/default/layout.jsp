<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://packtag.sf.net" prefix="pack" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${site.title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="Keywords" content="${site.title}"/>
    <meta name="Description" content="${site.description}"/>
	
	<link rel="stylesheet" href="${base}/themes/${theme.name}/css/screen.css" type="text/css" media="screen, projection"> 
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/print.css" type="text/css" media="print"> 
    <!--[if lt IE 8]>
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/ie.css" type="text/css" media="screen, projection">
    <!-- plugins -->
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/plugins/buttons/screen.css" type="text/css" media="screen, projection">
    <![endif]-->
	
	<link rel="stylesheet" href="${base}/themes/${theme.name}/css/style.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/fragment.css" type="text/css" media="screen, projection">
    
    <script type="text/javascript" src="${base}/static/js/yui/yui-min.js"></script>
    <script type="text/javascript" src="${base}/static/js/globle.js"></script>
    
    <script type="text/javascript">
    YUI().use('node', function(Y){
    	Y.on('domready', function(){
    		var hasLeftColumn = false;
        	var hasRightColumn = false;
        	if(Y.one('#body .left-column')!=null) {
        		hasLeftColumn = Y.one('#body .left-column').getContent().trim()!='';
        	}
        	if(Y.one('#body .right-column')!=null) {
        		hasRightColumn = Y.one('#body .right-column').getContent().trim()!='';
        	}
        	if(!hasLeftColumn || !hasRightColumn) {
        		if(!hasLeftColumn) {
        			if(Y.one('#body .left-column')!=null) {
        				Y.one('#body .left-column').remove();
        			}
        			Y.one('#body .main-column').addClass('span-16');
        		}
        		if(!hasRightColumn) {
        			Y.one('#body .main-column').addClass('span-19');
        			Y.one('#body .main-column').addClass('last');
        			if(Y.one('#body .right-column')!=null) {
        				Y.one('#body .right-column').remove();
        			}
        		}
        		if(!hasLeftColumn && !hasRightColumn) {
        			if(Y.one('#body .left-column')!=null) {
        				Y.one('#body .left-column').remove();
        			}
        			if(Y.one('#body .right-column')!=null) {
        				Y.one('#body .right-column').remove();
        			}
        			Y.one('#body .main-column').addClass('span-24');
        			Y.one('#body .main-column').addClass('last');
        		}
    		}
    	});
    });
	</script>
</head>
<body class="yui-skin-sam">
	<div class="container">
		<div id="head">
			<a href="${base}">
			<img id="logo" src="${base}/themes/${theme.name}/logo.png"/>
			</a>
			<div id="wrapper">
				<osf:ifPositionExist name="userMenu">
				<div id="user-menu">
					<osf:positionRender name="userMenu"/>
				</div>
				</osf:ifPositionExist>
				<osf:ifPositionExist name="siteSearch">
				<div id="site-search">
					<osf:positionRender name="siteSearch"/>
				</div>
				</osf:ifPositionExist> 
			</div>
			<br class="clear"/>
			<osf:ifPositionExist name="mainMenu">
			<div id="main-menu">
				<osf:positionRender name="mainMenu"/>
			</div>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="subMenu">
			<div id="sub-menu">
				<osf:positionRender name="subMenu"/>
			</div>
			</osf:ifPositionExist>
		</div>
		<div id="body">
			<%-- render --%>
			<osf:ifPositionExist name="leftColumn">
			<div class="span-5 left-column">
				<osf:positionRender name="leftColumn"/>
			</div>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="mainColumn">
			<div class='span-11 main-column'>
				<osf:positionRender name="mainColumn"/>
			</div>
			</osf:ifPositionExist>
			<osf:ifPositionExist name="rightColumn">
			<div class="span-8 last right-column">
				<osf:positionRender name="rightColumn"/>
			</div>
			</osf:ifPositionExist>
			<br class="clear"/>
		</div>
		<osf:ifPositionExist name="foot">
		<div id="foot">
			<osf:positionRender name="foot"/>
		</div>
		</osf:ifPositionExist>
	</div>
</body>
</html>