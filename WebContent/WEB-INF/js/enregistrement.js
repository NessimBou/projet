var form = document.querySelector("form");



function envoye_formulaire(){
    var nom = form.elements.nom.value;
    var prenom = form.elements.prenom.value;
    var login = form.elements.login.value;
    var email = form.elements.email.value;
    var mdp = form.elements.password.value;
    var check_mdp = form.elements.motdepasse.value;  
    var ok = enregistrement(nom,prenom,login,email,mdp,check_mdp);

    if(ok){
        alert("Inscription ok");
        creationUser(login,mdp);
        makeMainPanelConnexion();
//        enregistre(prenom,nom,email,login,mdp);
        
    } 
}



function enregistre(prenom, nom, mail, login, password) {
    if (!noConnection) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/BoutarHusseinTd2G1/createUser",
            data: "prenom=" + prenom + "&nom=" + nom + "&login=" + login + "&pwd" + password,
            datatype: "json",
            success: function (rep) { enregistreResponse(rep); },
            error: function (jqXHR, textStatus, errorThrow) { console.log("Erreur");}
        });
    }
}



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


