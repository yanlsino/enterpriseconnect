<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
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
	<c:choose>
		<c:when test="${empty user}">
			<div class="notice">回答问题，请先 <a href="#" class="loginAction">登录</a></div>
		</c:when>
		<c:otherwise>
		<form:form id="answer-form${id}" action="${base}/process/knowledge/answer" 
			commandName="answer" cssClass="answer-form">
			<div>
				<form:textarea path="content" id="editor${id}" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">
					<span id="status1${id}">
						提交
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="questionId"/>
				<br class="clear"/>
			</div>
		</form:form>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
YUI().use('yui2-editor', function(Y){
	var YAHOO = Y.YUI2;
	//
	var editor = new YAHOO.widget.Editor('editor${id}', 
	{
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
});
</script>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var answerForm = Y.one('#answer-form${id}');
	answerForm.on('submit', function(e){
		var content = Y.one('#content${id}').get('value'); 
		if(content.trim()!='') {		
			Y.one('#status1${id}').hide();
			Y.one('#status2${id}').show();
			//
			Y.on('io:complete', function(id, o){
				try {
					var answer = Y.JSON.parse(o.responseText);
					setTimeout('window.location.href="?answerId='+answer.id+'"', 500);
					Y.one('#content${id}').set('value', '');
				} catch(e) {
					// TODO alert message username or password invalid
				}
			});
			Y.io(answerForm.get('action'), {
				method: 'POST',
				form: {
					id: answerForm
				}
			});
		}
		e.halt();
	});
});
</script>