/**
 *Envoie le code de la page d'inscription 
 * @returns inscription.html
 */
function makeMainPanelEnregistrement() {
    $("head").load("./html/inscription.html");
    $("body").load("./html/inscription.html");
}



function makeMainPanelConnexion1(){
	$("body").load("./html/connexion.html");
	$("head").load("./css/connexion.css");
	$("head").load("./html/connexion.html");
}


/**
 *Envoie le code de la page de connexion 
 * @returns connexion.html
 */
function makeMainPanelConnexion() {
//	console.log("la");
   // $("head").load("./html/connexion.html");
//    $("body").load("./html/connexion.html");
	//$("head").load("./css/connexion.css");
	var html = "";
	html += "<div class=\"block_connexion_co\">";
	html += "	<div class=\"block_title_co\">";
	html += "	<h1>";
	html += "		Ouvrir une session";
	html += "	</h1>";
	html += "	<div class=\"block_form_co\">";
	html += "		<form method=\"get\" action=\"javascript:function(){return;}()\">";
	html += "	 		<div class=\"block_input_co\">";
	html += "       		<div class=\"title_form_co\"";
	html += "		    		<span>Login</span>";
    html += "        		</div>";
	html += "				<div class=\"input_form_co\">";
	html += "					<input  type=\"text\" name=\"login\" />";
	html += "       		</div>";
	html += "       		<div class=\"title_form_co\">";
	html += "       			<span>Password</span>";
	html += "       		</div>";
	html += "        		<div class=\"input_form_co\">";
	html += "       			<input type=\"password\" name=\"password\" />";
	html += "        		</div>";
	html += "     		</div>";
	html += "			<input class=\"input_login_co\" type=\"submit\" value=\"Connexion\" name=\"connexion\" onclick=\"check()\" />";
	html += "   	<div class=\"block_links_co\">";
	html += "			<a id=\"lien\" href=\"\">Mot de passe perdu</a>";
	html += "       	<a id=\"inscription\" href=\"./html/inscription.html\">Inscription</a>";
	html += "   	</div>";
	html += "   </form>";
	html += "</div>";
	
	
	$('body').html(html);
	
}
/**
 *Envoie le code de la page principal
 * @returns PagePrincipal.html
 */
function makeMainPanelPagePrincipal() {
    $("head").load("./html/PagePrincipale.html");
    $("body").load("./html/PagePrincipale.html");
}

/**
 *Envoie le code de la page de profil 
 * @returns profil.html
 */
function makeMainPanelProfil() {
    $("body").load("../html/Profil.html");
}

function makeMainPanelEnregistrementErreur(erreur) {
    makeMainPanelEnregistrement();
    console.log(erreur);
    console.log("Avant les erreurs");
    if (erreur === "nom") {
        $("#AideNom").html("Nom obligatoire");
        $("#AideNom").css("color", "red");
        $("#nom").css("display", "block-inline");
    }

    if (erreur === "prenom") {
        $("#AidePrenom")("Prenom Obligatoire");
        $("#AidePrenom").css("color", "red");
        $("#prenom").css("display", "block-inline");
    }

    if (erreur === "login") {
        $("#AideLogin").html("Le Login ne doit contenir que des chiffres");
        $("#AideLogin").css("color", "red");
        $("#login").css("display", "block-inline");

    }

    if (erreur === "email") {
        $("#AideEmail").html("Mail incorrect");
        $("#AideEmail").css("color", "red");
        $("#email").css("display", "block-inline");
    }

    if (erreur === "password") {
        $("#AidePassword").html("Mot de passe incorrect");
        $("#AidePassword").css("color", "red");
        $("#pass").css("display", "block-inline");

    }

    if (erreur === "motdepasse") {
        $("#AideCheckPasswrd").html("Mot de passe incorrect");
        $("#AideCheckPassword").css("color", "red");
        $("#check_pass").css("display", "block_inline");
    }
}

