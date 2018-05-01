package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.Commentaire;
import services.Message;

public class AddCommentaire extends HttpServlet {
	
	public AddCommentaire(){
		super();
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String key = req.getParameter("key");
		String message = req.getParameter("message");
		String idMessage = req.getParameter("idMessage");
		JSONObject ret = new JSONObject();
		try{
			int id = Integer.parseInt(idMessage);
			ret = Commentaire.addCommentaire(key, id, message);
		
		}catch(SQLException  e){
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");
		out.print(ret.toString());	
	}
	

}
