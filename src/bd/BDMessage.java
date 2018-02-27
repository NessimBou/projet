package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

import java.util.*;

import serviceTool.*;
import bd.*;

public class BDMessage {
 
	public BDMessage() {
		
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