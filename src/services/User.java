package services;

import serviceTool.ServiceRefused;

import java.sql.SQLException;
//import java.util.ArrayList;


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
	 * @throws ClassNotFoundException 
	 */
	public static JSONObject createUser(String login, String mdp,String nom, String prenom) throws JSONException, ClassNotFoundException{
		JSONObject ret = new JSONObject();
		
		if(login == null || mdp == null || nom == null || prenom == null){
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			if(!verif(login)){
				ret.put("le login doit etre une suite de nombre","ko");
				return ret;
			}
			if(bd.BdTools.userExist(login)){
				ret.put("Status","KO");
				ret.put("Error","UserExist");
			}else{
				ret.put("Status","OK");
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
	public static JSONObject login (String login, String password) throws NumberFormatException{
		
		if(login == null || password == null){
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Verifie que l'utilisateur existe 
			boolean is_login = BdTools.userExist(login);
			if(!is_login){
				return ServiceRefused.serviceRefused("L'utilisateur n'existe pas ", 1);	
			}
		
			
			//Verifie le bon mdp
			boolean check_pwd =  BdTools.checkPassword(login,password);
			if(!check_pwd){
				return ServiceRefused.serviceRefused("Erreur de login ou password", 2);
			}
		
			//parse int cree une erreur.
			int id_user = Integer.parseInt(login);
	
			JSONObject retour  = new JSONObject();
			String key = BdTools.insertSession(id_user,true);
			
			//verifier si il est bien connecté
			//je pense que le getConnect est optionnel si on a la cle a voir 
			if(key != null){
				retour.put("status", "ok");
				retour.put("key",key);
				return retour;
			}else{
				retour.put("status", "KO");
				retour.put("Erreur cle ou connection", -1);
				return retour;
			}
				
		}catch(NumberFormatException e){
			return ServiceRefused.serviceRefused("erreur parseint"+e.getMessage(),10);
		
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws JSONException
	 */
	public static JSONObject logout(String key) throws SQLException, ClassNotFoundException{
		if(key == null){
			return ServiceRefused.serviceRefused("Wrong Argument",-1);
		}
		JSONObject fin = new JSONObject();
		try {
			if(!BdTools.keyExist(key)){
				return ServiceRefused.serviceRefused("L'utilisateur n'est pas connecte", 3);
			}
			//on verifie qu'il n'est pas root puis ensuite on verifie qu'il a bien expir� la session
			if(BdTools.isRoot(key)){
				if(BdTools.expireSession(key)){
					fin.put("session", "ferme");
					fin.put("au Revoir", "A la prochaine");
				}else{
					return ServiceRefused.serviceRefused("La session n'a pas expire", 4);
				}	
			}else{
				return ServiceRefused.serviceRefused("L'utilisateur est un root", 4);	
			}
		}catch(JSONException | SQLException e){
			e.printStackTrace();
		}	
		return fin;
		
	}
	
	public static JSONObject SupprimerUser(String login) throws ClassNotFoundException{
		
		if(login == null){
			return ServiceRefused.serviceRefused("Wrong argument" , -1);
		}
		JSONObject fin = new JSONObject();
		try{
			if(!BdTools.userExist(login)){
				return ServiceRefused.serviceRefused("L'utilisateur n'existe pas ", 3);
			}
			if(BdTools.DeleteUser(login)){
				fin.put("utilisateur","supprimer");
			}else{
				return ServiceRefused.serviceRefused("l'utilisateur n'a pas ete supprime",4);
			}
		}catch(JSONException |SQLException e){
			e.printStackTrace();
		}
	
		return fin;
		
	}
	
	
	public static JSONObject DeconnectionUrgente(String key){
	
		if(key == null){
			return ServiceRefused.serviceRefused("Wrong argument",-1);
		}
		
		JSONObject fin = new JSONObject();
		try {
			if(!BdTools.keyExist(key)){
				return ServiceRefused.serviceRefused("L'utilisateur n'est pas connecte", 3);
			}
			//on verifie qu'il n'est pas root puis ensuite on verifie qu'il a bien expir� la session
			if(BdTools.deconnection(key)){
				fin.put("session", "ferme");
				fin.put("au Revoir", "A la prochaine");
			}else{
				return ServiceRefused.serviceRefused("La session n'a pas expire", 4);
			}	
		}catch(JSONException | SQLException e){
			e.printStackTrace();
		}	
		
		return fin;
	}
	
	
	public static boolean verif(String login){
		try{
			int log = Integer.parseInt(login);
			return true;
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return false;
		
	}

}
