package bd;

import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**Classe permettant de verifier si les donnees sont contenues dans la base de donnees
 * 
 * @author NessimDina
 *
 */
public class BdTools {
	
	public BdTools(){
		
	}
	
	/**Methode ajoutant un utilisateur a la base de donnee
	 * 
	 * @param login identifiant de l'user
	 * @param mdp mdp de l'user
	 * @param nom nom de l'user
	 * @param Prenom prenom de l'user
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public static void addToDBUser(String login,String mdp, String nom,String prenom) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query = "INSERT into user values(NULL,'"+login+"','"+nom+"','"+prenom+"','"+mdp+"');";
		int resultat= lecture.executeUpdate(query);
		if(resultat == 1){
			System.out.println("User ajoute a la bdd");
		}else{
			System.out.println("Erreur ajout");			
		}
		lecture.close();
		c.close();
	}

	
	
	/** Methode permettant de verifier si l'utilisateur existe dans la base de donn�es
	 * 
	 * @param login l'identifiant de l'utilisateur 
	 * @return True si il existe, false sinon
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static boolean userExist(String login) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		//Creer une nouvelle connexion a cette adresse
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		// cree une requete sql qui recupere l'identifiant de l'uilisateur
		String query="SELECT id FROM user WHERE login ='"+login+"';";
		ResultSet resultat = lecture.executeQuery(query);
		
		//si il n'y a pas de next alors l'utilisateur n'existe pas 
		if (!resultat.next()){
			resultat.close();
			lecture.close();
			c.close();
			return false;
		}
		resultat.close();
		lecture.close();
		c.close();
		return true;
	}

	/** Methode permettant de verifier si c'est le bon mdp associe a l'utilisateur
	 * 
	 * @param password le mot de passe de l'utilisateur
	 * @param login le nom de l'utilisateur 
	 * @return vrai si c'est le bon mot de passe, false sinon
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static boolean checkPassword(String login,String password) throws ClassNotFoundException, SQLException, IOException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		
		String query = "SELECT password from user where login = '"+login+"';";
		//Je sais pas a quoi sert preparedStatement du coup je l'ai mis en commentaire 
		//PreparedStatement ps = c.prepareStatement(query);
		ResultSet resultat = lecture.executeQuery(query);
		
		while(resultat.next()){
			if(resultat.getString("password").equals(password)){
				resultat.close();
				lecture.close();
				c.close();
				return true;
			}
		}
		resultat.close();
		lecture.close();
		c.close();
		return false;
		
	}	

	/**On insere la connexion dans la table session
	 *  
	 * @param id_user l'id du user
	 * @param root si il est root ou pas 
	 * @return la cle du user
	 * @throws SQLException
	 */
	public static String insertSession(int id_user,boolean root ) throws SQLException{
		//Creer une nouvelle connexion a cette adresse
		String key = UUID.randomUUID().toString();
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		String query;
		//SQL reconnait pas true/false ecrit comme �a
		if(root){
			query = "INSERT into session values(NULL,'"+id_user+"',NOW(),'"+key+"',True);";
		}else{	
			query = "INSERT into session values(NULL,'"+id_user+"',NOW(),'"+key+"','False');";
		}
		int resultat= lecture.executeUpdate(query);
		if(resultat == 1){
			lecture.close();
			c.close();
			return key;
		}else{
			key = "erreur";
			lecture.close();
			c.close();
			return key; 
		}
	}
	
	


	/**Verifie Utilisateur est root
	 * 
	 * @param login utilisateur
	 * @return True/false
	 * @throws SQLException
	 */
	public static boolean isRoot(String key) throws SQLException{
		int id;
		boolean a;
		
		//Creer une nouvelle connexion a cette adresse
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		// cree une requete sql qui recupere l'identifiant de l'uilisateur
	//	id = Integer.parseInt(login);
		String query="SELECT * FROM session WHERE cle ='"+key+"';";
		ResultSet resultat = lecture.executeQuery(query);
		
		//on recupere le boolean de root et on le stock dans une variable 
		if(resultat.next()){
			
			a = resultat.getBoolean("isRoot");
			resultat.close();
			lecture.close();
			c.close();
			return a;
		}
		resultat.close();
		lecture.close();
		c.close();
		return false;
	}
		
	
	
	 /**Si l'utilisateur est reste connecter un certain nombre de minute on expire la session sauf si il est root
	  * @param key cle de connexion 
	  * @return True/False
	  * @throws SQLException
	  */
	public static boolean expireSession(String key) throws SQLException{
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture= c.createStatement();
		String query= "SELECT TIMESTAMPDIFF(MINUTE,t, NOW()) FROM session where cle = '"+key+"';";
		ResultSet resultat = lecture.executeQuery(query);
		//cas ou il a bien supprimé la session 
		while(resultat.next()){
			int min = resultat.getInt("TIMESTAMPDIFF(MINUTE,t, NOW())");
			if(min > 30 && !isRoot(key)){
				boolean deco = deconnection(key);
				if(deco){
					resultat.close();
					lecture.close();
					c.close();
					return true;
				}
			}
		}
		resultat.close();
		lecture.close();
		c.close();
		return false;
	}
		
		
	/**Deconnecte l'utilisateur
	 * @param key cle de connection
	 * @return True/False
	 * @throws SQLException
	 */
	public static boolean deconnection(String key) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		String query = "DELETE FROM session where cle = '"+key+"';";
		int resultat = lecture.executeUpdate(query);
		if(resultat == 1){
			lecture.close();
			c.close();
			return true;
		}
		lecture.close();
		c.close();
		return false;
	}
	
	
	/**Verifie si la cle existe
	 * @param key cle de l'utilisateur
	 * @return true/false
	 * @throws SQLException 
	 */
	public static boolean keyExist(String key) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		String query="Select * From session WHERE cle ='"+key+"';";
		ResultSet resultat = lecture.executeQuery(query);
		
		if(!resultat.next()){
			resultat.close();
			lecture.close();
			c.close();
			return false;
		}
		resultat.close();
		lecture.close();
		c.close();
		return true;
	}
	
	public static String getKey(String login) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		int id = Integer.parseInt(login);
		String query ="Select * FROM session WHERE idUser ='"+id+"';";
		ResultSet resultat = lecture.executeQuery(query);
		if(resultat.next()){
		
			String key = resultat.getString("cle");
			resultat.close();
			lecture.close();
			c.close();
			return key;
		}
		resultat.close();
		lecture.close();
		c.close();
		return "erreur";
	}
	
	
	public static int getIdUser(String key) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		String query = "Select * from session where cle ='"+key+"';";
		ResultSet resultat = lecture.executeQuery(query);
		if(resultat.next()){
			int id = resultat.getInt("idUser");
			resultat.close();
			lecture.close();
			c.close();
			return id;
		}
		resultat.close();
		lecture.close();
		c.close();
		return -1;
	}
	
	
	
	/**Supprime l'utilisateur de la bdd user
	 * 
	 * @param login de l'utilisateur
	 * @return true/false
	 * @throws SQLException
	 */
	public static boolean DeleteUser(String login) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture= c.createStatement();
		String query= "DELETE from user where login='"+login+"';";
		int resultat = lecture.executeUpdate(query);
		if(resultat == 1){
			lecture.close();
			c.close();
			return true;
		}
		
		lecture.close();
		c.close();
		return false;
	}
	

}
