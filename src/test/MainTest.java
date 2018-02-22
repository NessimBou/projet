package test;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import bd.*;
import services.*;
import serviceTool.*;

public class MainTest {

	public static void main(String[] args) throws SQLException, JSONException, ClassNotFoundException {
		
		String login="coucou";
		String password="root";
		String nom="coucou";
		String prenom="coucou";
		Connection c= bd.Database.getMySQLConnection();
//		User user= new User();
//		user.login(login, password);
		System.out.println(services.User.login(login,password));
	
	

		//Database db=new Database("Test");
		
		Statement lecture = c.createStatement();
		ResultSet curseur = lecture.executeQuery("Select * from user;");
		while (curseur.next())
		{
			System.out.println(curseur.getString("id"));
			System.out.println(curseur.getString("login"));
			System.out.println(curseur.getString("nom"));
			System.out.println(curseur.getString("prenom"));
			System.out.println(curseur.getString("password"));
		}
		curseur.close();
		lecture.close();
		c.close();
//		//System.out.println(bd.UserTools.userExist("3408748"));
//		System.out.println(UUID.randomUUID().toString());

	}

}
