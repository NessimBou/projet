package bd;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Mongo {
	
	
	public Mongo() throws UnknownHostException{
		
	}
	
	public static BasicDBObject initialisationMongo(){
		BasicDBObject doc = new BasicDBObject();
		doc.put("name","mongo");
		doc.put("type","database");
		doc.put("count",1);
		
		return doc;
		
		
	}
	
	public static Collection MongoConnection(String nom) throws UnknownHostException{
		
		MongoClient mongo = new MongoClient(DBStatic.mongo_url);
		DB db = mongo.getDB(DBStatic.mongo_db);
		Set<String> colls = db.getCollectionNames();
		DBCollection col = db.getCollection(nom);
		return (Collection) col;
		
		
	}

}
