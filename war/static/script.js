function popup(mylink, windowname) {
	if (! window.focus){
		return true;
	}
	
	var href;
	if (typeof(mylink) == 'string') {
		href=mylink;
	}
	else {
		href=mylink.href;
	}
	
	window.open(href, windowname, 'width=400,height=400,scrollbars=yes');
	return false;
}

function disableForms() {
	var limit = document.forms[0].elements.length;
	for (i=0;i<limit;i++) {
		document.forms[0].elements[i].disabled = true;
	}
}