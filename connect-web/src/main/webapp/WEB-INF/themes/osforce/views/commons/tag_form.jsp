<%@ page pageEncoding="UTF-8"%>
<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>
	<div class="fragment-body">
		<p>同时添加多个tag，请用英文逗号(,)分割</p>
		<input id="tags" name="tags"/>
		
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var initialized = false;
	$('#${fragmentConfig.id} #tags').tagEditor({
		items: [${tags}],
		confirmRemoval: true,
		confirmRemovalText: '确定要删除该 Tag 吗？',
		afterAdd: function(tag){
			if(initialized) {
				var url = '${base}/process/commons/tag';
				var params = {
					name:tag,
					linkedId:'${linkedId}',
					entity:'${entity}'
				};
				$.post(url, params);
			}
		},
		afterRemove: function(tag){
			if(initialized) {
				var url = '${base}/process/commons/tag';
				var params = {
					name:tag,
					linkedId:'${linkedId}',
					entity:'${entity}'
				};
				$.get(url, params);
			}
		}
	});
	$('#${fragmentConfig.id} #tags').keypress(function(e){
		var code = (e.keyCode ? e.keyCode : e.which);
		 if(code == 13) { //Enter keycode
		 }
	});
	initialized = true;
});
</script>