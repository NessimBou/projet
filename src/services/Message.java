package services;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import bd.BDMessage;
import bd.BdTools;
import bd.Database;

public class Message {
	
	public Message() {
		
	}
	
	public static JSONObject addMessage(String idUser, String Content) throws ClassNotFoundException, SQLException, UnknownHostException, JSONException{
		BasicDBObject message = new BasicDBObject();
		GregorianCalendar c= new java.util.GregorianCalendar();
		Date d = c.getTime();
		int idMessage = 5 + (int)(Math.random() * ((100000 - 5) + 1));
		JSONObject id = new JSONObject();
		if(BdTools.userExist(idUser)){
			
			DBCollection col = Database.getCollection("message");
			
			
			message.put("idUser",idUser);
			message.put("idMessage", idMessage);
			message.put("date",d);
			message.put("message ", Content);
			
			id.put("idMessage", idMessage);
			id.put("idUser",idUser);
			id.put("date",d);
			id.put("message", Content);
			id.put("Status","OK");
			col.insert(message);
		}else{
			id.put("Status", "KO");
		}
		
		return id;
	}
	
	public static JSONObject addCommentaire(String key, int id_message,String content) throws JSONException, UnknownHostException, SQLException{
		JSONObject res = new JSONObject();
		GregorianCalendar c= new java.util.GregorianCalendar();
		Date d = c.getTime();
		if(BdTools.keyExist(key)){
			int idUser = BdTools.getIdUser(key);
			DBCollection col = Database.getCollection("message");
			BasicDBObject commentaire = new BasicDBObject();
			
			//variable pour faire la recherche dans mongo
			DBObject searchQuery = new BasicDBObject();
			DBObject updateQuery = new BasicDBObject();
			
			commentaire.put("idUser", idUser);
			commentaire.put("Date", d);
			commentaire.put("message",content);
			commentaire.put("idMessage",id_message);
			
			searchQuery.put("idMessage",id_message);
			updateQuery.put("$push", new BasicDBObject("commentaires",commentaire));
			col.update(searchQuery, updateQuery);
			res.put("Status", "OK");
			res.put("commentaire",commentaire);
		}else{
			res.put("Status", "KO");
		}
		return res; 
	}
	
	public static JSONObject deleteMessage(String idUser,ObjectId id_message) throws UnknownHostException, JSONException{
		JSONObject fin = new JSONObject();
		
		
		if(BDMessage.idMessageExist(idUser,id_message)){
			
			DBCollection col = Database.getCollection("message");
			BasicDBObject query = new BasicDBObject();
			query.put("idUser",idUser);
			query.put("_id",id_message);
			DBCursor cursor = col.find(query);
		
			if(cursor.hasNext()){
				col.remove(cursor.next());
			}
			cursor.close();
		}
		fin.put("message remove", "ok");
		return fin;
	}

	
	
	
	public static BasicDBObject listMessage(String idUser, String content) throws UnknownHostException {
		BasicDBObject ret = new BasicDBObject();
		DBCollection col = Database.getCollection("message");
		col.find();
		
		//Cas 1 : idUser, content, date , id  null: on restaure tous les messages
		//PAS POSSIBLE
		if ( idUser == null && content == null){
			BasicDBObject query = new BasicDBObject();
			DBCursor cursor = col.find();
			if(!cursor.hasNext()){
				ret.put("erreur", "Message inexistant");
			}else{
				int i =0;
				System.out.println(cursor.size());
				while(cursor.hasNext()){
					ret.put("message_" + i, cursor.next());
					i++;
				}
			}
			cursor.close();
		}
		
		//Cas 2 : content null : on restaure les messages de l'idUSer
		if(content == null && idUser != null){
			BasicDBObject query = new BasicDBObject();
			query.put("idUser",idUser);
			DBCursor cursor = col.find(query);
			if(!cursor.hasNext()){
				ret.put("erreur", "Message Utilisateur introuvable");
			}else{
				System.out.println(cursor.size());
				int i = 0;
				while(cursor.hasNext()){
					ret.put("message_"+i, cursor.next());
					i++;
					//System.out.println("je suis la ");
				}
			}
			cursor.close();
		}
		
		//Cas 3 : IdUSer null : on restaure les messages avec content et idUser
		if (idUser == null && content != null){
			BasicDBObject query = new BasicDBObject();
			query.put("content", content);
			DBCursor cursor = col.find(query);
			if(!cursor.hasNext()){
				ret.put("erreur","message introuvable");
			}else{
				while(cursor.hasNext()){
					ret.put("message", cursor.next());
				}
			}
			cursor.close();
		
		}

		
		return ret;
	}

}
