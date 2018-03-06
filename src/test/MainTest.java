package test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import bd.*;
import services.*;
import serviceTool.*;

public class MainTest {

	public static void main(String[] args) throws SQLException, JSONException, ClassNotFoundException, IOException {
		
		String login="12345";
		String password="root";
		String mdp = "saezfzef";
		String nom="coucou";
		String prenom="coucou";
		String test = "message test";
		Connection sql= bd.Database.getMySQLConnection();
		DBCollection mango = bd.Database.getCollection("message");
		User user= new User();
//		String key = "190f2edf-7c2f-4dfc-8c66-95ef7cb32637";
//		user.createUser(login, password, nom, prenom);
//		user.login(login, password);
//		System.out.println(BdTools.expireSession("536abb16-3c5c-4737-a2b6-3fa9ffb0882c"));
		
//		System.out.println(services.User.logout(key));
//		System.out.println(bd.BdTools.checkPassword(login, mdp));
		Message message = new Message();
//		BasicDBObject idmessage = new BasicDBObject();
//		idmessage = message.addMessage(login,test);
//		System.out.println(BDMessage.getIdMessage(login));
		message.deleteMessage(login,BDMessage.getIdMessage(login));
	

//	

	//	Pattern pattern = Pattern.compile("PASSWORD(([a-zA-Z]*))");
//		java.util.Date d1 = new java.util.Date();
//		Date dateToday = new java.sql.Date(d1.getTime());
//	
//		System.out.println(dateToday);
//		System.out.println(d1);
////		

		//Database db=new Database("Test");
		
//		Statement lecture = sql.createStatement();
//		ResultSet curseur = lecture.executeQuery("Select t from session where idUser = '"+login+"';");
//		while (curseur.next()){
//			System.out.println(curseur.getString("t"));
//		}
//			System.out.println(curseur.getString("id"));
//			System.out.println(curseur.getString("login"));
//			System.out.println(curseur.getString("nom"));
//			System.out.println(curseur.getString("prenom"));
//			System.out.println(curseur.getString("password"));

//		curseur.close();
//		lecture.close();
//		mango.close();
		sql.close();
//		//System.out.println(bd.UserTools.userExist("3408748"));
//		System.out.println(UUID.randomUUID().toString());

	}

}
