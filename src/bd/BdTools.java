package bd;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import serviceTool.Database;

/**Classe permettant de verifier si les donnï¿½es sont contenues dans la base de donnï¿½es
 * 
 * @author NessimDina
 *
 */
public class BdTools {
	
	public BdTools(){
		
	}
	
	/**Methode ajoutant un utilisateur ï¿½ la base de donnï¿½e
	 * 
	 * @param login identifiant de l'user
	 * @param mdp mdp de l'user
	 * @param nom nom de l'user
	 * @param Prenom prenom de l'user
	 * 
	 */
	public static void addToDBUser(String login,String mdp, String nom,String Prenom){
		
	}
	/** Methode permettant de verifier si l'utilisateur existe dans la base de donnï¿½es
	 * 
	 * @param login l'identifiant de l'utilisateur 
	 * @return True si il existe, false sinon
	 * @throws SQLException 
	 */
	public static boolean userExist(String login) throws SQLException{
		
		//Créer une nouvelle connexion à cette adresse
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/boutar_hussein","root","root");
		Statement lecture = c.createStatement();
		// crée une requete sql qui recupere l'identifiant de l'uilisateur
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

	/** Methode permettant de verifier si c'est le bon mdp associï¿½ ï¿½ l'utilisateur
	 * 
	 * @param password le mot de passe de l'utilisateur
	 * @param login le nom de l'utilisateur 
	 * @return vrai si c'est le bon mot de passe, false sinon
	 */
	public static boolean checkPassword(String login,String password){
		return true;
	}
	
	public static String insertSession(int id_user , boolean rest){
		return null;
	}
	
	public boolean isRoot(String user){
		return true;
	}
	
	public static boolean expireSession(int login) throws SQLException{
		return true;
	}
	/**Verifie si la clï¿½ existe
	 * 
	 * @param key clï¿½ de l'utilisateur
	 * @return true/false
	 */
	public static boolean keyExist(int key){
		return true;
	}

	public static int getIdUser(String login) {
		
		return 0;
	}
	
}
