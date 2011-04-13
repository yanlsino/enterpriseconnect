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
		<form:form id="folder-form${id}" action="${base}/process/document/folder" 
			commandName="folder" cssClass="folder-form${id}">
				<p>
					<label for="name" class="title">目录名</label>
					<br/>
					<form:input path="name" cssClass="text {validate:{required:true, messages:{required:'目录名不能为空！'}}}"/>
				</p>
				<p>
					<label for="root" class="title">根目录</label>
					<br/>
					<form:checkbox id="checkRoot${id}" path="root"/>
				</p>
				<p id="folderChooser">
					<label for="parent" class="title">上一级目录</label>
					<br/>
					<input id="parentFolder${id}" class="text" value="${folder.parent.name}" readonly="readonly">
					<form:hidden path="parentId" id="parentId${id}"/>
					<a href="#" id="selectAction${id}">选择</a>
				</p>
				<p>
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
					<form:hidden path="projectId"/>
				</p>
		</form:form>
		
		<div id="folder-tree${id}">
		</div>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var folderForm = Y.one('#folder-form${id}');
	folderForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.on('io:complete', function(id, o){
			try {
				var folder = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?folderId='+folder.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(folderForm.get('action'), {
			method: 'POST',
			form: {
				id: folderForm
			}
		});
		e.halt();
	});
});
</script>

<script type="text/javascript">
YUI().use('yui2-treeview', 'overlay', function(Y){
	var YAHOO = Y.YUI2;
	//
	Y.one('#checkRoot${id}').on('click', function(e){
		if(e.target.get('checked')) {
			Y.one('#folderChooser').hide();
		} else {
			Y.one('#folderChooser').show();
		}
	});
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
		Y.one('#parentFolder${id}').set('value', node.label);
		Y.one('#parentId${id}').set('value', node.data.id);
		overlay.hide();
    });
	tree.render();
});
</script>