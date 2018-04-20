var form = document.querySelector("form");
//form.addEventListener("submit",check);


function check(){
    console.log("je suis la");
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


function connecte(login, password){
    $.ajax({
    	type: "GET",
        url: "http://localhost:8080/BoutarHusseinTd2G1/login",
        data: "login=" + login + "&pwd=" + password,
        success: function (rep) { connexionResponse(rep,login); },
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}


function connexionResponse(rep,login){
	var ret = JSON.parse(rep,revival1);
	var status = ret.status;
	if(status === "OK"){
		env.login = login;
		env.id = ret.id;
		env.key = ret.key;
		makeMainPanelPagePrincipal();
	}
	else{
		console.log("erreur");
	}
}


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




function responseConnexion(res){
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
        func_erreur(rep.erreur);
    }
}
