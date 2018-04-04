function check(formulaire){
    var login  = formulaire.nom.value;
    var mdp  = formulaire.nom.value;
    if(formulaire_bon(login,mdp)){
        connexion(login,mdp);
    } 
}


function formulaire_bon(login,mdp){
    if(login.length <=0){
        erreur("login obligatoire");
        return false;
    }
    
    if(mdp <= 0){
        erreur("mdp obligatoire");
        return false;
    }
    
    return true;
}


