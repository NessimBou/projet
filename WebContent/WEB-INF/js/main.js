

function init(){
	noConnection=true;
	env=new Object();
	setVirtualdb();
}

function setVirtualdb(){
	localdb=[];
	follows=[];
	var v1= {"id":1,"login":"sly"};
	var v2= {"id":2,"login":"sly"};
	var v3=  {"id":1,"login":"sly"};
	follows[1]= [2,4];
	follows[2]= new Set();
	follows[4]=[1];
	
}

