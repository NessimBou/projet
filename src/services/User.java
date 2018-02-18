package services;

import serviceTool.ServiceRefused;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BdTools;

//MonObjet JSON.toString pour PrintWriter en JSON

/**classe User permettant de faire tout les services sur user tel que se connecter ect
 * 
 * @author NessimDina
 *
 */
public class User {
	
	public User(){
		
	}
	
	/**Methode permettant de cr�er un user
	 * 
	 * @param nom nom de l'utilisateur
	 * @param prenom prenom de l'utilisateur 
	 * @param login identifiant de l'utilisateur
	 * @param mdp mdp de l'utilisateur 
	 * @return un messsage si l'utilisateur � �t� cr�e, un message d'erreur si l'utilisateur existe deja 
	 * @throws JSONException 
	 */
	public static JSONObject createUser(String login, String mdp,String nom, String prenom) throws JSONException{
		JSONObject ret = new JSONObject();
		try{
			if(bd.BdTools.userExist(login)){
				ret.put("Status","KO");
				ret.put("Error","UserExist");
			}else{
				ret.put("Satus","OK");
				bd.BdTools.addToDBUser(login, mdp, nom, prenom);
			}
		}catch(JSONException | SQLException e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/** Methode permettant de ce login
	 * 
	 * @param login identifiant de l'utilisateur 
	 * @param mdp mdp de l'utilisateur 
	 * @return ouvre la session de l'utilisateur si les donn�es sont bonnes 
	 */
	public static JSONObject login (String login, String password){
		if(login == null || password == null){
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		
		
		try {
			//Verifie que l'utilisateur
			boolean is_login = BdTools.userExist(login);
			if(!is_login){
				return ServiceRefused.serviceRefused("L'utilisateur n'existe pas ", 1);	
			}
		
			//Verifie le bon mdp
			boolean check_pwd =  BdTools.checkPassword(login,password);
			if(!check_pwd){
				return ServiceRefused.serviceRefused("Erreur de login ou password", 2);
			}
		
			//Recupere l'id de l'utilisateur
			int id_user=BdTools.getIdUser(login);
		
			JSONObject retour  = new JSONObject();
			String key = BdTools.insertSession(id_user,false);
		
			retour.put("status", "ok");
			retour.put("key",key);
			return retour;	
			
		} catch (JSONException e) {
			return ServiceRefused.serviceRefused("JSON problem"+e.getMessage(),100);
		}catch(Exception e){
			return ServiceRefused.serviceRefused("Problem..."+e.getMessage(), 10000);
		}
		
		
	

	}
	
	

	/**Deconnecte un utilisateur
	 * 
	 * @param login identifiant de l'utilisateur
	 * @return True si il a bien �t� deconnect�
	 * @throws JSONException
	 */

	public static JSONObject logout(int key, String login){
		if(login == null){
			return ServiceRefused.serviceRefused("Wrong Argument",-1);
		}
		boolean is_login;
		boolean is_key= BdTools.keyExist(key);
		JSONObject fin = new JSONObject();
		try {
			is_login = BdTools.userExist(login);
			if(!is_login){
				return ServiceRefused.serviceRefused("L'utilisateur n'existe pas ", 1);	
			}
			if(!is_key){
				return ServiceRefused.serviceRefused("L'utilisateur n'est pas connecte", 3);
			}
			
			if(BdTools.expireSession(key)){
				fin.put("session", "ferme");
			}else{
				return ServiceRefused.serviceRefused("La session n'a pas expire", 4);
			}
		
			
		}catch(JSONException | SQLException e){
			e.printStackTrace();
		}	
		return fin;
		
	}

}
