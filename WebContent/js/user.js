/**
 * envoie une requete ajax pour supprimer l'utilisatueur
 * @param login: le login de l'utilisateur
 * @returns 
 */


function supp(form){
	var login = form.login.value;
	var password =  form.password.value;
	
	deleteUser(login,password);	
}

function deleteUser(login,password){
	//Sert plus a rien on est plus en noConnection

		console.log(login);
		console.log(password);
		$.ajax({
			type:"GET",
			url:"http://localhost:8080/BoutarHusseinTd2G1/deleteUser",
			data:"login="+login+"&password="+password,
			success:function(rep){deleteResponse(rep);},
			error:function(jqXHR,textStatus,errorThrow){alert("L'user n'existe pas on ne peut le supprimer");},
		});
}

function deleteResponse(rep){
	var ret = JSON.parse(rep,revival);
	var status = ret.Status;
	if (status == "OK"){
		console.log("user supprimé");
		alert("User Supprimé");
		makeMainPanelConnexion();
	}
}