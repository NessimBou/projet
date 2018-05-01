
/**
 * Initialise une base de donnée virtuelle et la variable env
 * @returns rien
 */
function init() {
    noConnection = false;
    env = new Object();
    SetVirtualDB();
	env.key = -1;
	env.login = -1 ; 
	env.idMessage = -1 ;
	env.msgs = [];
	
}
//plus besoin
/**
 * Initialise une base de donnée virtuelle
 * @returns rien
 */
function SetVirtualDB() {
    localdb = [];
    follows = [];


}
//plus besoin 
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

		