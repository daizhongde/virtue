package person.daizhongde.virtue.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class JDBCHibernateConnection {
	private DataSource dataSource;
	
	private static Connection connection;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Connection getConnection() {
//		Session session = this.getSession();
//		Connection con = session.connection(); //得到链接
		try {
			connection = ( connection != null && ( !connection.isClosed() )) ? connection : dataSource.getConnection();
//			connection = ( connection != null && ( !connection.isClosed() )) ? connection : session.connection);
		} catch (CannotGetJdbcConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
//		return connection==null?session.connection():connection;
//		return connection;
	}

	// 关闭数据库连接
	public void closeConnection(Connection conn) {
		try {
			if (conn != null && (!conn.isClosed()))
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static JDBCHibernateConnection getFromApplicationContext(
			ApplicationContext ctx) {
		return (JDBCHibernateConnection) ctx.getBean("jdbcHConnection");
	}
	
}
