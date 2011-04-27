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
	function openModalWindow(url) {
		$.modal('', {
			onOpen: function(dialog){
				dialog.overlay.fadeIn('slow', function () {
					dialog.container.slideDown('slow', function () {
						$(this).find('.simplemodal-data').load(url, function(){
							dialog.data.fadeIn('slow');
						});
					});
				});
			}
		});
	}
});