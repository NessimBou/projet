function deco(){
	var key = env.key;
	console.log(key);
	console.log("je suis la");
	$.ajax({
		type:"get",
		url: "http://localhost:8080/BoutarHusseinTd2G1/logout",
		data: "key="+ key,
		success: function(rep){console.log("ici");
							  deconnexionResponse(rep);},
		error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
		   
	});
}

function deconnexionResponse(rep){
	console.log("et ici");
	var ret = JSON.parse(rep,revival1);
	console.log(rep);
	var status = ret.Status;
	console.log(status);
	if(status == "OK"){
		alert("Au revoir!");
		env.key = -1;
		env.login = -1;
		env.id = -1;
		makeMainPanelConnexion();
		
	}
}