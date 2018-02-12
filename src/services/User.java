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
	
	/**Methode permettant de créer un user
	 * 
	 * @param nom nom de l'utilisateur
	 * @param prenom prenom de l'utilisateur 
	 * @param login identifiant de l'utilisateur
	 * @param mdp mdp de l'utilisateur 
	 * @return un messsage si l'utilisateur à été crée, un message d'erreur si l'utilisateur existe deja 
	 * @throws JSONException 
	 */
	public static JSONObject createUser(String nom, String prenom, String login, String mdp) throws JSONException{
		JSONObject ret = new JSONObject();
		if(bd.BdTools.userExist(login)){
			ret.put("Status","KO");
			ret.put("Error","UserExist");
			return ret;
		}else{
			ret.put("Satus","OK");
			bd.BdTools.addToDBUser(login, mdp, nom, prenom);
			return ret;
		}
	}
	
	/** Methode permettant de ce login
	 * 
	 * @param login identifiant de l'utilisateur 
	 * @param mdp mdp de l'utilisateur 
	 * @return ouvre la session de l'utilisateur si les données sont bonnes 
	 */
	public static JSONObject login (String login, String password){
		if(login == null || password == null){
			return ServiceRefused.serviceRefused("Wrong Argument", -1);
		}
		boolean is_login = BdTools.userExist(login);
		if(!is_login){
			return ServiceRefused.serviceRefused("L'utilisateur n'existe pas ", 1);	
		}
		boolean check_pwd =  BdTools.checkPassword(login,password);
		if(!check_pwd){
			return ServiceRefused.serviceRefused("Erreur de login ou password", 2);
		}
		JSONObject retour  = new JSONObject();
		String key = BdTools.insertSession(login,false);
		try {
			retour.put("status", "ok");
			retour.put("key",key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retour;	
	

	}
	
	/**expire une session
	 * 
	 * @param login login de l'utilisateur
	 * @throws SQLException 
	 */
	public static void expireSession(int login) throws SQLException{
		
	}

	/**Deconnecte un utilisateur
	 * 
	 * @param login identifiant de l'utilisateur
	 * @return True si il a bien été deconnecté
	 * @throws JSONException
	 */
	public static JSONObject logout(int login) throws JSONException{
		try{
			//fonction à implementer
			expireSession(login);
			return serviceAccepted();
		}
		catch(SQLException e)
		{
			return ServiceRefused.serviceRefused("Erreur SQL (User.logout) " + e, 500);
		}
	}
	

}
