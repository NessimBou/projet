package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import serviceTool.ServiceRefused;

public class Friend {

	public Friend(){
		
	}
	
	
	/** Ajoute un ami
	 * @param key clé de l'utilisateur
	 * @param idUser id de l'utilisateur
	 * @param idFriend id de l'ami ajouté
	 * @return KO/OK
	 * @throws ClassNotFoundException
	 */
	public static JSONObject addFriend(String key, String idUser, String idFriend) throws ClassNotFoundException {
		JSONObject ret = new JSONObject();
		if(idUser==null || idFriend == null ) {
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		try {
			if(!bd.BdTools.userExist(idFriend) || !bd.BdTools.userExist(idUser) || !bd.BdTools.keyExist(key)) {
				ret.put("Status", "KO");
				ret.put("Error", "Friend ou User n'existe pas");
				return ret;
			}if(!bd.BdTools.getConnect(idUser)) {
				ret.put("Status", "KO");
				ret.put("Error", "L'utilisateur n'est pas connecté");
				return ret;
			}else{
				ret.put("Status","OK");
				bd.BDFriends.addFriend(key, idUser, idFriend);
				return ret;
			}
		}catch(JSONException | SQLException e){
			e.printStackTrace();			
		}
		return ret;
	}
	
	
	/** Supprime un ami
	 * @param key clé user
	 * @param idUser id de l'utilisateur
	 * @param idFriend id de l'ami
	 * @return KO/OK
	 * @throws ClassNotFoundException
	 */
	public static JSONObject removeFriend(String key, String idUser, String idFriend) throws ClassNotFoundException {
		JSONObject ret = new JSONObject();
		if(idUser==null || idFriend == null || key==null) {
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		try {
			if(!bd.BdTools.userExist(idFriend) || !bd.BdTools.userExist(idUser) || !bd.BdTools.keyExist(key)) {
				ret.put("Status", "KO");
				ret.put("Error", "Friend ou User n'existe pas");
				return ret;
			}if(!bd.BdTools.getConnect(idUser)) {
				ret.put("Status", "KO");
				ret.put("Error", "L'utilisateur n'est pas connecté");
				return ret;
			}else {
				ret.put("Status", "KO");
				bd.BDFriends.removeFriend(key,idUser, idFriend);
				return ret;
			}
		}catch (JSONException | SQLException e) {
			e.printStackTrace();	
		}
		return ret;
	}
	
}
