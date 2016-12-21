package person.daizhongde.virtue.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class JDBCConnection {
	public static final String MySQL = "MySQL";
	public static final String Oracle = "Oracle";

	/**
	 * jdbc:mysql://10.226.4.235:3308?user=mig123&password=mig123
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static Connection getMySQLJDBCConnection(String url)
			throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);

		return DriverManager.getConnection(url);
	}

	/**
	 * jdbc:oracle:thin:newdelfi/newdelfi@10.226.4.235:1521:ora11gR2
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static Connection getOracleJDBCConnection(String url)
			throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		Object obj = Class.forName(driver).newInstance();
		DriverManager.registerDriver((Driver) obj);

		return DriverManager.getConnection(url);
	}

	public static Connection getJDBCConnection(String url, String dbtype)
			throws Exception {
		switch (dbtype) {
		case MySQL:
			return getMySQLJDBCConnection(url);
		case Oracle:
			return getOracleJDBCConnection(url);
		default:
			throw new Exception("This db is not support:<" + dbtype + ">");
		}

		// if (dbtype.trim().toLowerCase().equalsIgnoreCase("mysql")) {
		// return getMySQLJDBCConnection(url);
		// } else if (dbtype.trim().toLowerCase().equalsIgnoreCase("oracle")) {
		// return getOracleJDBCConnection(url);
		// } else {
		// throw new Exception("This db is not support:<" + dbtype + ">");
		// }
	}

	/**
	 * jdbc:mysql://10.226.4.235:3308?user=mig123&password=mig123
	 * 
	 * @param IP
	 * @param port
	 * @param db
	 * @param user
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	private static Connection getMySQLJDBCConnection(String IP, String port,
			String db, String user, String passwd) throws Exception {
		String url = ("jdbc:mysql://" + IP + ":" + port + "/" + db + "?user="
				+ user + "&password=" + passwd);
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		return DriverManager.getConnection(url);

	}

	/**
	 * jdbc:oracle:thin:newdelfi/newdelfi@10.226.4.235:1521:ora11gR2
	 * 
	 * @param IP
	 * @param port
	 * @param db
	 * @param user
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	private static Connection getOracleJDBCConnection(String IP, String port,
			String db, String user, String passwd) throws Exception {
		String url = ("jdbc:oracle:thin:"+user+"/"+passwd+"@"+IP+":"+port+":"+db );
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		return DriverManager.getConnection(url);

	}

	public static Connection getJDBCConnection(String IP, String port,
			String db, String user, String passwd, String dbtype)
			throws Exception {
		switch (dbtype) {
		case MySQL:
			return getMySQLJDBCConnection( IP,  port,
					 db,  user,  passwd );
		case Oracle:
			return getOracleJDBCConnection( IP,  port,
					 db,  user,  passwd);
		default:
			throw new Exception("This db is not support:<" + dbtype + ">");
		}
	}
}
