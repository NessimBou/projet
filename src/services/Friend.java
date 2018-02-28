package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import serviceTool.ServiceRefused;

public class Friend {

	public Friend(){
		
	}
	
	
	/** Ajoute un ami
	 * @param key cl� de l'utilisateur
	 * @param idUser id de l'utilisateur
	 * @param idFriend id de l'ami ajout�
	 * @return KO/OK
	 * @throws ClassNotFoundException
	 */
	public static JSONObject addFriend(String key, String idFriend) throws ClassNotFoundException {
		JSONObject ret = new JSONObject();
		if(key==null || idFriend == null ) {
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		try {
			if(!bd.BdTools.keyExist(key)) {
				ret.put("Status", "KO");
				ret.put("Error", "L'utilisateur n'est pas connect�");
				return ret;
		
			}if(!bd.BdTools.userExist(idFriend)) {
				ret.put("Status", "KO");
				ret.put("Error", "Friend n'existe pas");
				return ret;
			}else{
				ret.put("Status","OK");
				bd.BDFriends.addFriend(key, idFriend);
				return ret;
			}
		}catch(JSONException | SQLException e){
			e.printStackTrace();			
		}
		return ret;
	}
	
	
	/** Supprime un ami
	 * @param key cl� user
	 * @param idUser id de l'utilisateur
	 * @param idFriend id de l'ami
	 * @return KO/OK
	 * @throws ClassNotFoundException
	 */
	public static JSONObject removeFriend(String key, String idFriend) throws ClassNotFoundException {
		JSONObject ret = new JSONObject();
		if(key==null || idFriend == null) {
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		try {
			if(!bd.BdTools.userExist(idFriend)) {
				ret.put("Status", "KO");
				ret.put("Error", "Friend ou User n'existe pas");
				return ret;
			}if(!bd.BdTools.keyExist(key)) {
				ret.put("Status", "KO");
				ret.put("Error", "L'utilisateur n'est pas connect�");
				return ret;
			}else {
				ret.put("Status", "ok");
				ret.put("c'est triste",":'(");
				bd.BDFriends.removeFriend(key, idFriend);
				return ret;
			}
		}catch (JSONException | SQLException e) {
			e.printStackTrace();	
		}
		return ret;
	}
	
	public static JSONObject listFriends(String key) throws ClassNotFoundException {
		JSONObject ret = new JSONObject();
		if(key==null) {
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		try {
			
			if(!bd.BdTools.keyExist(key)) {
				ret.put("Status", "KO");
				ret.put("Error", "L'utilisateur n'est pas connect�");
				return ret;
			}else{
				bd.BDFriends.getList(key);
				ret.put("Status","OK");
				return ret;
			}
		}catch(JSONException | SQLException e){
			e.printStackTrace();			
		}
		return ret;
	}
	
}
