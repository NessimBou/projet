var form = document.querySelector("form");
form.addEventListener("submit",check);


function check(){
    var login  = form.elements.login.value;
    var mdp  = form.elements.password.value;
    console.log(login);
    console.log(mdp)
    if(formulaire_bon(login,mdp)){
        connecte(login,mdp);
    }
}


function formulaire_bon(login,mdp){
    if(login.length <=0){
        func_erreur("login obligatoire");
        return false;
    }
    
    if(mdp <= 0){
        func_erreur("mdp obligatoire");
        return false;
    }
    
    return true;
}


function connecte(login, password) {
    var idUser = 78;
    var key = "ABCD";
    if (!noConnection) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/BoutarHusseinTd2G1/Login",
            data: "login=" + login + "&password=" + password,
            success: function (rep) { connexionResponse(rep); },
            error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
            }
        });
    } else {
        responseConnexion({"key":key,"id":idUSer,"login":login,"follows":[2]});
    }
}


function responseConnexion(res){
    if(res.erre===undefined){
        env.key=res.key;
        env.id =res.id;
        env.login=res.login;
        env.follows = new Set();
    }
    for(var i =1;i <rep.follows.length;i++){
        env.follows.add(rep.follows[i]):
    }
    if(noConnection){
        follows[rep.id] = new Set();
        for(var i = 0 ; i < rep.follows.length;i++){
            follows[rep.id].add(rep.follows[i]);
        }
    }else{
        func_erreur(rep.erreur);
    }
}
