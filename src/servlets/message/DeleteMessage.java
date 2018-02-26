package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;

import services.Message;

public class DeleteMessage {


	public DeleteMessage(){
		super();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException, SQLException{
		
		String message = req.getParameter("idMessage");
		String idUser = req.getParameter("idUser");
		BasicDBObject ret = new BasicDBObject();
		
		int idMessage = Integer.parseInt(message);
		//Class.forName("com.mysql.jdbc.Driver");
		ret = Message.deleteMessage(idUser, idMessage);
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
