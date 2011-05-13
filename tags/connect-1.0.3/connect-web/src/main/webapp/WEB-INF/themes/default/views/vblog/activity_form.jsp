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
					<li><a href="#" id="insertFace${id}">表情</a></li>
					<li><a href="#" id="insertLink${id}">链接</a></li>
					<li><a href="#" id="insertImage${id}">图片</a></li>
				</ul>
				<span>
					<input type="checkbox" class="syncTarget" name="targets" value="sina"/>
					<img src="${base}/static/images/sina_16x16.png">
				</span>
				<span>
					<input type="checkbox" class="syncTarget" name="targets" value="tencent"/>
					<img src="${base}/static/images/tencent_16x16.png">
				</span>
				</c:if>
				<button id="${id}submitButton" type="submit" class="button right">
					<fmt:message key="vblog.activity_form.submit"/>
				</button>
				<br class="clear"/>
			</div>
			<form:hidden path="type"/>
			<form:hidden path="projectId"/>
			<form:hidden path="enteredId"/>
		</form:form>

		<div id="faceLayer${id}" style="display:none">
			<ul class="faces-list">
				<c:forTokens var="face" items="angel,angry,cool,crying,devilish,embarrassed,glasses,kiss,laugh,monkey,plain,raspberry,sad,sick,smile,smile-big,smirk,surprise,tired,uncertain,wink,worried" delims=",">
				<li><a id="${face}"><img src="${base}/static/images/faces/face-${face}.png"/></a></li>
				</c:forTokens>
			</ul>
		</div>
		<div id="linkLayer${id}" style="display:none">
			<a>插入链接</a>
			<input type="text" size="40"/>
		</div>
		<div id="imageLayer${id}" style="display:none">
			<a>插入图片</a>
			<input type="text" size="40"/>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#activity-form${id} .syncTarget').click(function(){
		if($(this).attr('checked')) {
			var target = $(this).val();
			var url = '${base}/process/oauth/authorized?target=' + target;
			$.get(url, function(o){
				if(!o.authorized) {
					var u = '${base}/process/oauth/authorizationUrl?target=' + target;
					$.get(u, function(o){
						showModalWindow(o.authUrl, {width: '800px', height: '600px'});
					})
				}
			});
		}
	});

});
</script>

<script type="text/javascript">
$(document).ready(function(){
	$('#insertFace${id}').aqLayer({
        margin: '0',
        object: '#faceLayer${id}',
        onLoad: function(){
        	$('#linkLayer${id}').data('aqLayer').trigger('close');
        	$('#imageLayer${id}').data('aqLayer').trigger('close');
        }
    });
    $('.faces-list a').click(function(){
		var faceId = $(this).attr('id');
		$('#description${id}').val($('#description${id}').val()+'[face:'+faceId+']');
		$('#faceLayer${id}').data('aqLayer').trigger('close');
		return false;
    });
    $('#insertLink${id}').aqLayer({
        margin: '0',
        object: '#linkLayer${id}',
        onLoad: function(){
	    	$('#faceLayer${id}').data('aqLayer').trigger('close');
	    	$('#imageLayer${id}').data('aqLayer').trigger('close');
	    }
    });
    $('#linkLayer${id} a').click(function(){
        var link = $('#linkLayer${id} input').val();
        if($.trim(link)=='') {
			link = '替换成链接地址';
        }
        $('#description${id}').val($('#description${id}').val()+'[link:'+link+']');
        $('#linkLayer${id}').data('aqLayer').trigger('close');
    });
    $('#insertImage${id}').aqLayer({
        margin: '0',
        object: '#imageLayer${id}',
        onLoad: function(){
	    	$('#faceLayer${id}').data('aqLayer').trigger('close');
	    	$('#linkLayer${id}').data('aqLayer').trigger('close');
	    }
    });
    $('#imageLayer${id} a').click(function(){
        var image = $('#imageLayer${id} input').val();
        if($.trim(image)=='') {
			image = '替换成图片地址';
        }
        $('#description${id}').val($('#description${id}').val()+'[img:'+image+']');
        $('#imageLayer${id}').data('aqLayer').trigger('close');
    });
});
</script>

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