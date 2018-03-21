/**
 * 
 */

function Message(id,auteur,text,date,comments){
	this.id = id;
	this.auteur=auteur;
	this.text=text,
	this.date=date;
	if(comments == undefined){
		comments=[];
	}
	this.comments=comments
}


function Commentaire(id,text,date){
	this.id=id;
	this.auteur=auteur;
	this.text=text;
	this.date=date;
}


function revival1(key,value){
	if(value.comments != undefined){
		val c = new Message(value.id,value.auteur,value.text,value.date,value.comments);
		return c;
	}elseif(value.text != undefined){
		val c = new Commentaire(value.id,value.text,value.date);
		return c;
	}elseif(key==date){
		var d = new Date(value);
		return d;
	}
	return value;
}

function makeMainPanel(FromId,FromLogin,query){
	env.message=[];
	if(FromId == undefined){
		FromId = 1;
	}
	env.FromId=FromId;
	env.FromLogin = FromLogin;
	console.log(env.FromLogin);
	env.query = query;
	var s = "<header id=\"top\">"; // header mettre celui du Pageprincipale.html
	if(env.FromId < 0){
		s+="<div id=\"title\">Actualites</div>"; // titre mettre <li><a id=profil href="profil.html" class="active">Profil</a></li>
	}else{
		
	}
	
}

Commentaire.prototype.getHTML=function(){
	return "<div id=\"message_"+this.id+"\"" +
	"class =\"message\">" +
	"<div class=\"text.message\">" +
	+this.text+"</div>" +
	"<div class=\"infos_message\">" +
	"<span> Post de <span class = \"link\" onclick=" +
	"javascript.pageUser("+this.auteur.id+")>" +
	"</span></span></div></div>";
}

Message.prototype.getHTML= function(){
	return "<div id=\"message_"+this.id+"\"" +
			"class =\"message\">" +
			"<div class=\"text.message\">" +
			+this.text+"</div>" +
			"<div class=\"infos_message\">" +
			"<span> Post de <span class = \"link\" onclick=" +
			"javascript.pageUser("+this.auteur.id+")>" +
			"</span></span></div></div>";
}