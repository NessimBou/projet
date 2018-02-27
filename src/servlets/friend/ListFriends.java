package servlets.friend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Friend;

public class ListFriends extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListFriends() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{

		String idUser = req.getParameter("idUser");
		JSONObject ret = new JSONObject();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ret = Friend.listFriends(idUser);	
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}
	

}
