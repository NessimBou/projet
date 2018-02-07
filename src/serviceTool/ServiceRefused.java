package serviceTool;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceRefused {

	public ServiceRefused(){
		
	}
	
	public static JSONObject serviceRefused(String m,int idError){
		JSONObject reponse = new JSONObject();
		try {
			reponse.put("status","ko");
			reponse.put("message", m);
			reponse.put("idError ", idError);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reponse;
		
	}
}
