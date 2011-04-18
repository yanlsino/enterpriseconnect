<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<c:choose>
			<c:when test="${not empty user.project.profile.logo}">
			<img id="activity-form-logo${id}" class="activity-form-logo top left thumbnail" src="${base}/logo/download/${user.project.profile.logo.id}/60x60"/>
			</c:when>
			<c:otherwise>
			<img id="activity-form-logo${id}" class="activity-form-logo top left thumbnail" src="${base}/themes/${theme.name}/stock/${user.project.category.code}.png"/>
			</c:otherwise>
		</c:choose>
		<form:form id="activity-form${id}" cssClass="activity-form" 
			action="${base}/process/vblog/activity" commandName="activity">
			<div>
				<form:textarea path="description" id="description${id}"/>
			</div>
			<div>
				<c:if test="${showToolbar}">
				<ul class="toolbar left">
					<li><a href="#" class="insertFace">表情</a></li>
					<li><a href="#" class="insertLink">链接</a></li>
					<li><a href="#" class="insertImage">图片</a></li>
					<!-- 
					<li><a href="#" class="insertVideo">视频</a></li>
					-->
				</ul>
				</c:if>
				<button id="${id}submitButton" type="submit" class="button right">
					<fmt:message key="vblog.activity_form.submit"/>
				</button>
			</div>
			<form:hidden path="type"/>
			<form:hidden path="projectId"/>
			<form:hidden path="enteredId"/>
		</form:form>
		<br class="clear"/>
		
		<div id="overlay${id}" class="overlay">
			<div id="face-select${id}" style="display:none">
				<ul>
				<c:forTokens var="face" items="angel,angry,cool,crying,devilish,embarrassed,glasses,kiss,laugh,monkey,plain,raspberry,sad,sick,smile,smile-big,smirk,surprise,tired,uncertain,wink,worried" delims=",">
					<li><a id="${face}" href="#"><img src="${base}/static/images/faces/face-${face}.png"/></a></li>
				</c:forTokens>
				</ul>
			</div>
			<div id="url-input${id}" style="display:none">
				<input class="text" value="http://"/>
				<button>插入</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#activity-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			var description = formData[0].value;
			if($.trim(description)=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(activity){
			setTimeout(function(){
				window.location.reload();
			}, 500);
		}
	});
});
</script>
<script type="text/javascript">
YUI().use('overlay', function(Y){
	var overlay = new Y.Overlay({
        srcNode:"#overlay${id}",
        visible:false,
        width:"108px",
        align: {
    		node: '.toolbar',
    		points: [Y.WidgetPositionAlign.TL, Y.WidgetPositionAlign.TR]
    	}
    });
	overlay.render();
	//
	Y.all('#overlay${id} a').on('click', function(e){
		var face = e.currentTarget.get('id');
		insertObject('[face:'+face+']');
		hide();
		e.halt();
	});
	Y.one('#url-input${id} button').on('click', function(e){
		var type = Y.one('#url-input${id}').get('title');
		var url = Y.one('#url-input${id} input').get('value');
		insertObject('['+type+':'+url+']');
		hide();
		e.halt();
	})
	//
	var insertFace = Y.one('#activity-form${id} .insertFace');
	var insertLink = Y.one('#activity-form${id} .insertLink');
	var insertImage = Y.one('#activity-form${id} .insertImage');
	var insertVideo = Y.one('#activity-form${id} .insertVideo');
	if(insertFace!=null) {
		insertFace.on('click', function(e){
			overlay.set('width', '108px');
			show('#face-select${id}');
			e.halt();
		});
	}
	if(insertLink!=null) {
		insertLink.on('click', function(e){
			overlay.set('width', '380px');
			Y.one('#url-input${id}').set('title', 'link');
			show('#url-input${id}');
			e.halt();
		});
	}
	if(insertImage!=null) {
		insertImage.on('click', function(e){
			overlay.set('width', '380px');
			Y.one('#url-input${id}').set('title', 'img');
			show('#url-input${id}');
			e.halt();
		});
	}
	if(insertVideo!=null) {
		insertVideo.on('click', function(e){
			overlay.set('width', '380px');
			Y.one('#url-input${id}').set('title', 'video');
			show('#url-input${id}');
			e.halt();
		});
	}
	
	function insertObject(object) {
		var editArea = Y.one('#description${id}');
		var content = editArea.get('value');
		editArea.set('value', content+object);
	}
	
	function hide() {
		Y.one('#face-select${id}').hide();
		Y.one('#url-input${id}').hide();
		overlay.hide();
	}
	
	function show(id) {
		Y.one('#face-select${id}').hide();
		Y.one('#url-input${id}').hide();
		Y.one(id).show();
		overlay.show();
	}
});
</script>