package serviceTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {
	private DataSource datasource;
	private static Database database=null;
	
	public Database(String name) throws SQLException{
		try{
			datasource = (DataSource) new InitialContext().lookup("java:comp/env"+ name);
		}catch(NamingException e){
			throw new SQLException(name + "is missing in JNDI! : "+e.getMessage());
		}
	}
	
	public Connection getConnection()throws SQLException{
		return datasource.getConnection();
	}
	
	/**Renvoye une nouvelle connexion à une base de données
	 * 
	 * @return nouvelle connexion
	 * @throws SQLException
	 */
	public static Connection getMySQLConnection() throws SQLException{
		if(DBStatic.mysql_pooling == false){
			return (DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+dbStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password));
		}else{
			if(database == null){
				database = new Database("jdbc/db");
			}
			return (database.getConnection());
		}
	}
}
