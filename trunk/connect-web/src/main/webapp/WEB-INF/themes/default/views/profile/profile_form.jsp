<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form id="logo-form${id}" class="logo-form" 
			action="${base}/process/commons/attachment" method="post">
			<div>
			<c:choose>
				<c:when test="${not empty profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>
				</c:when>
				<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png"/>
				</c:otherwise>
			</c:choose>
			</div>
			<div>
				<input type="file" id="select-file${id}" name="file">
				<input type="hidden" name="forward" value="/profile/logo"/>
				<input type="hidden" name="profileId" value="${profile.id}"/>
			</div>
		</form>
	
		<form:form id="profile-form${id}" cssClass="profile-form"
			action="${base}/process/profile/profile" commandName="profile">
			<div>
				<label for="title"><fmt:message key="profile.profile_form.title"/></label>
				<br/>
				<form:input path="title"/>
			</div>
			<div>
				<label for="shortDescription"><fmt:message key="profile.profile_form.shortDescription"/></label>
				<br/>
				<form:textarea path="shortDescription" cssClass="shortDescription "/>
			</div>
			<div>
				<label for="description"><fmt:message key="profile.profile_form.description"/></label>
				<form:textarea path="description" id="editor${id}" cssClass="description"/>
			</div>
			<div id="profile-attributes${id}" class="profile-attributes">
			<c:forEach var="entry" items="${profile.attributesMap}">
				<div>
					<input name="labels" value="${entry.key}"/>
					<input name="values" value="${entry.value}"/>
				</div>
			</c:forEach>
			</div>
			<div><a id="addRow${id}" href="#">添加行</a></div>
			<div>
				<button type="submit" class="button">
					<span id="status1${id}">
						<fmt:message key="profile.profile_form.submit"/>
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
				<form:hidden path="logoId"/>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-upload-iframe', 'json', function(Y){
	Y.one('#select-file${id}').on('change', function(e){
		var logoForm = Y.one('#logo-form${id}');
		Y.on('io:complete', function(id, o){
			var profile = Y.JSON.parse(o.responseText);	
			window.location.href='?profileId'+profile.id;
		});
		Y.io(logoForm.get('action'), {
			method: 'POST',
			form: {
				id: logoForm,
				upload: true
			}
		});
		e.halt();
	});
	Y.one('#addRow${id}').on('click', function(e){
		var html = '<div>' + 
				   	   '<input name="labels"/>' + 
				   	   '<input name="values"/>' +
				   '</div>';
		Y.one('#profile-attributes${id}').insert(html);
		e.halt();
	});
});
</script>

<script type="text/javascript">
YUI().use('io-form', 'json', 'yui2-editor', function(Y){
	var YAHOO = Y.YUI2;
	//
	var editor = new YAHOO.widget.Editor('editor${id}', 
	{
	    height: '150px',
	    width: '400px',
	    animate: true,
	    dompath: true,
	    focusAtStart: true,
	    autoHeight: true,
	    collapse: false,
	    toolbar: {
	      collapse: false,
	      draggable: false,
	      buttonType: 'advanced',
	      buttons: [
	          { group: 'textstyle',
	              buttons: [
	                  { type: 'push', label: 'Bold CTRL + SHIFT + B', value: 'bold' },
	                  { type: 'push', label: 'Italic CTRL + SHIFT + I', value: 'italic' },
	                  { type: 'push', label: 'Underline CTRL + SHIFT + U', value: 'underline' },
	              ]
	          },             
	          { type: 'separator' },
	          { group: 'indentlist',
	              buttons: [
	                  { type: 'push', label: 'Indent', value: 'indent', disabled: true },
	                  { type: 'push', label: 'Outdent', value: 'outdent', disabled: true },
	                  { type: 'push', label: 'Create an Unordered List', value: 'insertunorderedlist' },
	                  { type: 'push', label: 'Create an Ordered List', value: 'insertorderedlist' }
	              ]
	          }        
	      ]
	    }
	});
	//
	editor.on('toolbarLoaded', function() {
	    this.on('afterNodeChange', function(o) {
	    	editor.saveHTML();
	    });
	}, editor, true);
	editor.render();
	// 
	var profileForm = Y.one('#profile-form${id}');
	profileForm.on('submit', function(e){
		//
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			try {
				var profile = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href ="?profileId='+ profile.id +'"', 500);
			} catch(e) {
				// alert message
			}
		});
		Y.io(profileForm.get('action'), {
			method: 'POST',
			form: {
				id: profileForm
			}
		});
		e.halt();
	});
});
</script>