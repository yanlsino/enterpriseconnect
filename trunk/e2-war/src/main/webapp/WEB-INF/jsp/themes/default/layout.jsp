<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://packtag.sf.net" prefix="pack" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="Content-Language" content="zh-CN"/>
	<meta name="robots" content="index, follow"/>
	<meta http-equiv="X-UA-Compatible" content="edge"/>
	<link rel="shortcut icon" type="image/x-icon" href="${base}/themes/${theme.name}/favicon.ico"/>
	<title>${site.title}</title>
	<meta name="Keywords" content="${site.keywords }"/>
	<meta name="Description" content="${site.description}"/>
	
	<pack:script src="/components/jquery/jquery.min.js"/>
	
	<pack:script src="/components/jquery-form/jquery.form.js"/>
	<pack:script src="/components/jquery-validate/jquery.validate.js"/>
	<pack:script src="/components/jquery-validate/jquery.metadata.js"/>
	
	<pack:style src="/components/nyroModal/nyroModal.css"/>
	<pack:script src="/components/nyroModal/jquery.nyroModal.js"/>
	
	<pack:script src="/components/kindeditor/kindeditor.js"/>
	
	<pack:style src="/components/fullcalendar/redmond/theme.css"/>
	<pack:style src="/components/fullcalendar/fullcalendar.css"/>
	<pack:script src="/components/fullcalendar/fullcalendar.min.js"/>
	<pack:script src="/components/fullcalendar/jquery-ui.custom.min.js"/>
	
	<pack:script src="/components/jquery-blockUI/jquery.blockUI.js"/>
	
	<pack:style src="/components/jquery-tageditor/jquery.tag.editor.css"/>
	<pack:script src="/components/jquery-tageditor/jquery.tag.editor.js"/>
	
	<pack:style src="/components/plupload/examples/css/plupload.queue.css"/>
	<pack:script src="/components/plupload/js/plupload.full.min.js"/>
	<pack:script src="/components/plupload/js/jquery.plupload.queue.min.js"/>
	
	<pack:style src="/components/jquery-zTree/zTreeStyle.css"/>
	<pack:script src="/components/jquery-zTree/jquery-zTree-2.5.js"/>
	
	<!-- tooltip 
	<link rel="stylesheet" type="text/css" href="${base}/components/jquery-tooltip/jquery.tooltip.css" />
	<script type="text/javascript" src="${base}/components/jquery-tooltip/lib/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="${base}/components/jquery-tooltip/lib/jquery.dimensions.js"></script>
	<script type="text/javascript" src="${base}/components/jquery-tooltip/jquery.tooltip.js"></script>
	-->
	
	<!--<link rel="stylesheet" type="text/css" href="${base}/components/temp/css/linkinCss1.css"/>-->
	<!--<script type="text/javascript" src="${base}/components/temp/linkinJs1.js"></script>-->
	<!--<link rel="stylesheet" type="text/css" href="${base}/components/temp/linkinCss2.css"/>-->
	<!--<script type="text/javascript" src="${base}/components/temp/linkinJs2.js"></script>-->
	
	<!--<link rel="stylesheet" type="text/css" href="${base}/components/temp/linkinCss1.css"/>-->
	<!--<script type="text/javascript" src="${base}/components/temp/linkinJs1.js"></script>-->
	<!--<link rel="stylesheet" type="text/css" href="${base}/components/temp/linkinCss2.css"/>-->
	<!--<script type="text/javascript" src="${base}/components/temp/linkinJs2.js"></script>-->
	
	<!--theme style -->
	<link rel="stylesheet" type="text/css" href="${base}/themes/${theme.name}/css/layout.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/themes/${theme.name}/css/fragments.css"/>
	<link rel="stylesheet" type="text/css" href="${base}/themes/${theme.name}/css/components.css"/>
		
	<!-- sisley try css and js -->	
	<link rel="stylesheet" type="text/css" href="${base}/themes/${theme.name}/css/osforce.css"/>	
	<script type="text/javascript" src="${base}/themes/${theme.name}/css/dropdown.js"></script>
	
	<!-- TODO move to a extra javascript file -->
	<script type="text/javascript">
	$(document).ready(function(){
		// remove td tag if td tag no sub tag
		if($.trim($('#layout td.left').html())=='') {
			$('#layout td.left').remove();
		}
		if($.trim($('#layout td.main').html())=='') {
			$('#layout td.main').remove();
		}
		if($.trim($('#layout td.right').html())=='') {
			$('#layout td.right').remove();
		}
	});
	</script>

