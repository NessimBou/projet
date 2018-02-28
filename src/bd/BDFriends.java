package bd;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.util.Date;

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
		String query = "Select * FROM session WHERE cle  ='"+key+"';";
		ResultSet resultat= lecture.executeQuery(query);;
		int idUser = resultat.getInt("idUser");
		
		//Puis on ajoute l'amitie dans la table friend	
		query = "INSERT into friend values('"+idUser+"','"+idFriend+"','"+dateToday+"');";
		int resultat2= lecture.executeUpdate(query);
		if(resultat2 == 1){
			System.out.println("Amitie ajout�e a la bdd");
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
		String query = "Select * FROM session WHERE cle  ='"+key+"';";
		ResultSet resultat= lecture.executeQuery(query);;
		int idUser = resultat.getInt("idUser");
		
		//Puis on supprime l'amitie dans la table friend
		query = "DELETE FROM friend where idUser = '" + idUser + "' and idFriend = '"+idFriend+"';";
		int resultat2= lecture.executeUpdate(query);
		if(resultat2 == 1){
			System.out.println("Amitie supprimee de la bdd");
		}else{
			System.out.println("Erreur suppression ami");			
		}
		lecture.close();
		c.close();	
	}
	
	
	public static String getList(String key) throws ClassNotFoundException, SQLException {
		String friends = "";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		
		//on recupere l'idUser a partir de la cle
		String query = "Select * FROM session WHERE cle  ='"+key+"';";
		ResultSet resultat= lecture.executeQuery(query);;
		int idUser = resultat.getInt("idUser");
		
		
		query = "SELECT * from friend where userId = '"+idUser+"'; ";
		resultat= lecture.executeQuery(query);
		
		//Si l'utilisateur n'a pas d'amis
		if (!resultat.next()){
			resultat.close();
			lecture.close();
			c.close();
			return "L'utilisateur " +idUser+" n'a pas d'amis pour le moment :( ";
		}
		
		while(resultat.next()) {
			friends += resultat.getString("idFriend");
		}
		resultat.close();
		lecture.close();
		c.close();
		return friends;
	}
}
