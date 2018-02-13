package bd;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**Classe permettant de verifier si les donn�es sont contenues dans la base de donn�es
 * 
 * @author NessimDina
 *
 */
public class BdTools {
	
	public BdTools(){
		
	}
	
	/**Methode ajoutant un utilisateur � la base de donn�e
	 * 
	 * @param login identifiant de l'user
	 * @param mdp mdp de l'user
	 * @param nom nom de l'user
	 * @param Prenom prenom de l'user
	 * 
	 */
	public static void addToDBUser(String login,String mdp, String nom,String Prenom){
		
	}
	/** Methode permettant de verifier si l'utilisateur existe dans la base de donn�es
	 * 
	 * @param login l'identifiant de l'utilisateur 
	 * @return True si il existe, false sinon
	 */
	public static boolean userExist(String login){
		boolean retour;
		try {
			Class.forName("com.mysql.jdbc.Priad").newInstance();
			Connection c = Database.getMySQLConnection();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/** Methode permettant de verifier si c'est le bon mdp associ� � l'utilisateur
	 * 
	 * @param password le mot de passe de l'utilisateur
	 * @param login le nom de l'utilisateur 
	 * @return vrai si c'est le bon mot de passe, false sinon
	 */
	public static boolean checkPassword(String login,String password){
		return true;
	}
	
	public static String insertSession(String login , boolean rest){
		return null;
	}
	
	public boolean isRoot(String user){
		return true;
	}
	
	public static boolean expireSession(int login) throws SQLException{
		return true;
	}
	/**Verifie si la cl� existe
	 * 
	 * @param key cl� de l'utilisateur
	 * @return true/false
	 */
	public static boolean keyExist(int key){
		return true;
	}
	
}
