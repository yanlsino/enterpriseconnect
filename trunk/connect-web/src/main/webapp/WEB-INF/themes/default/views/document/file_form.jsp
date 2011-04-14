<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
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
			<c:when test="${empty fileItem.folder}">
			<u:security code="document-folder-add" project="${project}" userRequired="true">
			<div class="notice">
				当前无上传目录，请先添加...
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</div>
			</u:security>
			</c:when>
			<c:otherwise>
			<form:form id="file-form${id}" cssClass="file-form" commandName="fileItem">
				<div>
					<label>上传目录</label>
					<br/>
					<input id="folder${id}" class="text" value="${fileItem.folder.name}" readonly="readonly"/>
					<input id="folderId${id}" type="hidden" value="${fileItem.folderId}"/>
					<a href="#" id="selectAction${id}">选择</a>
				</div>
				<div>
					<input type="file" id="select-file${id}" name="file" />
				</div>
				<div id="file-queue${id}" class="file-queue"></div>
				<div>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="folderId"/>
					<input type="hidden" name="forward" value="/document/file"/>
				</div>
			</form:form>
			</c:otherwise>
		</c:choose>
		<div id="folder-tree${id}"></div>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-upload-iframe', 'json', function(Y){
	var fileQueue = Y.one('#file-queue${id}');
	Y.one('#select-file${id}').on('change', function(e){
		var path = e.currentTarget.get('value');
		//
		var fileForm = Y.one('#file-form${id}');
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			var fileItem = Y.JSON.parse(o.responseText);	
			var html = '<div><span>'+ path +'<span><a class="removeAction">x</a></div>';
			Y.one('#file-queue${id}').insert(html);
			bindEvent();
			e.currentTarget.set('value', '');
		});
		Y.io('${base}/process/commons/attachment', {
			method: 'POST',
			form: {
				id: fileForm,
				upload: true
			}
		});
		e.halt();
	});
	function bindEvent() {
		Y.all('.removeAction').on('click', function(e){
			var parent = e.currentTarget.get('parentNode');
			parent.remove();
			e.halt();
		});
	}
});
</script>

<script type="text/javascript">
YUI().use('yui2-treeview', 'overlay', function(Y){
	var YAHOO = Y.YUI2;
	//
	var overlay = new Y.Overlay({
        srcNode:"#folder-tree${id}",
        visible:false,
        width:"20em",
        align: {
    		node: '#selectAction${id}',
    		points: [Y.WidgetPositionAlign.TL, Y.WidgetPositionAlign.TR]
    	}
    });
	overlay.render();
	//
	Y.one('#selectAction${id}').on('click', function(e){
		overlay.show();
		e.halt();
	});
	//
	var tree = new YAHOO.widget.TreeView("folder-tree${id}", ${folderTree});
	tree.subscribe("labelClick", function(node) {
		Y.one('#folder${id}').set('value', node.label);
		Y.one('#folderId${id}').set('value', node.data.id);
		overlay.hide();
    });
	tree.render();
});
</script>