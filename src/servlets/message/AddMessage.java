package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import services.Friend;
import services.Message;

public class AddMessage extends HttpServlet{
	
	public AddMessage(){
		super();
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		
		String message = req.getParameter("message");
		String idUser = req.getParameter("idUser");
		JSONObject ret = new JSONObject();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			ret = Message.addMessage(idUser, message);

		}catch(ClassNotFoundException | SQLException  e){
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
