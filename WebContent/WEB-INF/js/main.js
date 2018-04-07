function init() {
    noConnection = true;
    env = new Object();
    SetVirtualDB();

}
function SetVirtualDB() {
    localdb = [];
    follows = [];
    var u1 = {"id": 1, "login": "sly"};
    var u2 = {"id": 2, "login": "joe"};
    var u3 = {"id": 3, "login": "luc"};

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


