doGet = function(url, locator) {
	$.ajax({
	  url: url,
	  success: function(data) {
		try {
		    $(locator).html(data);
		} 
		catch(error) {}
	  }
	});
}

doPost = function(url,locator,reqObj) {
	success = function(data) {
		try {
		    $(locator).html(data);
		} 
		catch(error) {}
	}
	reqData = JSON.stringify(reqObj);
	$.post(url,reqData,success);
}
