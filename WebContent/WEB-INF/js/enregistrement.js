//form = variable qui recupere les valeurs du formulaire
var form = document.querySelector("form");


/**
 * recupere les valeurs du formulaire  
 * @returns une requete ajax
 */
function envoye_formulaire() {
    var nom = form.elements.nom.value;
    var prenom = form.elements.prenom.value;
    var login = form.elements.login.value;
    var email = form.elements.email.value;
    var mdp = form.elements.password.value;
    var check_mdp = form.elements.motdepasse.value;  
    var ok = enregistrement(nom,prenom,login,email,mdp,check_mdp);
	
    if(ok){
        
        enregistre(prenom, nom, email, login, mdp );
       
//        enregistre(prenom,nom,email,login,mdp);
        
    } 
}


/**envoie une requete pour créer un utilisateur
 * 
 * @param prenom: prenom user
 * @param nom: nom user
 * @param mail: mail user
 * @param login: login user
 * @param password:password user
 * @returns return la page de connexion si c'est bon sinon une erreur
 */
function enregistre(prenom, nom, mail, login, password) {
	//envoie une requete ajax
	$.ajax({
        //type d'envoi formulaire (get/post)
		type: "get",
		//url de la requete pour créer un utilisateur
        url: "http://localhost:8080/BoutarHusseinTd2G1/createUser",
        //Les arguments de la requete url
        data: "prenom=" + prenom + "&nom=" + nom + "&login=" + login + "&pwd=" + password,
        //type de data que l'on va recevoir
        datatype: "json",
        success: function (rep) { console.log("avant enregistreResponse");
        						  enregistreResponse(rep);
								  console.log("apres enregistreResponse");
								 //makeMainPanelConnexion();
								},
        error: function (jqXHR, textStatus, errorThrow) { console.log("Erreur");},
        });
}	


/** Fonction qui recupere le json de createUser et si la creation de l'user est ok il return la page de connexion
 * @param rep: Json reponse de create user
 * @returns si ok retourne la page de connexion sinon envoie une erreur
 */
function enregistreResponse(rep){
	console.log("debut enregistreResponse");
	var ret = JSON.parse(rep,revival2);
	var status = ret.status;
	if(status === "OK"){
		console.log("Debut if enregistreResponse");
		alert("Inscription ok");
		makeMainPanelConnexion();
	}else{
		console.log(status);
	}
}


/**Fonction pour parser les valeus de json
*@param key: clé du json
*@param value: valeur du json
*@returns la valeur du json
*/
function revival2(key, value) {
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


/*

function responseConnexion1(res){
    if(res.erre===undefined){
        env.key=res.key;
        env.id =res.id;
        env.login=res.login;
        env.follows = new Set();
    }
    for(var i =1;i <rep.follows.length;i++){
        env.follows.add(rep.follows[i]);
    }
    if(noConnection){
        follows[rep.id] = new Set();
        for(var i = 0 ; i < rep.follows.length;i++){
            follows[rep.id].add(rep.follows[i]);
        }
    }else{
        erreur(rep.erreur);
    }
}
*/


/**
 * Verifie que les parametres sont bons
 * @param nom:nom de l'user
 * @param prenom:prenom de l'user
 * @param login:login de l'user
 * @param email:email de l'user
 * @param mdp : mdp de l'user
 * @param check_mdp: mdp de l'user
 * @returns True/false
 */
function enregistrement(nom,prenom,login,email,mdp,check_mdp){
   
    if(nom  <= 0){
        erreur("Nom Obligatoire");
        return false;
    }
    
    if(prenom<=0){
        erreur("prenom obligatoire")
        return false;
    }
    //L'email ne contient pas de @
    var regex = /@upmc.fr$/;
    if(!regex.test(email)){
        erreur("Format email incorrect,doit finir par\"@upmc.fr\"");
        return false;
    }
    
    if(login.length >= 10){
        erreur("Le login ne doit faire que 10 characteres max");
        return false;
    }
    
    //Si le login contient des lettres
    regex = /[a-zA-Z]/;
    if(regex.test(login)){
        erreur("Login ne doit contenir que des chiffres");
        return false;

    }
    
    
    if(mdp <=0){
        erreur("mdp obligatoire");
        return false;
    }
    
    if(check_mdp <=0){
        erreur("mdp obligatoire");
        return false;
    }
    
    if(mdp != check_mdp){
        erreur("Les deux mots de passe ne sont pas les mêmes");
        return false;
    }
    return true;
}


/**
 * Change le css pour mettre une erreur si c'est pas bon
 * @param msg : Le message d'erreur
 * @returns css changé
 */
function erreur(msg){
	var msg_box="<div id=\"msg_erreur\">"+msg+"<br><br></div>";
	var old_msg=$("#msg_erreur");
	if (old_msg.length==0){
		$("form").prepend(msg_box);
	}else{
		old_msg.replaceWith(msg_box);
    }
    $("#msg_erreur").css("color","red");
}    


