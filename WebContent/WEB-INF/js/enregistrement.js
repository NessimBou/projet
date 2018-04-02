var form = document.querySelector("form");
form.addEventListener("submit",check_enregistrement(formulaire));



function check_enregistrement(formulaire){
    var nom = formulaire.nom.value;
    var prenom = formulaire.prenom.value;
    var login = formulaire.login.value;
    var email = formulaire.email.value;
    var mdp = formulaire.password.value;
    var check_mdp = formulaire.motdepasse.value;
   
    if(!enregistrement(nom,prenom,login,email,mdp,check_mdp)){
        erreur("enregistrement mauvais");
    }
    console.log("ok");
}

function enregistrement(nom,prenom,email,login,mdp,check_mdp){
    if(nom.value<= 0){
        erreur("nom obligatoire");
        return false;
    }
    
    if(prenom.value<=0){
        erreur("prenom obligatoire");
        return false;
    }
    if(email.value<=0){
        erreur("email obligatoire");
        return false;
    }
    if(login.value<=0){
        erreur("login obligatoire");
        return false;
    }
    if(mdp.value <=0){
        erreur("mot de passe obligatoire");
        return false;
    }
    if(check_mdp.value <=0){
        erreur("mot de passe obligatoire");
        return false;
    }
    
    if(mdp.value != check_mdp.value){
        erreur("mot de passe different");
        return false;
    }
    return true;
}

function erreur(message){
    var msg = "<div id =\"message_erreur\">"+message+"></div>";
    if(message.length <= 0){
        $("form").prepend(msg);
    }
    else{
        $("#erreur").replaceWith(msg);
    }    
    $("#erreur").css({"color":"red"}); 
}

