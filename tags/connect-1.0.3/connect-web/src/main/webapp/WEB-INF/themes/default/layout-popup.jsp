<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>

<div class="container">
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
</div>