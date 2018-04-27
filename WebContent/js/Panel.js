/**
 *Envoie le code de la page d'inscription 
 * @returns inscription.html
 */
function makeMainPanelEnregistrement() {
	var html = "";
	html += "	<div class='titre'>";
	html += "		<h1>Inscription</h1>";
	html += "	</div>";
	html += "	<div class='bloc_form'>";
	html += "		<form method='get'  action='javascript:(function(){return;})()' onSubmit=javascript:envoye_formulaire(this)>";
	html += "			<div class='block_coord'>";
	html += "				<div class = 'title_form'>";
	html += "					<span>Nom :</span>";
	html +=					"</div>";
	html += "				<div id='nom' class='input_form'>";
	html += "					<input  type='text' name='nom'  class='form' required/>";
	html += "				</div>";
	html += "			</div>";
	
	html += "			<div class='block_coord'>";
	html += "				<div class = 'title_form'>";
	html +=	"					<span>Prenom :</span>";
	html += "				</div>";
	html +=	"				<div id='prenom' class='input_form'>";
	html += "					<input  type='text' name='prenom'  class='form' required />";
	html += "				</div>";
	html += "			</div>";
	
 	html += "			<div class='block_coord'>" ;
	html += "				<div class = 'title_form'><span>Login :</span></div>";
	html += "				<div id='login' class='input_form'>";
	html += "					<input  type='text' name='login'  class='form' required />";
	html += "				</div>";
	html += "			</div>";
	
	html += "			<div class='block_coord'>";
	html += "				<div class = 'title_form'><span>Mail :</span></div>";
	html += "				<div id='email' class='input_form'>";
	html += "					<input  type='text' name='email'  class='form' required/>";
	html += "				</div>";
	html += "			</div>";
	
	html += "			<div class='block_coord'>";
	html += "				<div class = 'title_form'><span>Password :</span></div>";
	html += "				<div id='pass' class='input_form'>";
	html += "					<input  type='password' name='password' id='password' class='form' required/>";
	html += "				</div>";
	html += "			</div>";
	
	html += "			<div class='block_coord'>";
	html += "				<div class = 'title_form'><span>Confirmez votre password :</span></div>";
	html += "				<div id='check_pass' class='input_form'>";
	html += "					<input  type='password' name='motdepasse' id='motdepasse'  class='form' required/>";
	html += "				</div>";
	html += "			</div>";
	
	html += "			<div class=block_button>";
	html += "				<input class='button' type='submit' value='Envoyer' name='envoyer' 	>";
	html += "				 <input class='button' type='submit' value='Annuler' name='annuler'>";
	html += "			</div>";
	html += "		</form>";
	html += "	</div>";

	$('body').html(html);
}




/**
 *Envoie le code de la page de connexion 
 * @returns connexion.html
 */
function makeMainPanelConnexion() {
	console.log("la");
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
	html += "		<form method=\"get\" action=\"javascript:function(){return;}()\" onSubmit=javascript:check(this)>";
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
	html += "			<input class=\"input_login_co\" type=\"submit\" value=\"Connexion\" name=\"connexion\"  />";
	html += "   	<div class=\"block_links_co\">";
	html += "			<a id=\"lien\" href=\"\">Mot de passe perdu</a>";
	html += "       	<a id=\"inscription\" href=\"javascript:makeMainPanelEnregistrement();\">Inscription</a>";
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

