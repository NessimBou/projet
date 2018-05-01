package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.SQLException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

//import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import services.Message;

@SuppressWarnings("serial")
public class ListMessage extends HttpServlet {
	
	public ListMessage(){
		super();
	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		String idUser = req.getParameter("idUser");
		
		JSONObject ret = new JSONObject();

		//Class.forName("com.mysql.jdbc.Driver");
		try {
			ret = Message.listMessage(idUser);
			System.out.println(ret);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}

}
