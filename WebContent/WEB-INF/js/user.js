

function deleteUser(login){
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
			success:function(rep){connexionResponse(rep);},
			error:function(jqXHR,textStatus,errorThrow){alert("L'user n'existe pas on ne peut le supprimer");},
		})
	}
}



function creationUser(login, mdp,nom,prenom){
	if(noConnection){
		if(follows.length === 0){
        	var u = {"id":1, "login":login, "mdp" : mdp};
        	follows.add(u);
        	console.log(follows);
        	console.log(follows.length);
    	}else{
        	var u = {"id": follows.length, "login":login, "mdp" : mdp};
        	follows.add(u);
        	console.log(follows);
        	console.log(follows.length);
    	}	
	}else{
		$.ajax({
			type:"GET",
			url:"http://localhost:8080/BoutarHusseinTd2G1/createUser",
			data:"login=" + login + "&password="+mdp+"&nom="+nom+"&prenom="+prenom,
			success:function(rep){connexionResponse(rep);},
			error:function(jqXHR,textStatus,errorThrow){alert("impossible de creer un utilisateur");},
		})
	}
    
}