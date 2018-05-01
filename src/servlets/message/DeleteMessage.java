package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import services.Message;

public class DeleteMessage extends HttpServlet{


	public DeleteMessage(){
		super();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		
		String message = req.getParameter("idMessage");
		String idUser = req.getParameter("idUser");
		//ObjectId id  = new ObjectId(idUser);
		JSONObject ret = new JSONObject();
		
		//Class.forName("com.mysql.jdbc.Driver");
		try {
			ret = Message.deleteMessage(idUser, message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
