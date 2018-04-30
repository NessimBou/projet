package services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BdTools;
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
				ret.put("Error", "L'utilisateur n'est pas connecte");
				return ret;
		
			}if(!bd.BdTools.userExist(idFriend)) {
				ret.put("Status", "KO");
				ret.put("Error", "Friend n'existe pas");
				return ret;
			}else{
				java.util.Date d1 = new java.util.Date();
				Date dateToday = new java.sql.Date(d1.getTime());
				ret.put("Status","OK");
				ret.put("idFriend",idFriend);
				ret.put("Date",dateToday);
				
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
		JSONObject user = new JSONObject();
		if(key==null) {
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		try {
			
			if(!bd.BdTools.keyExist(key)) {
				ret.put("Status", "KO");
				ret.put("Error", "L'utilisateur n'est pas connecte");
				return ret;
			}else{
				
				ArrayList<String> list = bd.BDFriends.getList(key);
				System.out.println(list.size());
				int i = 0;
				for(String friend : list){
					JSONObject ami = new JSONObject();
					Date date = BdTools.getDate(key, friend);
					//ret.put("id",list.size());
					ami.put("idFriend",friend);
					ami.put("Date",date);
					user.put(""+i, ami);
					i++;
				}
				ret.put("id",list.size());
				ret.put("Status","OK");
				ret.put("friend", user);
				return ret;
			}
		}catch(JSONException | SQLException e){
			e.printStackTrace();			
		}
		return ret;
	}
	
}
