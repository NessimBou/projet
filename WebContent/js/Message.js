var textarea = document.getElementById('textarea').value;

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
    this.auteur = login;
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
    return "<div id=\"message_" + this.id + "\" \
    class=\"message\"> \
    <div class=\"text_message\"> \
    " + this.text + "<div class=\"infos_message\"> \
    <span> Post de " + this.auteur + "\
    </span></div></div></div>";
}


/**
 * Constructeur d'un commentaire
 * @param id: id du commentaire
 * @param auteur: login de l'user
 * @param text : le commentaire 
 * @param date: date d'envoi
 * @returns : rien 
 */
function Commentaire(id, auteur, text, date) {
    this.id = id;
    this.auteur = auteur;
    this.text = text;
    this.date = date;
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



function completeMessages() {
    if (!noConnection) {

    } else {
        var tab = getFromLocalDB(env.fromId, -1, env.minId, 1);
        completeMessagesResponse(JSON.stringify(tab));
    }
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
    $("#message_"+id+"img").remplaceWith("<img src="" onclick=\"javascript replieMessage("+id+")\"/>");
}

function replieMessage(id){
    
}

function newComment(){
    var commentaire = $("#comment").text();
    
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
	if(res.status == "OK"){
		// alert("Message posté")
		$("#comment").val("");
		//recharger la page
		$(makeMainPanel(env.id, env.login));
			
	}
	else{
		alert(res.error)
	}
}


function deleteMessage(idUser,idMessage){
	$.ajax({
    	type: "get",
        url: "http://localhost:8080/BoutarHusseinTd2G1/deleteMessage",
        data: "idUser=" + idUser + "&idMessage=" + idMessage,
		datatype:"json",
        success: function (rep) { messageSuppResponse(rep);},
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}

function messageSuppResponse(rep){
	
}

function listMessage(idUser,content){
	$.ajax({
    	type: "get",
        url: "http://localhost:8080/BoutarHusseinTd2G1/listMessage",
        data: "idUser=" + idUser + "&content=" + content,
		datatype:"json",
        success: function (rep) { listMessageResponse(rep);},
        error: function (jqXHR, textStatus, errorThrow) {
                alert(textStatus);
        },
    });
}

function listMessageResponse(rep){
	
}
