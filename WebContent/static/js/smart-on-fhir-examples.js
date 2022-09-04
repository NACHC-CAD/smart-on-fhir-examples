doGet = function(url, locator) {
	$.ajax({
	  url: url,
	  success: function(data) {
	    $(locator).html(data);
	    alert('Load was performed.');
	  }
	});
}


