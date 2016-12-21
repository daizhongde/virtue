package person.daizhongde.virtue.jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import person.daizhongde.virtue.util.SQLManySwitch;

public class JDBCBatchInsert {
	/**
	 * single sql, multi-record, Mainly use for import
	 * @param conn
	 * @param insertSQL
	 * @param oa_row
	 * @param columnTypes
	 */
	public void batchInsert( Connection conn, String insertSQL, List<Object[]> oa_row, int[] columnTypes  ){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement(insertSQL);
			conn.setAutoCommit(false);
			for(int i = 0, j = oa_row.size(); i<j; i++) {
				Object[] row = oa_row.get(i);
				for( int m=0, n=row.length; m<n; m++ ){
					SQLManySwitch.setParameterValue(m+1, columnTypes[m], row[m], stmt);
//					stmt.setString(m+1, row[m]);
				}    
			    stmt.addBatch();
			    
			    if (i % 100 == 0) {
			        stmt.executeBatch();
			        conn.commit();
			    }
			}
			stmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1.getLocalizedMessage());
			}
			throw new RuntimeException(e.getLocalizedMessage());
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e.getLocalizedMessage());
			}
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e.getLocalizedMessage());
			}
		}
	}
	
	public void batchExecSQL( Connection conn, List<String> sqllist ){
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			for(int i = 0, j = sqllist.size(); i<j; i++) {
				String tempSQL = sqllist.get(i);
			    stmt.addBatch(tempSQL);
			    
			    if (i % 100 == 0) {
			        stmt.executeBatch();
			        conn.commit();
			    }
			}
			stmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1.getLocalizedMessage());
			}
			throw new RuntimeException(e.getLocalizedMessage());
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e.getLocalizedMessage());
			}
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
//				e.printStackTrace();
				throw new RuntimeException(e.getLocalizedMessage());
			}
		}
	}
}
