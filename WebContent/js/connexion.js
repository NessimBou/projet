//form = variable qui recupere les valeurs du formulaire
var form = document.querySelector("form");
//form.addEventListener("submit",check);

/**
 * recupere les valeurs du formulaire  
 * @returns une requete ajax
 */
function check(){
    
    var login  = form.elements.login.value;
    var mdp  = form.elements.password.value;
	
    console.log(login);
    console.log(mdp)
    if(formulaire_bon(login,mdp)){
        connecte(login,mdp);
    }else{
		console.log("error connexion");
	}
}

/**
 * Verifie que les parametres sont bons
 * @param login:login de l'utilisateur
 * @param mdp:mdp de l'utilisateur
 * @returns True/False
 */
function formulaire_bon(login,mdp){
    if(login.length <=0){
        erreur("login obligatoire");
        return false;
    }
    
    if(mdp <= 0){
        erreur("mdp obligatoire");
        return false;
    }
    
    if(login.length >= 10 ){
        erreur("Login ou mdp incorrect");
        return false;
    }
    var  regex = /[a-zA-Z]/;
    if(regex.test(login)){
        erreur("Login ou mdp incorrect");
        return false;
    }
    
    return true;
}

/**envoie une requete pour se connecter
 * 
 * @param login: login user
 * @param password:password user
 * @returns return la page principal de l'user si c'est bon sinon une erreur
 */
function connecte(login, password){
    $.ajax({
    	type: "GET",
        url: "http://localhost:8080/BoutarHusseinTd2G1/login",
        data: "login=" + login + "&pwd=" + password,
		datatype:"json",
        success: function (rep) { connexionResponse(rep,login); },
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}

/** Fonction qui recupre le json de login et si la connection de l'user est ok il return la page principal
 * @param rep: Json reponse de login
 * @returns si ok retourne la page principal sinon envoie une erreur
 */
function connexionResponse(rep,login){
	var ret = JSON.parse(rep,revival1);
	var status = ret.status;
	if(status === "OK"){
		//on stock le login
		env.login = login;
		//on Stock la clé mais je sais pas ou encore
		env.key = ret.key;
		
		console.log(env.login);
		console.log(env.key);
		makeMainPanelPagePrincipal();
	}
	else{
		console.log("erreur");
	}
}

/**Fonction pour parser les valeus de json
*@param key: clé du json
*@param value: valeur du json
*@returns la valeur du json
*/
function revival1(key, value) {
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



//function revival(resp,)	



//Je pense qu'on en a plus besoin vu qu'on est plus en no connection
//function responseConnexion(res){
//    if(res.erre===undefined){
//        env.key=res.key;
//        env.id =res.id;
//        env.login=res.login;
//        env.follows = new Set();
//    }
//    for(var i =1;i <rep.follows.length;i++){
//        env.follows.add(rep.follows[i]);
//    }
//    if(noConnection){
//        follows[rep.id] = new Set();
//        for(var i = 0 ; i < rep.follows.length;i++){
//            follows[rep.id].add(rep.follows[i]);
//        }
//    }else{
//        func_erreur(rep.erreur);
//    }
//}
