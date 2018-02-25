package servlets.friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Friend;

public class RemoveFriend extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RemoveFriend() {
		super();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{

		String idUser = req.getParameter("idUser");
		String idFriend = req.getParameter("idFriend");
		JSONObject ret = new JSONObject();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			ret = Friend.removeFriend(idUser, idFriend);	

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}
	
}
