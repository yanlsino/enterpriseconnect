YUI().use('node', 'io-base', function(Y){
	Y.on('domready', function(){
		Y.all('.addMemberAction').on('click', function(e){
			sendRequest(e.currentTarget.get('href'));
			e.halt();
		});
		//
		Y.all('.concernAction').on('click', function(e){
			sendRequest(e.currentTarget.get('href'));
			e.halt();
		});
		//
		Y.all('.addMemberAction').on('click', function(e){
			
		});
	});
	
	function sendRequest(uri) {
	    Y.on('io:complete', function(id, o){
	    	window.location.reload();
	    });
		Y.io(uri);
	}
});