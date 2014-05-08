package ru.db.experiment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import ru.db.experiment.RecordBuilder.Record;

public class TenThreadButchGenerator extends AbstactTableGenerator { 
	
	private String connectionUrl;
	
	public TenThreadButchGenerator(String connectionUrl, int totalRowCount) throws SQLException {		
		this(connectionUrl, totalRowCount, "Tab6");
	}
	
	public TenThreadButchGenerator(String connectionUrl, int totalRowCount, String tableName) throws SQLException {
		//TODO Refactoring Изменить абстрактный класс, connection должен создаваться и закрываться в конкретном классе.
		this.connection = DriverManager.getConnection(connectionUrl);
		this.connectionUrl = connectionUrl;
		this.totalRowCount = totalRowCount;
		this.tableName = tableName;
	}
	
	@Override
	public void generate() throws SQLException {
		if(existTable()) {
			dropTable();
		}
		
		createTable();
		
		String insert_sql = "insert into " + tableName + " values (?, ?, ?, ?, ?, ?)";
		
		startMilis = System.currentTimeMillis();
		
		PreparedStatement ps = connection.prepareStatement(insert_sql);
		
		RecordBuilder rb = new RecordBuilder("PreparedStatement ps = connection.prepareStatement(insert_sql);"
				+ "		RecordBuilder rb = new RecordBuilder(\"\");"
						+ "		for(int i = 1; i <= totalRowCount; i ++) {"
						+ "			Record rec = rb.buildRecord(i);"
						+ "			ps.setInt(1, rec.getId());"
						+ "			ps.setBoolean(2, rec.isBool());"
						+ "			ps.setString(3, rec.getText());"
						+ "			ps.setString(4, rec.getInsCode());"
						+ "			ps.setTimestamp(5, new Timestamp(rec.getCreateDateTime().getTime()));"
						+ "			ps.setDate(6, new Date(rec.getCreateDate().getTime()));	"
						+ "			ps.executeUpdate();"						
						+ "		}" 
						+ "			connection.commit();"
						);
		
		for(int i = 1; i <= totalRowCount; i ++) {
			Record rec = rb.buildRecord(i);
			ps.setInt(1, rec.getId());
			ps.setBoolean(2, rec.isBool());
			ps.setString(3, rec.getText());
			ps.setString(4, rec.getInsCode());
			ps.setTimestamp(5, new Timestamp(rec.getCreateDateTime().getTime()));
			ps.setDate(6, new Date(rec.getCreateDate().getTime()));	
			ps.addBatch();
			if(i % 65000 == 0) {
				ps.executeBatch();
			}
			
		}
		ps.executeBatch();
		connection.commit();
		
		finishMilis = System.currentTimeMillis();

	}	
	
	private class PartInsert implements Runnable {
		
		private int startIndex;
		private int endIndex;
		
		public PartInsert(int startIndex, int endIndex) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			try {
				// создать подключение
				Connection conn = DriverManager.getConnection(connectionUrl);
				String insert_sql = "insert into " + tableName + " values (?, ?, ?, ?, ?, ?)";
				// выполнить вставка butch
				// коммит
				// закрыть подключение
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
