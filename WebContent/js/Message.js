/*
var textarea = document.getElementById('textarea').value;
*/

function envoyeMessage(){
	if(textarea.length > 0){
		id = env.login;
		newMessage(id,textarea);	
	}
}

/**
 * Constructeur d'un message
 * @param id:id du message
 * @param login:login du message
 * @param text: le message
 * @param date:date d'envoie
 * @param comments:commentaire
 * @returns:rien 
 */
function Message(id, login, text, date, comments) {
    this.id = id;
    this.login = login;
    this.text = text;
    this.date = date;
    if (comments === undefined) {
        comments = [];
    }
    this.comments = comments;
}

/**
 * @returns: le code html pour mettre un message
 */
Message.prototype.getHTML = function() {
   	var html="<div class='message'  id='message_"+this.id+"'>"
	html += "	<span id='delete_m' onclick='deleteMessage("+this.login+","+this.id+")'>&times;</span>"
	html += "	<div class='corps_message'>"
	html += "   	<div class='text_message'>"+this.text+"</div>"	
	html += "		<div class='info_message'>" 
	html += "			<span class='auteur_message'>Message ecrit par :" + this.login + "</span>"	
	html += "			<span class='date_message'> Posté le : " + this.date + "</span>"
	html += "		</div>"
	html += "	</div>"		
	html += "	<div class='commentaires'></div>"
	html += "	<div class='show_hide'>"
	html += "	   <div class='show_comments' onclick='javascript:developpeMessage("+ JSON.stringify(this.id) +")'>"
	html += "			<i class='fa fa-sort-down' id='developpe_comments'></i>"
	html += "		</div>"
	html += "	</div>"
	html += " </div>"
	return html;
	
}


/**
 * Constructeur d'un commentaire
 * @param id: id du commentaire
 * @param auteur: login de l'user
 * @param text : le commentaire 
 * @param date: date d'envoi
 * @returns : rien 
 */
function Commentaire(id,idMsg, auteur, text, date) {
    this.id = id;
    this.auteur = auteur;
    this.text = text;
    this.date = date;
	this.idMsg = idMsg;
}

/**
 * @returns le code html pour poster un commentaire
 */
Commentaire.prototype.getHTML = function() {
    return "<div id=\"message_" + this.id + "\" \
    class=\"message\"> \
    <div class=\"text_message\"> \
    " + this.text + "<div class=\"infos_message\"> \
    <span> Post de " + this.auteur + "\
    </span></div></div></div>";
}

/**Fonction pour parser les valeus de json
*@param key: clé du json
*@param value: valeur du json
*@returns la valeur du json
*/
function revival(key, value) {
    /*if (value.comment != undefined) {
        var c = new Message(value.id, value.auteur, value.text, value.date, value.comments);
        return c;
    }
    else if (value.text != undefined) {
        var f = new Commentaire(value.id,value.idMsg, value.auteur, value.text, value.date);
        return f;
    }
    else if (key === value.date) {
        var d = new Date(value);
        return d;
    }*/

    return value;
}



function completeMessagesResponse(rep) {
    var tab = JSON.parse(rep, revival1);
    for (var i = 0; i < tab.length; i++) {
        var m = tab[i];
        alert(m.getHTML());
        env.messages[m.id] = m;
        if (m.id > env.maxId) {
            env.maxId = m.id;
        }
        if (env.minId < 0 || m.id < env.minId) {
            env.minId = m.id;
        }
    }
}

function developpeMessage(id){
    var m =env.msgs[id];
    var e1 = $(".list_msg")+ id +$(".bloc_comment");
    for(var i =0; i < m.length ; i++){
        var c = m.Comments[i];
        e1.append(c.getHTML());
    }
    e1 = $(".list_msg") + id +$(".bloc_comment");
    e1.append();
  /*  $("#message_"+id+"img").remplaceWith("<img src="" onclick=\"javascript replieMessage("+id+")\"/>");*/
}

