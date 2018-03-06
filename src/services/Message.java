package services;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import bd.BDMessage;
import bd.BdTools;
import bd.Database;

public class Message {
	
	public Message() {
		
	}
	
	public static BasicDBObject addMessage(String idUser, String Content) throws ClassNotFoundException, SQLException, UnknownHostException{
		BasicDBObject message = new BasicDBObject();

		
		if(BdTools.userExist(idUser)){
			
			DBCollection col = Database.getCollection("message");
			
			//message.put("_id", 'NULL');
			message.put("idUser",idUser);
			GregorianCalendar c= new java.util.GregorianCalendar();
			Date d = c.getTime();
			message.put("date",d);
			message.put("message", Content);
			
			col.insert(message);
		}
		BasicDBObject id = new BasicDBObject();
		id.put("message envoyé","ok");
		return id;
	}
	
	
	public static BasicDBObject deleteMessage(String idUser,String idMessage) throws UnknownHostException{
		BasicDBObject fin = new BasicDBObject();
		
		
		if(BDMessage.idMessageExist(idUser,idMessage)){
			
			DBCollection col = Database.getCollection("message");
			BasicDBObject query = new BasicDBObject();
			query.put("idUser",idUser);
			query.put("_id",idMessage);
			DBCursor cursor = col.find(query);
		
			if(cursor.hasNext()){
				col.remove(cursor.next());
			}		
		}
		fin.put("message remove", "ok");
		return fin;
	}
	
	public static BasicDBObject listMessage(String idUser, String content, String listId) throws UnknownHostException {
		BasicDBObject ret = new BasicDBObject();
		//Cas 1 : idUser, content, listId tous null : on restaure TOUS les message
/*		if(idUser == null & content == null & listId == null) {
			DBCollection col = Database.getCollection("message");
			col.find();
			
		}
*/		return ret;
	}

}
