<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${site.title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="Keywords" content="${site.title}"/>
    <meta name="Description" content="${site.description}"/>
	<link rel="SHORTCUT ICON" href="${base}/themes/${theme.name}/icons/favicon.ico"/>

	<link rel="stylesheet" href="${base}/themes/${theme.name}/css/screen.css" type="text/css" media="screen, projection"/>
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/print.css" type="text/css" media="print"/>
    <!--[if lt IE 8]>
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/ie.css" type="text/css" media="screen, projection"/>
    <![endif]-->

	<!-- plugins -->
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/plugins/buttons/screen.css" type="text/css" media="screen, projection"/>


    <script type="text/javascript" src="${base}/static/components/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/static/components/global.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-jHtmlArea/style/jHtmlArea.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-jHtmlArea/jquery.jHtmlArea.min.js"></script>

    <script type="text/javascript" src="${base}/static/components/jquery-form/jquery.form.min.js"></script>
    <script type="text/javascript" src="${base}/static/components/jquery-busy/jquery.busy.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-datePicker/jquery.datePicker.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-datePicker/jquery.datePicker.js"></script>
    <script type="text/javascript" src="${base}/static/components/jquery-datePicker/date.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-simplemodal/jquery.simplemodal.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-simplemodal/jquery.simplemodal.min.js"></script>

	<link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-aqLayer/jquery.aqLayer.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-aqLayer/jquery.aqLayer.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-zTree/jquery.zTree.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-zTree/jquery.zTree.min.js"></script>

    <script type="text/javascript" src="${base}/static/components/jquery-cycle/jquery.cycle.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/static/components/jquery-pagination/pagination.css"/>
    <script type="text/javascript" src="${base}/static/components/jquery-pagination/jquery.pagination.js"></script>

    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/style.css" type="text/css" media="screen, projection"/>
    <link rel="stylesheet" href="${base}/themes/${theme.name}/css/fragment.css" type="text/css" media="screen, projection"/>
    <script type="text/javascript">
    $(document).ready(function(){
		var hasLeftColumn = $.trim($('#body .left-column').html())!='';
		var hasRightColumn = $.trim($('#body .right-column').html())!='';
		if(!hasLeftColumn) {
			$('#body .left-column').remove();
			$('#body .main-column').addClass('span-16');
		}
		if(!hasRightColumn) {
			$('#body .right-column').remove();
			$('#body .main-column').addClass('span-19');
			$('#body .main-column').addClass('last');
		}
		if(!hasLeftColumn && !hasRightColumn) {
			$('#body .left-column').remove();
			$('#body .right-column').remove();
			$('#body .main-column').addClass('span-24');
			$('#body .main-column').addClass('last');
		}
    });
	</script>
</head>
<body>
	<div class="container">
		<div id="head">
			<a href="${base}">
			<img id="logo" src="${base}/themes/${theme.name}/logo.png"/>
			</a>
			<div id="wrapper">
				<osf:position exist="userMenu">
					<div id="user-menu">
					<osf:position render="userMenu"/>
					</div>
				</osf:position>
				<osf:position exist="siteSearch">
				<div id="site-search">
					<osf:position render="siteSearch"/>
				</div>
				</osf:position>
			</div>
			<br class="clear"/>
			<osf:position exist="mainMenu">
			<div id="main-menu">
				<osf:position render="mainMenu"/>
			</div>
			</osf:position>
			<osf:position exist="subMenu">
			<div id="sub-menu">
				<osf:position render="subMenu"/>
			</div>
			</osf:position>
		</div>
		<div id="body">
			<%-- render --%>
			<osf:position exist="leftColumn">
			<div class="span-5 left-column">
				<osf:position render="leftColumn"/>
			</div>
			</osf:position>
			<osf:position exist="mainColumn">
			<div class='span-11 main-column'>
				<osf:position render="mainColumn"/>
			</div>
			</osf:position>
			<osf:position exist="rightColumn">
			<div class="span-8 last right-column">
				<osf:position render="rightColumn"/>
			</div>
			</osf:position>
			<br class="clear"/>
		</div>
		<osf:position exist="foot">
		<div id="foot">
			<osf:position render="foot"/>
		</div>
		</osf:position>
	</div>
</body>
</html>