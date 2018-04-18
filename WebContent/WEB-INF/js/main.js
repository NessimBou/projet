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

		