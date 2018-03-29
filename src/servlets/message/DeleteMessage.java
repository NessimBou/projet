package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import services.Message;

public class DeleteMessage {


	public DeleteMessage(){
		super();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException, SQLException, JSONException{
		
		String message = req.getParameter("idMessage");
		String idUser = req.getParameter("idUser");
		ObjectId id  = new ObjectId(idUser);
		JSONObject ret = new JSONObject();
		
		//Class.forName("com.mysql.jdbc.Driver");
		ret = Message.deleteMessage(idUser, id);
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
