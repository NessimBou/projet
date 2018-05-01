package test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import bd.*;
import services.*;
import serviceTool.*;

public class MainTest {

	public static void main(String[] args) throws SQLException, JSONException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
		
		String login="1";
		String password="root";
		String nom="coucou";
		String prenom="coucou";
		String message = "message test";
		String commentaire = "est ce que c'est bon?";		
//		
//		String login1="12345";
//		String password1="root";
//		String nom1="coucou";
//		String prenom1="coucou";
//		
//		String login2="123456";
//		String password2="root";
//		String nom2="coucou";
//		String prenom2="coucou";
//		
//		String login3="123457";
//		String password3="root";
//		String nom3="coucou";
//		String prenom3="coucou";
//		
//		
//
//		
		//User.createUser(login, password, nom, prenom);
		User.login(login,password);
		Connection sql= bd.Database.getMySQLConnection();
		DBCollection mango = bd.Database.getCollection("message");
		User user= new User();
		String key = BdTools.getKey(login);
//		System.out.println(user.logout(key));
	
		
		//		
//		Friend.removeFriend(key, login);
//		Friend.removeFriend(key, login2);
//		Friend.removeFriend(key, login3);
//		Friend.removeFriend(key, login1);
//		
//		user.DeconnectionUrgente(key);
//		BdTools.DeleteUser(login1);
//		BdTools.DeleteUser(login2);
//		BdTools.DeleteUser(login3);
//		BdTools.DeleteUser(login);
//		
//		user.createUser(login, password, nom, prenom);
//		user.createUser(login1, password1, nom1, prenom1);
//		user.createUser(login2, password2, nom2, prenom2);
//		user.createUser(login3, password3, nom3, prenom3);
//		
//		user.login("1", "root");
//		key = BdTools.getKey(login);
//		
//		
//		Friend.addFriend(key,login1);
//		Friend.addFriend(key,login2);
//		Friend.addFriend(key,login3);
//		System.out.println(Friend.listFriends("e8f9f52d-6987-41eb-a2c6-a16b02ec85e8"));
//		
		
//		String key = "190f2edf-7c2f-4dfc-8c66-95ef7cb32637";
//		user.createUser(login, password, nom, prenom);
//		user.login(login, password);
//		System.out.println(BdTools.expireSession("536abb16-3c5c-4737-a2b6-3fa9ffb0882c"));
		
//		System.out.println(services.User.logout(key));
//		System.out.println(bd.BdTools.checkPassword(login, mdp));
//		Message message = new Message();
		ObjectId idmessage = new ObjectId();

	//	System.out.println(Message.addMessage(login,message));
		idmessage = BDMessage.getIdMessage(login);
		System.out.println(idmessage);
		int id = BDMessage.getId(idmessage);
		System.out.println(id);
		Commentaire.deleteCommentaire("1", "88070");
		System.out.println(user.logout(key));
		
		//		String test = " ezjfhqekhn";
//		Message.addMessage("1",test);
//		test = " zkefjaezo";
//		Message.addMessage(login2,test);
//		test = " blblbl";
//		Message.addMessage(login2,test);
//		test = " salut";
//		Message.addMessage(login1,test);
//		test = " coucou";
//		Message.addMessage(login1,test);
//		test = " one piece";
//		Message.addMessage(login1,test);
//		test = " rouroruou";
//		Message.addMessage(login3,test);
//		test = " ghe";
//		Message.addMessage(login3,test);
//		Date date = null;
		String comment = null;
//		String log = null;
//		
		System.out.println("---------------------");
		System.out.println("Premier essai");
//		System.out.println(Message.listMessage(login,comment));
		System.out.println("fin premier essai");
//		System.out.println("---------------------");
//		System.out.println("deuxieme essai");
//		System.out.println(Message.listMessage(login1,comment, date));
//		System.out.println("fin deuxieme essai");
//		System.out.println("---------------------");
//		System.out.println("troisieme essai");
//		System.out.println(Message.listMessage(login2,comment, date));
//		System.out.println("fin troisieme essai");
//		System.out.println("---------------------");
//		System.out.println("quatrieme essai");
//		System.out.println(Message.listMessage(log, comment, date));
//		System.out.println("fin quatrieme essai");
//		System.out.println(BDMessage.getIdMessage(login));
		//Message.deleteMessage("2","60169");
	

//	


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
//
		
		List<String> l1 = new ArrayList<>(); 
		//list<String> l2 = new ArrayList<>();
		
		System.out.print(l1.size());
		System.out.println(l1.isEmpty());
		
		
		
	}

}
