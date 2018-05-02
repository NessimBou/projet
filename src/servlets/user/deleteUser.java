package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.User;

public class deleteUser extends HttpServlet {

	public deleteUser(){
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException , IOException{
		String login = req.getParameter("login");
		String pwd = req.getParameter("password");
		
		JSONObject ret = new JSONObject();
		try {
			//il faut utiliser le Class.forName sinon lorsque l'on se connecte a tomcat il ne pourra pas faire les test car on lui dit jamais
			//d'utiliser le driver
			Class.forName("com.mysql.jdbc.Driver");
			ret = User.SupprimerUser(login, pwd);
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
		}
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());
	}
}
