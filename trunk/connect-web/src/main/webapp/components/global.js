var settings = {
	simple : {
		toolbar: [
	              ["bold", "italic", "underline"],
	              ["p", "h1", "h2", "h3", "h4", "h5", "h6"],
	              ["link", "unlink", "|", "image"]
	          ]
	}
}

$(document).ready(function(){
	//
	$('.zoom-image').click(function(){
		var url = $(this).find('img').attr('src');
		$.modal('<img src="'+url+'"/>');
		return false;
	});
	//
	$('.loginAction').click(function(){
		var url = $(this).attr('href');
		openModalWindow(url);
		return false;
	});
	//
	$('.addMemberAction').click(function(){
		var url = $(this).attr('href');
		$.get(url,function(){
			window.location.reload();
		});
		return false;
	});
	$('.concernAction').click(function(){
		var url = $(this).attr('href');
		$.get(url,function(){
			window.location.reload();
		});
		return false;
	});
	$('.leaveMessageAction').click(function(){
		var url = $(this).attr('href');
		openModalWindow(url);
		return false;
	});
});

function showModalWindow(url, options) {
	if (window.showModalDialog) {
		window.showModalDialog(url, '', 'dialogWidth:'+options.width+';dialogHeight:'+options.height);
	} else {
		window.open(url, '', 'height='+options.width+',width='+options.height+',toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');
	}
}

function openModalWindow(url, options) {
	$.modal('', {
		minHeight: options!=null?options.minHeight:'auto',
		onOpen: function(dialog){
			dialog.overlay.fadeIn('slow', function () {
				dialog.container.slideDown('fast', function () {
					$(this).find('.simplemodal-data').load(url, function(){
						dialog.data.fadeIn('slow');
					});
				});
			});
		}
	});
}