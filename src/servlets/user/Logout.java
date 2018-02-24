package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bd.BdTools;
import services.User;

public class Logout extends HttpServlet {

	public Logout(){
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException , IOException{
		String login = req.getParameter("login");
		
		JSONObject ret = new JSONObject();
		try {
			//il faut utiliser le Class.forName sinon lorsque l'on se connecte a tomcat il ne pourra pas faire les test car on lui dit jamais
			//d'utiliser le driver
			Class.forName("com.mysql.jdbc.Driver");
			String key = BdTools.getKey(login);
			ret = User.logout(login, key);
		} catch (ClassNotFoundException | SQLException e) {
	
			e.printStackTrace();
		} 
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());
	}
	
}

