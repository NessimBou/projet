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

public class AddFriend extends HttpServlet {
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;


	public AddFriend() {
		super();
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		
		String key = req.getParameter("key");
		String idFriend = req.getParameter("idFriend");
		//String loginFriend = req.getParameter("loginFriend");
		JSONObject ret = new JSONObject();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ret = Friend.addFriend(key, idFriend);

		}catch(ClassNotFoundException  e){
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}
	
}
