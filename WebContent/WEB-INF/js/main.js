function init() {
    noConnection = true;
    env = new Object();
    SetVirtualDB();

}
function SetVirtualDB() {
    localdb = [];
    follows = [];


}

function getFromLocalDB(fromId,minId,maxId,nbMax){
	var tab=[];
	var nb=0;
	var f=new Set();
	if (fromId>0)
		f=follows[fromId];
	for (var i=localdb.length-1;i>=0;i--){
        tab[i]=localdb[i];
    }

	return tab;
}


function creationUser(login, mdp){
    if(follows.length === 0){
        var u = {"id":1, "login":login, "mdp" : mdp};
        follows.add(u);
        console.log(follows);
        console.log(follows.length);
    }else{
        u = {"id": follows.length, "login":login, "mdp" : mdp};
        follows.add(u);
        console.log(follows);
        console.log(follows.length);
    }
}