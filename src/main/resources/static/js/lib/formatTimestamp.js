/**
 *
 */

function formatRetornoData(p) {
	//return p.getDate() + '/' + (p.getMonth()+1) + '/' + p.getFullYear();
	return  ("0" + p.getDate()).slice(-2) + '/' +
	        ("0" + (p.getMonth() + 1)).slice(-2) + '/' +
	        p.getFullYear();
}

function formatTimestamp(ts) {
	var p1 = new Date(ts);
	return formatRetornoData(p1);
}