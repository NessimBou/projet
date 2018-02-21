package bd;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.UUID;

/**Classe permettant de verifier si les donnees sont contenues dans la base de donnees
 * 
 * @author NessimDina
 *
 */
public class BdTools {
	private boolean a;
	
	public BdTools(){
		
	}
	
	/**Methode ajoutant un utilisateur a la base de donnee
	 * 
	 * @param login identifiant de l'user
	 * @param mdp mdp de l'user
	 * @param nom nom de l'user
	 * @param Prenom prenom de l'user
	 * @throws SQLException 
	 * 
	 */
	public static void addToDBUser(String login,String mdp, String nom,String prenom) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		String query = "INSERT into user values(NULL,'"+login+"','"+nom+"','"+prenom+"','PASSWORD("+mdp+")');";
		int resultat= lecture.executeUpdate(query);
		if(resultat == 1){
			System.out.println("User ajoute a la bdd");
		}else{
			System.out.println("Erreur ajout");			
		}
		
		
	}

	
	
	/** Methode permettant de verifier si l'utilisateur existe dans la base de donn�es
	 * 
	 * @param login l'identifiant de l'utilisateur 
	 * @return True si il existe, false sinon
	 * @throws SQLException 
	 */
	public static boolean userExist(String login) throws SQLException{
		
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
	 */
	public static boolean checkPassword(String login,String password){
//		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
//		Statement lecture= c.createStatement();
//		String query = "Select * from user where login =" +login+ "';"; 
//		
		return true;
	}
	

	public static String insertSession(int id_user,boolean root ) throws SQLException{
		//Creer une nouvelle connexion a cette adresse
		String key = UUID.randomUUID().toString();
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		String query = "INSERT into session values(NULL,"+id_user+",NULL,NOW,"+key+","+root+"true);";
		int resultat= lecture.executeUpdate(query);
		if(resultat == 1){
			return key;
		}else{
			key = "erreur";
			return key; 
		}
	}
	
	
	


	/**Verifie Utilisateur est root
	 * 
	 * @param login utilisateur
	 * @return True/false
	 * @throws SQLException
	 */
	public boolean isRoot(String login) throws SQLException{
		int id;
		boolean a;
		
		//Creer une nouvelle connexion a cette adresse

		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		// cree une requete sql qui recupere l'identifiant de l'uilisateur
		id = Integer.parseInt(login);
		String query="SELECT * FROM session WHERE idUser ='"+id+"';";
		ResultSet resultat = lecture.executeQuery(query);
		
		//on recupere le boolean de root et on le stock dans une variable 
		a = resultat.getBoolean("isRoot");
		resultat.close();
		lecture.close();
		c.close();
		return a;
	}
		
	
	
	 /**Si l'utilisateur est reste connecter un certain nombre de minute on expire la session sauf si il est root
	  * 
	  * @param key cle de connexion 
	  * @return True/False
	  * @throws SQLException
	  */
	public static boolean expireSession(int key) throws SQLException{
		//Pour l'instant on la laisse comme ça mais apres il faudra calculer depuis combien de temps 
		// il est connecter puis si il a depassé le temps on expire la Session
		boolean a;
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture= c.createStatement();
		String query ="Select * from session WHERE cle='"+key+"';";
		ResultSet resultat = lecture.executeQuery(query);
		
		a = resultat.getBoolean("isRoot");
		if(a == true){
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
	
	
	/**Verifie si la cle existe
	 * 
	 * @param key cle de l'utilisateur
	 * @return true/false
	 * @throws SQLException 
	 */
	public static boolean keyExist(int key) throws SQLException{
		
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

	
//
//	public static int getIdUser(String login) {
//		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
//		Statement lecture = c.createStatement();
//		int id = Integer.parseInt(login)
//		String query = "Select * From session Where "
//		
//		return 0;
//	}
	
	/**Verifie que l'utilisateur est connecte
	 * 
	 * @param login utilisateur
	 * @return TRue/false
	 * @throws SQLException
	 */
	
	public static boolean  getConnect(String login) throws SQLException{
		
		int id;
		boolean a;
		//Connexion 
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		
		//passe le login de String a int
		id = Integer.parseInt(login);
		
		//requete SQL
		String query="SELECT * FROM Session WHERE idUser ='"+id+"';";
		ResultSet resultat = lecture.executeQuery(query);
		
		//on regarde le boolean de connect et on le stock dans une variable
		a = resultat.getBoolean("connect");
		resultat.close();
		lecture.close();
		c.close();
		return a;
	}
}
