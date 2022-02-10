

function gerarMatricula(){
	
	var txt = "ACA";
	
	var random = Math.floor(Math.random() * 9999999); 
	
	document.getElementById("matricula").value = txt + random;
}


$("#formPesquisarAluno").submit(function(e) {
	return;
	
	// e.preventDefault();
	
	$("#formPesquisarAlunoSubmitButton").prop("disabled", true);
	
	var form = $(this);
	
	$.ajax({
		type: form.attr("method"),
		url: form.attr("action"),
		data: form.serialize(),
		success: function(data){
			alert(data);
		},
		error: function(data){
			console.log("error: " + JSON.stringify(data));
		}
		
	});
	
	
	setTimeout(function(){
	  $('#formPesquisarAlunoSubmitButton').prop("disabled", false);
	}, 2000);
	
	
	
});