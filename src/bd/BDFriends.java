package bd;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.util.Date;

import services.Friend;

public class BDFriends {

	/** Ajoute une amitie
	 * @param key cl� de l'utilisateur
	 * @param idUser id de l'utilisateur
	 * @param idFriend id de l'ami ajout�
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public static void addFriend(String key, String idFriend) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		java.util.Date d1 = new java.util.Date();
		Date dateToday = new java.sql.Date(d1.getTime());
		
		
		//on recupere l'idUser a partir de la cle
		int idUser = BdTools.getIdUser(key);
		
		//Puis on ajoute l'amitie dans la table friend	
		String query = "INSERT into friend values('"+idUser+"','"+idFriend+"','"+dateToday+"');";
		int resultat2= lecture.executeUpdate(query);
		if(resultat2 == 1){
			System.out.println("Amitie ajoutee a la bdd");
		}else{
			System.out.println("Erreur ajout ami");			
		}
		lecture.close();
		c.close();
	}
	
	/** Supprime une amitie
	 * @param key cl� de l'utilisateur
	 * @param idUser id de l'utilisateur
	 * @param idFriend id de l'ami supprim�
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void removeFriend(String key, String idFriend) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		
		
		//on recupere l'idUser a partir de la cle
		int idUser = BdTools.getIdUser(key);
		//Puis on supprime l'amitie dans la table friend
		String query = "DELETE FROM friend where idUser = '" + idUser + "' and idFriend = '"+idFriend+"';";
		int resultat= lecture.executeUpdate(query);
		if(resultat == 1){
			System.out.println("Amitie supprimee de la bdd");
		}else{
			System.out.println("Erreur suppression ami");			
		}
		lecture.close();
		c.close();	
	}
	
	
	public static ArrayList<String> getList(String key) throws ClassNotFoundException, SQLException {
		ArrayList<String> listfriend = new ArrayList();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		
		//on recupere l'idUser a partir de la cle
		int idUser=BdTools.getIdUser(key);
		
		String query = "SELECT * from friend where idUser = '"+idUser+"'; ";
		ResultSet resultat= lecture.executeQuery(query);
		
		while(resultat.next()) {
			String friends = resultat.getString("idFriend");
			listfriend.add(friends);
		}
		
		//si l'utilisateur n'a pas d'amis
		if(listfriend.size() == 0){
			resultat.close();
			lecture.close();
			c.close();
			listfriend.add("L'utilisateur " +idUser+" n'a pas d'amis pour le moment :(");
			return listfriend;
		}
		
		resultat.close();
		lecture.close();
		c.close();
		return listfriend;
	}
}
