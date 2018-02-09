package bd;
/**Classe permettant de verifier si les données sont contenues dans la base de données
 * 
 * @author NessimDina
 *
 */
public class BdTools {
	
	public BdTools(){
		
	}
	
	/**Methode ajoutant un utilisateur à la base de donnée
	 * 
	 * @param login identifiant de l'user
	 * @param mdp mdp de l'user
	 * @param nom nom de l'user
	 * @param Prenom prenom de l'user
	 * 
	 */
	public static void addToDBUser(String login,String mdp, String nom,String Prenom){
		
	}
	/** Methode permettant de verifier si l'utilisateur existe dans la base de données
	 * 
	 * @param login l'identifiant de l'utilisateur 
	 * @return True si il existe, false sinon
	 */
	public static boolean userExist(String login){
		return true;
	}

	/** Methode permettant de verifier si c'est le bon mdp associé à l'utilisateur
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
	
}
