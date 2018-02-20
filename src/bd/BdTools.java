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
		String query="SELECT * FROM Session WHERE idUser ='"+id+"';";
		ResultSet resultat = lecture.executeQuery(query);
		a = resultat.getBoolean("isRoot");
		resultat.close();
		lecture.close();
		c.close();
		return a;
	}
		
	
	public static boolean expireSession(int login) throws SQLException{
		return true;
	}
	
	
	/**Verifie si la cle existe
	 * 
	 * @param key cle de l'utilisateur
	 * @return true/false
	 */
	public static boolean keyExist(int key){
		return true;
	}

	

	public static int getIdUser(String login) {
		
		return 0;
	}
	
	/**Verifie que l'utilisateur est connecte
	 * 
	 * @param login utilisateur
	 * @return TRue/false
	 * @throws SQLException
	 */
	
	public static boolean  getConnect(String login) throws SQLException{
		
		int id;
		boolean a;
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		id = Integer.parseInt(login);
		String query="SELECT * FROM Session WHERE idUser ='"+id+"';";
		ResultSet resultat = lecture.executeQuery(query);
		a = resultat.getBoolean("connect");
		resultat.close();
		lecture.close();
		c.close();
		return a;
	}
}
