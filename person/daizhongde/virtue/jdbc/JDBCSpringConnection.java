package person.daizhongde.virtue.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JDBCSpringConnection {
	private DataSource dataSource;
	private static Connection connection;
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Connection getConnection() {
		try {
			connection = (connection != null && (!connection.isClosed()))?connection:DataSourceUtils.getConnection(this.dataSource);
		} catch (CannotGetJdbcConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
//		return connection;
//		return DataSourceUtils.getConnection(this.dataSource);
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
	public static JDBCSpringConnection getFromApplicationContext(
			ApplicationContext ctx) {
		return (JDBCSpringConnection) ctx.getBean("jdbcSConnection");
	}
}
