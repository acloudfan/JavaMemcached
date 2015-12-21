// index.js

// request message on server
//xhrGet("api/hello", function(responseText){
//	// add to document
//	var mytitle = document.getElementById('message');
//	mytitle.innerHTML = responseText;
//
//}, function(err){
//	console.log(err);
//});

/**
 * Add the quote to Memcached
 */
function postQuote(){
	quote = $('#quote').val().trim();
	expiry = 3000;
	
	if(quote == ''){
		alert("Blank not accepted as a quote");
	} else {
		
		var json = {"quote":quote, "expiry":expiry};
		
//		api_call('api/quote','POST','{"quote":"'+quote+'"}',function(dat){
		api_call('api/quote','POST',JSON.stringify(json),function(dat){
			$('#qod').text(dat.quote);
			$('#setat').text('Last Set @ '+(new Date()).toTimeString());
		});
	}
}

function getQuote(){
		api_call('api/quote','GET','',function(dat){
			//alert(dat.quote);
			$('#qod').text(dat.quote);
			$('#refreshedat').text('Last Refreshed @ '+(new Date()).toTimeString());//+getDate().toTimeString())
		});
}

//utilities
//function createXHR(){
//	if(typeof XMLHttpRequest != 'undefined'){
//		return new XMLHttpRequest();
//	}else{
//		try{
//			return new ActiveXObject('Msxml2.XMLHTTP');
//		}catch(e){
//			try{
//				return new ActiveXObject('Microsoft.XMLHTTP');
//			}catch(e){}
//		}
//	}
//	return null;
//}



function api_call(url, type, data, callback) {
	  $.ajax(
	    {
	      url: url,
	      type: type,
	      processData: false,
	      contentType: 'application/json; charset=utf-8',
	      data: JSON.stringify(data),
	      dataType: 'json',
	      async: true,
	      complete: function (result) {
	      	callback(result.responseJSON);
	      }
	    });
	}
//function xhrGet(url, callback, errback){
//	var xhr = new createXHR();
//	xhr.open("GET", url, true);
//	xhr.onreadystatechange = function(){
//		if(xhr.readyState == 4){
//			if(xhr.status == 200){
//				callback(xhr.responseText);
//			}else{
//				errback('service not available');
//			}
//		}
//	};
//	xhr.timeout = 3000;
//	xhr.ontimeout = errback;
//	xhr.send();
//}
//function parseJson(str){
//	return window.JSON ? JSON.parse(str) : eval('(' + str + ')');
//}
//function prettyJson(str){
//	// If browser does not have JSON utilities, just print the raw string value.
//	return window.JSON ? JSON.stringify(JSON.parse(str), null, '  ') : str;
//}

