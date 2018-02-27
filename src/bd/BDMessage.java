package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import java.util.*;

import org.json.JSONObject;

import serviceTool.*;
import bd.*;

public class BDMessage {
 
	public BDMessage() {
		
	}
	
	
	public static BasicDBObject getIdMessage(String idUser) throws UnknownHostException{
		BasicDBObject retour = new BasicDBObject();
		DBObject ret = null;
		DBCollection col = Database.getCollection("message");
		
		BasicDBObject query = new BasicDBObject();
		query.put("idUser",idUser);
		
		DBCursor cursor = col.find();
		while(cursor.hasNext()){
			//Dans ret, on a les messages du user un par un
			ret = cursor.next();
		}
		retour.put("idMessage", ret.get("_id"));	
		return retour;	//On retourne un BasicDBObject avec l'id du DERNIER message ajouté par l'utilisateur
	}
	
	public static boolean idMessageExist(String idUser, int id) throws UnknownHostException{
		DBCollection col = Database.getCollection("message");
		BasicDBObject query = new BasicDBObject();
		query.put("idUser",idUser);
		query.put("_id",id);
		DBCursor cursor = col.find(query);
		if(cursor.hasNext()){
			cursor.close();
			return true;
		}
		return false;
	}
	
	
	
	
}