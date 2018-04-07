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

Message.prototype.getHTML = function() {
    return "<div id=\"message_" + this.id + "\" \
    class=\"message\"> \
    <div class=\"text_message\"> \
    " + this.text + "<div class=\"infos_message\"> \
    <span> Post de " + this.auteur + "\
    </span></div></div></div>";
}

function Commentaire(id, auteur, text, date) {
    this.id = id;
    this.auteur = auteur;
    this.text = text;
    this.date = date;
}

Commentaire.prototype.getHTML = function() {
    return "<div id=\"message_" + this.id + "\" \
    class=\"message\"> \
    <div class=\"text_message\"> \
    " + this.text + "<div class=\"infos_message\"> \
    <span> Post de " + this.auteur + "\
    </span></div></div></div>";
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