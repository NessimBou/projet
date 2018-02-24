package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import services.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends HttpServlet {

	public Login(){
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException , IOException{
		String login = req.getParameter("login");
		String pwd = req.getParameter("pwd");
		
		JSONObject ret = new JSONObject();
		try {
			//il faut utiliser le Class.forName sinon lorsque l'on se connecte a tomcat il ne pourra pas faire les test car on lui dit jamais
			//d'utiliser le driver
			Class.forName("com.mysql.jdbc.Driver");
			ret = User.login(login, pwd);
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
		}
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());
	}
}
