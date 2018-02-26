package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import services.Friend;
import services.Message;

public class AddMessage {
	
	public AddMessage(){
		super();
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException, SQLException{
		
		String message = req.getParameter("message");
		String idUser = req.getParameter("idUser");
		BasicDBObject ret = new BasicDBObject();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			ret = Message.addMessage(idUser, message);

		}catch(ClassNotFoundException  e){
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
