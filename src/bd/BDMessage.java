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

import org.bson.types.ObjectId;
import org.json.JSONObject;

import serviceTool.*;
import bd.*;

public class BDMessage {
 
	public BDMessage() {
		
	}

	public static Date getDateMessage(ObjectId id) throws UnknownHostException{

		DBObject ret = null;
		DBCollection col = Database.getCollection("message");
		BasicDBObject query = new BasicDBObject();
		query.put("_id",id);
		DBCursor cursor = col.find(query);
		if(cursor.size() != 1){
			System.out.println("erreur pour trouver la data");
			return null;
		}
		while(cursor.hasNext()){
			ret = cursor.next();
			
		}
		return (Date) ret.get("date");
	}
	
	public static ObjectId getIdMessage(String idUser) throws UnknownHostException{
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
		
		return (ObjectId) ret.get("_id");	//On retourne un BasicDBObject avec l'id du DERNIER message ajouté par l'utilisateur
	}
	
	public static boolean idMessageExist(String idUser, ObjectId id) throws UnknownHostException{
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