var urlcors = "";
let URI = "http://bstk.pythonanywhere.com/paraio"; //https://bisterco.pythonanywhere.com

function showSaldo(data) {
	$("#saldo").html('');
	$("#saldo").append('<p> Valor: ' + parseMoeda(data.valor) + ' - Data: ' + formatTimestamp(data.data) + '</p>'+
			'<input type=hidden name=saldo_atual id=saldo_atual value=' + data.valor + '>');
}

function getSaldo() {
	console.log(">> getSaldo 2 ...");
    var settings = {
      "url": "https://bstk.pythonanywhere.com/paraio/saldo",
      "method": "GET",
      "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
      console.log(response);
    });

}

function _getSaldo() {
	//console.log(">> getSaldo ...");
	//var url = urlcors + "https://paraio.com/v1/saldo/1143180213983645696";
	var url = urlcors + URI + "/saldo";
    console.log(">> getSaldo ..." + url);
			$.ajax({
					method : 'GET',
					//dataType : 'json',
					url : url,
					//crossDomain : true,
					//headers: {
					//	"Authorization": "Anonymous app:dbapp"
					//	},
					tismeout: 0,
					//async: false,
					//jsonpCallback: 'cBack',
					//contentType : "application/json",
					success : function(data) {
						console.log("data:" + data.valor);
						showSaldo(data);
					},
					error : function(e) {
						console.log(e.message);
					},
					done : function (response) {
                      console.log(response)
                    }
				});
}


function showExtrato(data) {
	 $(".extrato").html('');
	 console.log("showExtrato:data.items.length: " +  data.items.length);
	 var html='* exibe ' + data.items.length + ' ultimas linhas somente <br><table border=1>';
	 //html += '<thead><td>ID1</td><td>TYPE1</td><td>ID2</td><td>TYPE2</td></thead>';
	 //html += '<thead><td>TAG</td><td>DIA</td><td>DESCRICAO</td><td>VALOR</td><td>REALIZADO</td></thead>';
 	 html += '<thead><td>TIPO</td><td>DATA</td><td>VALOR</td><td>SALDO ANTERIOR</td><td>SALDO ATUAL</td><td>COMENTARIO</td></thead>';

	for ( var key in data.items) {

		var value = data.items[key];

		html += '<tr>';
		html += '<td>' + value.tipo + '</td>';
		html += '<td>' + formatTimestamp(value.data) + '</td>';
		html += '<td>' + parseNumero2(value.valor) + '</td>';
		html += '<td>' + parseNumero2(value.saldo_anterior) + '</td>';
		html += '<td>' + parseNumero2(value.saldo_atual) + '</td>';
		html += '<td>' + value.comentario + '</td>';
		html += '</tr>';
	}
	html += '</table>';
	$(".extrato").html(html);

}



function getExtrato(dias) {
	console.log(">> getExtrato...");
               //https://paraio.com/v1/movims?sort=properties.data:desc&limit=1
	//var url = "https://paraio.com/v1/movims?sort=properties.data:desc&limit=10";
	//var url = urlcors + "https://paraio.com/v1/movims?sort=timestamp&limit="+dias;
	var url = urlcors + URI + "/movim?dias="+dias;

	$.ajax({
			type : 'GET',
			url : url,
			crossDomain : true,
			headers: { "Authorization": "Anonymous app:dbapp" },
			timeout: 0,
			async: false,
			//jsonpCallback: 'cBack',
			contentType : "application/json",
			dataType : 'json',
			success : function(data) {
				showExtrato(data);
			},
			error : function(e) {
				console.log(e.message);
			}
		});
}

function postMovimento() {

var saldoAtual = $("#saldo_atual").val();

//alert("Saldo Atual:" + saldoAtual);

var valor = $("#valor").val();
var tipo = $("#tipom").val();
var data = new Date($("#data").val() + " 09:00:00").getTime();
var comentario = $("#comentario").val();

var novoSaldo;

if (tipo == 'C') {
	novoSaldo = parseNumero(saldoAtual) + parseNumero(valor);
} else {
	novoSaldo = parseNumero(saldoAtual) - parseNumero(valor);
}

var MovimJ = {
  "type": "movim",
  "tipo": tipo,
  "data": data,
  "saldo_atual": novoSaldo.toString(),
  "valor": valor,
  "saldo_anterior": saldoAtual,
  "comentario": comentario
  };

var SaldoJ = { "valor": novoSaldo, "data" : data};

	//inclui movimentacao
	//let url = urlcors + "https://paraio.com/v1/movims/";
	let url = urlcors + URI + "/movim";
    $.ajax({
	        url : url, // + periodoId + "/links/" + number,
	        type : 'POST',
	        async: false,
	        //headers: { "Authorization": "Anonymous app:dbapp" },
	        contentType: 'application/json',
	        data:  JSON.stringify(MovimJ),
        success: function () {
           //alert('form was submitted');
         },
			error : function(e) {
				console.log(e.message);
			}
       });


    //atualiza saldo
	// url = urlcors + "https://paraio.com/v1/saldo/1143180213983645696"; // + periodoId + "/links/" + number
	url = urlcors + URI + "/saldo"; // + periodoId + "/links/" + number
    $.ajax({
        url : url,
        type : 'PUT',
        async: false,
        //headers: { "Authorization": "Anonymous app:dbapp" },
        contentType: 'application/json',
        data:  JSON.stringify(SaldoJ),
    success: function () {
       //alert('form was submitted');
	   $('.toast').toast('show');
     },
			error : function(e) {
				console.log(e.message);
			}
   });

}

// check CORS
//$(".cors").change(function() {
$(document).on("change","#cors",function() {
	//if (this.checked) {
	//	urlcors = "https://cors-anywhere.herokuapp.com/";
	//	onInit();
	//} else {
		urlcors = "";
	//}
});


//-----------------------------

function onInit() {
	console.log("onInit...");
	//$("html,body").css("cursor","progress");
	console.log($("#cors").val());
	if ($("#cors").val() == 1) { alert("CORS checked."); }
	getSaldo();
	getExtrato(10);
	//$("html,body").css("cursor","default");
}