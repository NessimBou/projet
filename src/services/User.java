package services;

import org.json.JSONObject;

//MonObjet JSON.toString pour PrintWriter en JSON
public class User {
	
	public User(){
		
	}
	
	public static JSONObject createUser(String nom, String prenom, String user, String mdp){
		JSONObject ret = new JSONObject();
		if(bd.userExist(user)){
			ret.put("Status","KO");
			ret.put("Error","UserExist");
			return ret;
		}else{
			ret.put("Satus","OK");
			bd.addToDBUser(user, mdp, nom, prenom);
			return ret;
		}
	}
	
	public static JSONObject login (String user, String mdp){
		JSONObject ret = new JSONObject();
		if(!bd.userExist(user)){
			serviceTool.ServiceRefused.serviceRefused("User existe deja", 2);
			return ret;
		}else if (!checkpassword(mdp))
			ret.put("Satus","OK");
			bd.addToDBUser(user, mdp, nom, prenom);
			return ret;
		}
	}
	

}