/**
*Affiche pour chaque ami un bloc dans la zone statistique
*/
function makeListFriend(list){
	s = "<div><p>";
	for(var id in list){
		s+= id +" </p></div>";
		s+="<div><p>";
	}
	s+="</p></div>";
	$("#list").html(s);
}
/*
function makeMainPanelEnregistrement(erreur){
    var s="<div class=\"titre\">";
    s+="<h1>Inscription</h1>";
    s+="</div>";
    s=+"<div class=\"block_form\">";
    s+="<form method=\"get\" action=javascript:(function(){return;})()>";
    s+="<div class =\"block_coord\">";
    s+="<dv class=\"title_form\"><span>Nom :</span></div>";
    s=+"<div id=\"nom\" class=\"input_form\">";
    s+="<input type=\"text\" name=\"nom\" class=\"form\" required/>";
    if(erreur === "nom"){
        s+= "<span id=\"AideNom\"></span>";
        $("#AideNom").html("Nom Obligatoire");
        $("#AideNom").css("color","red");
    
    }else{
        s+="<span id=\"AideNom\"></span>";
    }
    s+="</div>";
    s+="</div>";
    s+="<div class=\"block_coord\">";
    s+="<div class = \"title_form\"><span>Prénom :</span></div>";
    s+="<div id=\"prenom\" class=\"input_form\">";
    s+="<input  type=\"text\" name=\"prenom\"  class=\"form\" required />"
    if(erreur === "prenom"){
        s+="<span id=\"AidePrenom\"></span>";
        $("#AidePrenom").html("Prenom obligatoire");
        $("#AidePrenom").css("color","red");
        
    }else{
        s+="<span id =\"AidePrenom\"></span>";
    }
    
    s+="</div>";
    s+="</div>";
    s+="<div class=\"block_coord\">";
    s+="<div class = \"title_form\"><span>Login :</span></div>";
    s+="<div id=\"login\" class=\"input_form\">";
    s+="<input  type=\"text\" name=\"login\"  class=\"form\" required />"
    if(erreur === "login"){
        s+="<span id=\"AideLogin\"></span>";
        $("#AideLogin").html("login ne doit contenir que des chiffrs");
        $("#AideLogin").css("color","red");
        
    }else{
        s+="<span id =\"AideLogin\"></span>";
    }
    
    s+="</div>";
    s+="</div>";
    s+="<div class=\"block_coord\">";
    s+="<div class = \"title_form\"><span>Mail :</span></div>";
    s+="<div id=\"email\" class=\"input_form\">";
    s+="<input  type=\"text\" name=\"email\"  class=\"form\" required />"
    if(erreur === "email"){
        s+="<span id=\"AideEmail\"></span>";
        $("#AideEmail").html("Format Email incorrect");
        $("#AideEmail").css("color","red");
        
    }else{
        s+="<span id =\"AideEmail\"></span>";
    }
    
    s+="</div>";
    s+="</div>";
    s+="<div class=\"block_coord\">";
    s+="<div class = \"title_form\"><span>Password :</span></div>";
    s+="<div id=\"pass\" class=\"input_form\">";
    s+="<input  type=\"password\" name=\"password\" id=\"password\"  class=\"form\" required />"
    if(erreur === "pass"){
        s+="<span id=\"AidePassword\"></span>";
        $("#AidePassword").html("Mot de passe obligatoire");
        $("#AidePassword").css("color","red");
        
    }else{
        s+="<span id =\"AidePassword\"></span>";
    }
    
    s+="</div>";
    s+="</div>";
    s+="<div class=\"block_coord\">";
    s+="<div class = \"title_form\"><span>Confirmez votre password :</span></div>";
    s+="<div id=\"check_pass\" class=\"input_form\">";
    s+="<input  type=\"password\" name=\"motdepasse\" id=\"motdepasse\"  class=\"form\" required />"
    if(erreur === "check_pass"){
        s+="<span id=\"AideCheckPassword\"></span>";
        $("#AideCheckPassword").html("Prenom obligatoire");
        $("#AideCheckPassword").css("color","red");
        
    }else{
        s+="<span id =\"AideCheckPassword\"></span>";
    }
    s+="</div>";
    s+="</div>";
    s+="<class=block_button>";
    s+="<input class=\"button\" type=\"submit\" value=\"Envoyer\" name=\"envoyer\">";
    s+="<input class=\"button\" type=\"submit\" value=\"Annuler\" name=\"annuler\">";
    s+="</div>";
    s+="</form>";
    s+="</div>";
    
    
    $('body').html(s);
}
*/
