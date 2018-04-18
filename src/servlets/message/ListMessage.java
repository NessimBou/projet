package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import services.Message;

@SuppressWarnings("serial")
public class ListMessage extends HttpServlet {
	
	public ListMessage(){
		super();
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		String content = req.getParameter("content");
		String idUser = req.getParameter("idUser");
		
		BasicDBObject ret = new BasicDBObject();

		//Class.forName("com.mysql.jdbc.Driver");
		ret = Message.listMessage(idUser, content);
			
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
