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
	html += "				 <input class='button' type='submit' value='Retour' name='retour' onClick='makeMainPanelConnexion()'>";
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
	$("head").append("<link>");
	var css = $("head").children(":last");
	css.attr({
      rel:  "stylesheet",
      type: "text/css",
      href: "./css/connexion.css"
	});
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
	html += "       	<a id=\"inscription\" href=\"javascript:makeMainPanelEnregistrement()\">Inscription</a>";
	html +=	"           <a id=\"supprimer\" href=\"javascript:makeMainPanelSupprimerUser()\">Supprimer un compte</a>";			
	html += "   	</div>";
	html += "   </form>";
	html += "</div>";
	$('body').html(html);
	
}

function makeMainPanelSupprimerUser(){
	  
	$("head").append("<link>");
	var css = $("head").children(":last");
	css.attr({
      rel:  "stylesheet",
      type: "text/css",
      href: "./css/connexion.css"
	});
	
	
	var html = "";
	html += "<div class=\"block_connexion_co\">";
	html += "	<div class=\"block_title_co\">";
	html += "	<h1>";
	html += "		Supprimer un user";
	html += "	</h1>";
	html += "	<div class=\"block_form_co\">";
	html += "		<form method=\"get\" action=\"javascript:function(){return;}()\" onSubmit=javascript:supp(this)>";
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
	html += "       		<div class=\"title_form_co\">";
	html += "       			<span>Retapez votre mot de passe</span>";
	html += "       		</div>";
	html += "        		<div class=\"input_form_co\">";
	html += "       			<input type=\"password\" name=\"mdp\" />";
	html += "        		</div>";
	html += "     		</div>";
	html += "			<input class=\"input_login_co\" type=\"submit\" value=\"Suppimer\" name=\"Supprimer\"  />";
	html += "   	</div>";
	html += "   </form>";
	html += "</div>";
	$('body').html(html);
	
}

function makeAjoutFriend(idFriend,date){
	var html ="";
	var key = env.key;
	html += "<li id=\"lala\">";
	html += "<p>Ajout de: "+idFriend+" ,le :"+date+"</p>";
	html += "<span id='delete_m' onclick='unFollow(\""+key+"\",\""+idFriend+"\")'>&times;</span>";
	html += "</li>";
	$("#list").append(html);
	$("#lala").css("color","white");  
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




function makeMainPanelPagePrincipal(){
	var html = "";
	html += "<header id=\"header\">";
    html += "<div class=\"logo\"></div>";       
    html += " <ul class=\"menu\">";       
	html +=	"<li><input class=\"rec\" id=recherche type=search name=\"zone\" placeholder=\"Zone Recherche\"></li>";		
	html +=	"<li><a  id=buttonrecherche href=\"\">Recherche</a></li>";			
	html +=	"</ul>";	
    html +=  "<ul class=\"disconnect\">";
    html +=	 "<form method='get'  action='javascript:(function(){return;})()' onSubmit='javascript:deco()'>";
    html +=  "<li><input type=\"submit\" value=\"Deconnexion\" id=\"deconnexion\"  ></li>";
    html += "</form>";
    html +=  "</ul>";      
    html +=  "</header>";          
//        <!--Zone statistique-->
    html += " <div class=\"stat\">"; 
	html +="<div id=\"friend\">";
	html +="<form method='get'  action='javascript:(function(){return;})()' onSubmit='javascript:ajout(this)'>"; 
	html +="<input id=\"ami\" type=\"text\" name=\"ami\" placeholder=\"IdAmi\">";	
	html +="<input type=\"submit\" value=\"Ajouter\" id=\"ajouter\" >";
	html +="</form>";
	html +="</div>";		
	html +="<div id=\"list\">";		
	html +="</div>";		
    html += "<ul>";  	
    html += "<li>Nombre des messages postes</li>";      	
    html += "<li>Nombre de messages de l'utilisateur</li>";          
    html += "</ul>";      
    html += "</div>"; 
	html +="<form method='get'  action='javascript:(function(){return;})()' onSubmit='javascript:envoie(this)'>"
    html += "<div class=\"block_message\">";  
    html += "<div class=\"newmessage\">";      
    html +="<label for=\"textarea\">votre message:</label>";           
    html +=" <br /> ";          
    html += "<textarea id=\"textarea\" placeholder=\"Ecrivez un message ici\" > </textarea>";          
    html +=  "<input type=\"submit\" value=\"Envoyer\" class=\"send_button\" id=\"buttonenvoyer\">";     
	html += "</from>";
    html +="</div>";
	html +="<div id=\"affichage\">";
	html +=" </div>";
//    <!--section liste message -->
    html +="<div class=\"list_msg\">";               
    html +="<div class=\"box_message\">";              
    html +="<div class=\"username\">";                  
    html +=" <h3>Login</h3>";                     
    html +=" </div>";                 
    html +="<div class=\"content\">";                 
    html +="<textarea id=\"comment\" placeholder=\"Ecrivez un commentaire ici\"></textarea>";                    
    html +="<p class=\"date\"> Date </p>";                   
    html +="<input type=\"submit\" value=\"Répondre\" class=\"rep_button\" id=\"buttonrep\" onclick=\"envoyeMessage()\" >";                  
    html +="</div>";             
    html +=" <div class=\"icon\">";           
    html +="<input type=\"submit\" value=\"+\" class=\"plus\" id=\"buttonplus\">";     
	html +="</div>";                                      
    html +="<div class=\"bloc_comment\">";                  
    html +="<div class=\"username\">";                  
    html +="<h3>Login</h3>";                      
    html +=" </div>";                 
    html +="<div class=\"content\">";                       
    html +=" <p>Contenu blablabla..........</p>";                     
    html +="<p class=\"date\"> Date </p>";                      
    html +="<input type=\"submit\" value=\"Répondre\" class=\"rep_button\" id=\"buttonrep\">";
    html +=" </div>";                  
    html +=" </div>";                          
    html +=" </div>";              
    html +=" </div>";     
    html +=" </div>"; 
	
	$("body").html(html);
	
	$(listFriend(env.key));
	
	$(listMessage(env.login));
	
	$("head").append("<link>");
	var css = $("head").children(":last");
	css.attr({
      rel:  "stylesheet",
      type: "text/css",
      href: "./css/PagePrincipale.css"
	});
        
}
