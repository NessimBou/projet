/**
 * envoie une requete ajax pour supprimer l'utilisatueur
 * @param login: le login de l'utilisateur
 * @returns 
 */
function deleteUser(login){
	//Sert plus a rien on est plus en noConnection
	if(noConnection){
		if(follow.contains(login)){
			follow.remove(login)
			console.log(follow)	
		}else{
			alert("L'user n'existe pas on ne peut le supprimer");
		}
	}else{
		$.ajax({
			type:"GET",
			url:"http://localhost:8080/BoutarHusseinTd2G1/deleteUser",
			data:"login="+login,
			success:function(rep){deleteResponse(rep);},
			error:function(jqXHR,textStatus,errorThrow){alert("L'user n'existe pas on ne peut le supprimer");},
		})
	}
}

function deleteResponse(rep){
	var ret = JSON.parse(rep,revival);
	var status = ret.status;
	if (status === "OK"){
		console.log("user supprimé");
	}
}