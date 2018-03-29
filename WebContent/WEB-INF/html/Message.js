/**
 * 
 */

function Message(id, auteur, text, date, comments) {
	this.id = id;
	this.auteur = auteur;
	this.text = text;
	this.date = date;
	if (comments == undefined) {
		comments = [];
	}
	this.comments = comments;
}


function Commentaire(id, text, date, auteur) {
	this.id = id;
	this.auteur = auteur;
	this.text = text;
	this.date = date;
}


Commentaire.prototype.getHTML = function () {
	return "<div id=\"message_" + this.id + "\"" +
	"class =\"message\">" +
	"<div class=\"text.message\">" + this.text + "</div>" +
	"<div class=\"infos_message\">" +
	"<span> Post de <span class = \"link\" onclick=" +
	"javascript.pageUser(" + this.auteur.id + ")>" +
	"</span></span></div></div>";
}

Message.prototype.getHTML = function () {
	return "<div id=\"message_"+  this.id + "\"" +
	"class =\"message\">" +
	"<div class=\"text.message\">" + this.text + "</div>" +
	"<div class=\"infos_message\">" +
	"<span> Post de <span class = \"link\" onclick=" +
	"javascript.pageUser(" + this.auteur.id + ")>" +
	"</span></span></div></div>";
}




function revival(key, value) {
	if (value.comments != undefined) {
		var c = new Message(value.id, value.auteur, value.text, value.date, value.comments);
		return c;
	}else if (value.text != undefined) {
		var l = new Commentaire(value.id, value.text, value.date);
		return l;
	}else if (key == value.date) {
		var d = new Date(value);
		return d;
	}
	return value;
}


JSON.parse(json_texte, revival);

function makeMainPanel(fromId, fromLogin, query) {
	var env;
	env.msgs = []
	if (fromId==undefined) {
		fromId = -1;
	}
	env.fromId = fromId;
	env.fromLogin = fromLogin;
	console.log(env.fromLogin);
	env.query = query;
	var s = "<header id=top";
	if (env.fromId < 0) {
		s += "<div id=title> Actualités </div>";
	}else {
		if (!env.follows.has(env.fromId)) {
			s += "<div id=\"title\"> Page de " + fromLogin + "</div>"
			s += "<div class = \"add\"> <img src=\"Images/add.png\" title=\"suivre\" onclick='Javascript:follow()'></div></div>";
		}else {
			s += "<div id=\"title\"> Page de " + fromLogin + "</div>"
			s += "<div class = \"add\"> <img src=\"Images/remove.png\" title=\"suivre\" onclick='Javascript:stopfollow()'></div></div>";
		}
	}
	s += "</div> <div id=\"connect\"> <span id=\"log\" pageUser(" + env.id + "," + env.login + ")\")>"
	s += "<div class = \"add\"> <img src=\"Images/disco.png\" title=\"suivre\" onclick='Javascript:disconnect()'></div></div>";
}

function setVirtualdb() {
	var localdb = [];
	var follows = [];
	var v1 = {"id" : 1, "login" : "sly"};
	var v2 = {"id" : 2, "login" : "sly"};
	var v3 =  {"id" : 1, "login" : "sly"};
	follows[1] = [2,4];
	follows[2] = new Set();
	follows[4] =[1];
	
	var message = new Message("3520520", "moi", "salut", Date);

}

function init() {
	noConnection = true;
	var env = new Object();
	setVirtualdb();
}







