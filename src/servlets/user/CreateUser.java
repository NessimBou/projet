package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUser extends HttpServlet {
	
	public CreateUser(){
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		String login = req.getParameter("login");
		String name = req.getParameter("name");
		String frame = req.getParameter("frame");
		String pwd = req.getParameter("pwd");
		
		JSONObject ret = null;
		try{
			ret = services.User.createUser(login, name, frame, pwd);
			PrintWriter out = res.getWriter();
			res.setContentType("text/plain");
			out.print(ret.toString());
		}catch(IOException | JSONException e){
			e.printStackTrace();
		}
		
	}
}