function replieMessage(id){
    
}

function newComment(){
    var commentaire = $("#comment").text();
    
}

function envoie(form){
	var message = form.textarea.value;
	newMessage(env.login,message);
}

function newMessage(id,textarea){
	$.ajax({
    	type: "get",
        url: "http://localhost:8080/BoutarHusseinTd2G1/addMessage",
        data: "idUser=" + id + "&message=" + textarea,
		datatype:"json",
        success: function (rep) { messageResponse(rep);},
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}

function messageResponse(rep){
	var res = JSON.parse(rep, revival);
	console.log(res);
	if(res.Status == "OK"){
		// alert("Message posté")
		//$("#comment").val("");
		//recharger la page
		$("#textarea").val("");
		var message = new Message(res.idMessage,res.idUser,res.message,res.date,[]);
		$(".box_message").prepend(message.getHTML());
		env.msgs[message.idMessage] = message;
	}else{
		console.log("Erreur message");
	}
		
		
		/*var html = "";
		html +="<div class=\"username\">";                  
    	//html +=" <h3>Login</h3>";                     
    	html += "<p> Message de :"+res.idUser +"</p>";
		html +=" </div>";      
		
		html += "<p>"+res.message+"</p>";
		$("#affichage").html(html);*/
		//makeMainPanelPagePrincipal();
}


function deleteMessage(idUser,idMessage){
	$.ajax({
    	type: "get",
        url: "http://localhost:8080/BoutarHusseinTd2G1/deleteMessage",
        data: "idUser=" + idUser + "&idMessage=" + idMessage,
		datatype:"json",
        success: function (rep) { messageSuppResponse(rep,idMessage);},
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}

function messageSuppResponse(rep,idMessage){
	var res = JSON.parse(rep, revival);
	if(res.Status == "OK"){
		env.msgs[idMessage] = null;
		//maj affichage
		var html = "";
		
		for(var i in env.msgs){
			if(env.msgs[i] != null)
				html = env.msgs[i].getHtml() + html;
		}
		
		$(".box_message").html(html);
	}
	else{
		console.log("Erreur suppression message");
	}
}

function listMessage(idUser,content){
	console.log(idUser);
	console.log(content);
	$.ajax({
    	type: "get",
        url: "http://localhost:8080/BoutarHusseinTd2G1/listMessage",
        data: "idUser=" + idUser + "&content=" + content,
		datatype:"JSON",
        success: function (rep) { listMessageResponse(rep);},
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}

function listMessageResponse(rep){
	//console.log(rep);
	var messagesHtml = "";
	var res = JSON.parse(rep,revival);
	
	var status = res.Status;
	if (status == "OK"){
		env.msgs = [];
		taille = res.taille;
		
		for (var i =0 ; i < taille ;i++){
			var msgMeta = res.messages[i];
			console.log(msgMeta);
			console.log(msgMeta.message);
			//recup des commentaire
			var coms = []
			for(var c in msgMeta.commentaires){
				var com = msgMeta.commentaires[c]
				//console.log(com.date);
				
				coms.push(new Commentaire(com.id, msgMeta.idMessage, com.idUser, com.message, com.date))					
			}
			
			var msg = new Message(msgMeta.idMessage, msgMeta.idUser, msgMeta.message, msgMeta.date, coms);
			/*console.log(msg);
			console.log(msg.id);
			console.log(msg.login);
			console.log(msg.date);
			console.log(msg.text);*/
			
			env.msgs[i]=msg;
			
		}
		for(var key in env.msgs){
			messagesHtml = env.msgs[parseInt(key)].getHTML() + messagesHtml;		
		}
		//ajout bouton charger
		/*messagesHtml += "<a id='charger' onclick='completeMessages("+mid+")'>Plus de posts</a>"*/
	   $('.box_message').append(messagesHtml);
	}else{
		console.log("erreur list");
	}
	
}
