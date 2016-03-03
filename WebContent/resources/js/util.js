
function toFomatorString(date, formator) {
	var returnText = formator.toUpperCase();
	if (returnText.indexOf("YYYY") > -1) {
		returnText = returnText.replace("YYYY", date.getFullYear());
	}

	if (returnText.indexOf("MM") > -1) {
		returnText = returnText.replace("MM", date.getMonth() + 1);
	}

	if (returnText.indexOf("DD") > -1) {
		returnText = returnText.replace("DD", date.getDate());
	}

	if (returnText.indexOf("HH") > -1) {
		returnText = returnText.replace("HH", date.getHours());
	}

	if (returnText.indexOf("MI") > -1) {
		returnText = returnText.replace("MI", date.getMinutes());
	}

	if (returnText.indexOf("SS") > -1) {
		returnText = returnText.replace("SS", date.getSeconds());
	}

	if (returnText.indexOf("SI") > -1) {
		returnText = returnText.replace("SI", date.getMilliseconds());
	}

	return returnText;
}




function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}

function getAbsoluteURL(relativeURL){
	var href=window.location.href;
	var i=href.lastIndexOf("/");
	return href.substr(0,i+1)+relativeURL;
}