</head>  
<body>
 
<script type="text/javascript">document.body.className += " js ";</script>
 <script>
 LI.define( 'InboxNav.Styles' );
 LI.InboxNav.Styles = 
 ["linkinCss3.css"]
 ;
 LI.define( 'InboxNav.Scripts' );
 LI.InboxNav.Scripts = 
 ["linkinJs3.js"]
 ;
 </script>
<div id="doc4">
 
 <div id="header" class="global-nav">
	 <table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- 基于IE的样式混乱而添加该table -->
	 <tr>
	 	<td>
	 	
	 	<!-- ===============  logo start===================== -->
	 	<osf:ifPositionExist name="userMenu">
	 	<div id="hd" class="wrapper">		
			<osf:positionRender name="userMenu"/>
			<h1 class="logo"><a href="${base}">OSForce</a></h1> 
	 	</div> 	
	 	</osf:ifPositionExist>
	 	<!-- ===============  logo end  ===================== -->
	 	
	 	</td>
	 </tr>
	 </table>
 
 
 
 
<!-- ===============  bar start  ===================== -->
<osf:ifPositionExist name="mainMenu">
<div id="main-menu" class="bar">
 		<div class="wrapper">
 		<!-- ===========  nav    ============== -->
		<osf:positionRender name="mainMenu"/>
 		<!-- ============  search  ============ --> 
 			<osf:ifPositionExist name="siteSearch">
				<osf:positionRender name="siteSearch"/>
			</osf:ifPositionExist> 
			
 		</div>
 		
 	</div>
 	</osf:ifPositionExist>
 <!-- ===============  bar end  ===================== -->

 </div>
 
	<c:if test="${not empty project}">
	<div id="title-bar">
		<h2><a href="${base}/${project.uniqueId}/profile">${project.title}</a></h2>
	</div>
	</c:if>

	<div id="sub-menu">
		<osf:ifPositionExist name="subMenu">
			<osf:positionRender name="subMenu"/>
		</osf:ifPositionExist>
	</div>
	
	
	<!-- 网页主题部分 -->
	<div id="bd">
		<div id="yui-main">

				<div class="yui-b">
					<table id="layout">
						<tr>
							<osf:ifPositionExist name="leftColumn">
							<td class="left" width="${positionConfig.width}">
								<osf:positionRender name="leftColumn"/>
							</td>	
							</osf:ifPositionExist>
							<osf:ifPositionExist name="mainColumn">
							<td class="main">
								<osf:positionRender name="mainColumn"/>
							</td>
							</osf:ifPositionExist>
							<osf:ifPositionExist name="rightColumn">
							<td class="right" width="${positionConfig.width}">
								<osf:positionRender name="rightColumn"/>
							</td>
							</osf:ifPositionExist>
						</tr>
					</table>
				</div>
			</div>	
	
		<div class="yui-b">
				<!-- PUT SECONDARY COLUMN CODE HERE -->
		</div>
	</div>
    
    
	<!--============ footer start ============-->	
	<table width="100%" border="0" cellspacing="50" cellpadding="50" class="footer">
     <tr><td height="10px"></td></tr> 
      <tr>
        <td align="center">             
                            <%=request.getServerName() %>
           <fmt:message key="copyright"/>
           <a href="http://${site.domain}">${site.title}</a> 
           Powered by 
           <a href="#">E2</a>
           <c:if test="${param.test}">
           <a href="#" id="runTest">测试本页响应时间</a>
           </c:if>            
            
    	</td>
      </tr>
    </table>
	<!--============ footer end ============-->
   
	<script type="text/javascript">
		$('#runTest').click(function(){
			var url = document.location.href;
			var start = new Date();
			$.ajax({
				url:url,
				async:false,
				success:function(data){
					var end = new Date();
					var result = end.getTime() - start.getTime();
					alert('响应时间为'+result+'毫秒！');
				}
			});
			return false;
		});
	</script>
 </div>    
</body>
</html> 
 

