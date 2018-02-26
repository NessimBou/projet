package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import java.util.*;

import serviceTool.*;
import bd.*;

public class BDMessage {
 
	public BDMessage() {
		
	}

	public static BasicDBObject addMessage(String idUser, String Content) throws ClassNotFoundException, SQLException, UnknownHostException{
		BasicDBObject message = new BasicDBObject();
		int idMessage = (int)(Math.random() * 100000000);
		BasicDBObject id = new BasicDBObject();
		if(BdTools.userExist(idUser)){
			DBCollection col = Database.getCollection("message");
			
			message.put("_id", idMessage);
			message.put("idUser",idUser);
			GregorianCalendar c= new java.util.GregorianCalendar();
			Date d = c.getTime();
			message.put("date",d);
			message.put("message", Content);
			//message.put("Commentaire",);
			
			col.insert(message);
				
		}
		return 
	}
	
	
	public static boolean idMessageExist(String login, int id){
		DBCollection col = Database.getCollection("message");
		if(col.find({ idUser:'login',_id:'id'} ==1){
	}
	
	
	
	public static void removeMessage()
}