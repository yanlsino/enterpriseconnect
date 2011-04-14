<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>

<table id="layout-popup">
	<tr>
		<osf:ifPositionExist name="leftColumn">
		<td class="left" width="${position.width}">
			<osf:positionRender name="leftColumn" />
		</td>
		</osf:ifPositionExist>
		<osf:ifPositionExist name="mainColumn">
		<td class="main">
			<osf:positionRender name="mainColumn" />
		</td>
		</osf:ifPositionExist>
		<osf:ifPositionExist name="rightColumn">
		<td class="right" width="${position.width}">
			<osf:positionRender name="rightColumn" />
		</td>
		</osf:ifPositionExist>
	</tr>
</table>