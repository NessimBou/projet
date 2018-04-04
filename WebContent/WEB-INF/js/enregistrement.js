var form = document.querySelector("form");
form.addEventListener("submit",function(e){var nom = form.elements.nom.value;
                                           var prenom = form.elements.prenom.value;
                                           var login = form.elements.login.value;
                                           var email = form.elements.email.value;
                                           var mdp = form.elements.password.value;
                                           var check_mdp = form.elements.motdepasse.value;  
                                           var ok = enregistrement(nom,prenom,login,email,mdp,check_mdp)
                                           console.log("ici");
                                           if(!ok){
                                               console.log("la");
                                               makeMainPanelEnregistrement(ok);
                                           }else{
                                               console.log("ok");
                                           }
                                            
});



function check_enregistrement(formulaire){
    var nom = formulaire.nom.value;
    var prenom = formulaire.prenom.value;
    var login = formulaire.login.value;
    var email = formulaire.email.value;
    var mdp = formulaire.password.value;
    var check_mdp = formulaire.motdepasse.value;
   
    /*if(!enregistrement(nom,prenom,login,email,mdp,check_mdp)){
        erreur("enregistrement mauvais");
    }*/
    console.log("ok");
}




function enregistrement(nom,prenom,login,email,mdp,check_mdp){
   
    if(nom  <= 0){
        /*$("#AideNom").html("Nom Obligatoire");
        $("#AideNom").css('color','red');
        $("#nom").css('display','inline-block');
        
        */
        return "nom";
    }
    
    if(prenom<=0){
        /*$("#AidePrenom").html("prenom Obligatoire");
        $("#AidePrenom").css('color','red');
        $("#prenom").css('display','inline-block');
        */
        
        return "prenom";
    }
    //L'email ne contient pas de @
    var regex = /@/;
    if(!regex.test(email)){/*
        $("#AideEmail").html("erreur mail");
        $("#AideEmail").css('color','red');
        $("#mail").css('display','inline-block');*/
        
        return "email";
    }
    
    //Si le login contient des lettres
    regex = /[a-zA-Z]/;
    if(regex.test(login)){
        /*$("#AideLogin").html("erreur de login, Ne doit contenir que des chiffres");
        $("#AideLogin").css('color','red');
        $("#login").css('display','inline-block');*/
        return "login";
    }
    
    
    if(mdp <=0){/*
        $("#AidePassword").html("Mot de passe Obligatoire");
        $("#AidePassword").css('color','red');
        $("#pass").css('display','inline-block');*/
        return "pass";
    }
    if(check_mdp <=0){/*
        $("#AideCheckPassword").html("Mot de passe Obligatoire");
        $("#AideCheckPassword").css('color','red');
        $("#check_pass").css('display','inline-block');*/
        return "check_pass";
    }
    
    if(mdp != check_mdp){
        $("#AideCheckPassword").html("Les mots de passe ne sont pas pareil");
        $("#AideCheckPassword").css('color','red');
        $("#check_pass").css('display','inline-block');
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





