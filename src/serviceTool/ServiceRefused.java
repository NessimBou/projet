package serviceTool;

import org.json.JSONException;
import org.json.JSONObject;


/**Classe permettant d'envoyer un message si le service est refusé
 * 
 * @author NessimDina
 *
 */
public class ServiceRefused {

	
	public ServiceRefused(){
		
	}
	
	/**Methode envoyant un message si le service est refusé
	 * 
	 * @param m Message d'erreur
	 * @param idError identifiant d'erreur
	 * @return return le message + l'identifiant
	 */
	public static JSONObject serviceRefused(String m,int idError){
		JSONObject reponse = new JSONObject();
		try {
			reponse.put("status","ko");
			reponse.put("message", m);
			reponse.put("idError ", idError);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return reponse;
		
	}
}
