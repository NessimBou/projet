/**
 * Constructeur Ami
 * @param id: id user
 * @param friendId : id Friend
 * @param date: date amitié
 * @returns:rien 
 */
function Friend(id, friendId, date) {
    this.id = id;
    this.friendId = friendId;
    this.date = date;
}


function ajout(form) {
	idFriend = form.ami.value;
	console.log(idFriend);
	console.log(env.key);
	var  regex = /[a-zA-Z]/;
	if(!regex.test(idFriend)){
	 	follow(env.key,idFriend);  
	 }
	
}


/**
 * requete Ajax pour ajouter un ami
 * @param cle: key de la connection
 * @param idFriend : id de l'ami
 * @returns:rien
 */
function follow(cle,idFriend){
	//console.log(ici);
    $.ajax({
		type : "get",
		url: "http://localhost:8080/BoutarHusseinTd2G1/addFriend",
		data:"key=" + cle + "&idFriend=" + idFriend,
		success: function(rep){addFriend(rep);},
		error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);},
	});
    
}


/**
 * affiche un message quand on a ajouté un ami
 * @param rep: reponse json de la requete
 * @returns un message
 */

function addFriend(rep){
	var ret = JSON.parse(rep,revival3);
	var status = ret.Status;
	var error = ret.Error;
	console.log("ici");
	console.log(ret);
	if(status == "OK"){
		console.log("ajout ami ok");
		console.log("je suis ici");
		//listFriend(env.key);
		makeAjoutFriend(ret.idFriend,ret.Date);
	}else{
		console.log("je suis la");
		if(status === "KO"){
			console.log("la");
			if(error === "Error"){
				console.log("et la");
				console.log(error);
			}
		}
	}
}

/**
 * requete Ajax pour supprimer un ami
 * @param cle: key de la connection
 * @param idFriend : id de l'ami
 * @returns:rien
 */
function unFollow(cle,idFriend){
    $.ajax({
		type : "get",
		url: "http://localhost:8080/BoutarHusseinTd2G1/removeFriend",
		data:"key=" + cle + "&idFriend=" + idFriend,
		success: function(rep){removeFriend(rep);},
		error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);},
	});
}




/**
 * affiche un message quand on a supprimé un ami
 * @param rep: reponse json de la requete
 * @returns un message
 */
function removeFriend(rep){
	var ret = JSON.parse(rep,revival3);
	var status = ret.Status;
	var error = ret.Error;
	if(status == "OK"){
		console.log("enlever ami ok");
	}else{
		if(status == "KO"){
			if(error === "Error"){
				console.log(error);
		
			}
		}
	}
	
}


/**
 * requete Ajax pour afficher les amis
 * @param cle: key de la connection
 * @returns:rien
 */
function listFriend(cle){
	$.ajax({
		type:"get",
		url : "http://localhost:8080/BoutarHusseinTd2G1/listFriend",
		data:"key="+cle,
		success:function(rep){listFriends(rep);},
		error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);},
	});
	
}


/**
 * affiche la liste des amis
 * @param rep: reponse json de la requete
 * @returns liste des amis 
 */
function listFriends(rep){
	var ret = JSON.parse(rep,revival3);
	var status = ret.Status;
	
	if(status == "OK"){
		
		for(var i = 0 ; i< ret.id;i++){
			var id = ret.idFriend+"_"+i;
			var date = ret.Date+"_"+i;
			console.log(id);
			console.log(date);
			makeAjoutFriend(id,date);
		}		
	}
}




/**Fonction pour parser les valeus de json
*@param key: clé du json
*@param value: valeur du json
*@returns la valeur du json
*/
function revival3(key, value) {
    if (value.comment != undefined) {
        var c = new Message(value.id, value.auteur, value.text, value.date, value.comments);
        return c;
    }
    else if (value.text != undefined) {
        var f = new Commentaire(value.id, value.auteur, value.text, value.date);
        return f;
    }
    else if (key === value.date) {
        var d = new Date(value);
        return d;
    }

    return value;
}