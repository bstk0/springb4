/**
 * mylib - 10.02 - v1
 */

//-------- NUMERO - INICIO

 function parseNumero(str) {
	 if (str == "") str = "0";
	 return parseFloat(str.replace(',', '.'));
 }

 //duas decimais e sem moeda
 function parseNumero2(str) {
	 //console.log("typeof(str):" + typeof(str));
	 var _num;
	 if (typeof(str) == "string")
		 _num = parseNumero(str)
	 else
		 _num = str
	 return parseFloat(_num.toFixed(2)).toLocaleString('pt-BR');
 }


 function parseMoeda(totalPago) {
	 return parseFloat(totalPago.toFixed(2)).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
 }
//-------- NUMERO - FIM

// -------- DATA - INICIO

 function formatRetornoDataUS(p) {
		//return p.getDate() + '/' + (p.getMonth()+1) + '/' + p.getFullYear();
		return  ("0" + (p.getMonth() + 1)).slice(-2) + '/' +
		        ("0" + p.getDate()).slice(-2) + '/' +
		        p.getFullYear();
}

// -------- DATA - FIM

 function _getUrlVars() {
	    var vars = {};
	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
	        vars[key] = value;
	    });
	    return vars;
}

 function GetURLParameter(parameter) { //, defaultvalue){
	 var urlparameter;
	    if(window.location.href.indexOf(parameter) > -1){
	        urlparameter = _getUrlVars()[parameter];
	        }
	    return urlparameter;
}

/*
function GetURLParameter(sParam) {
	console.log("GetURLParameter.sParam:" + sParam);
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
    //return "";
}
*/