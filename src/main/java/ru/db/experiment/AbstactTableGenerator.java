package ru.db.experiment;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstactTableGenerator implements TableGenerator {

	protected String tableName;

	protected Connection connection;
	protected int totalRowCount;
	
	protected long startMilis;
	
	protected long finishMilis;
	
	
	@Override
	public void printStatistic() {
		System.out.println("Вставлено " + totalRowCount + " замисей за " + (finishMilis - startMilis) + " мс." );
	}
		
	
	protected boolean existTable() throws SQLException {
		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(), null);
		boolean res = rs.next();
		rs.close();
		return res;
	}
	
	protected void dropTable() throws SQLException {
		
		String drop_sql = "DROP TABLE " + tableName;
		
		Statement st = connection.createStatement();
		
		st.executeUpdate(drop_sql);
		connection.commit();
		st.close();
	}
		
	protected void createTable() throws SQLException {
		Statement st = connection.createStatement();
		
		String create_sql = "CREATE TABLE " + tableName 
				+ "(ID INT NOT NULL, BOOL BOOLEAN NOT NULL, text VARCHAR(256) NOT NULL, code VARCHAR(1024) NOT NULL, "
				+ "createDateTime TIMESTAMP not null, createDate DATE not null)";
		
		st.executeUpdate(create_sql);
		connection.commit();
		st.close();
	}

}
