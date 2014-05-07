package ru.db.experiment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import ru.db.experiment.RecordBuilder.Record;

public class CoomitPerRowGenerator extends AbstactTableGenerator {
	
	public CoomitPerRowGenerator(Connection connection, int totalRowCount) {
		this.connection = connection;
		this.totalRowCount = totalRowCount;
		this.tableName = "Tab1";
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
						+ "			connection.commit();"
						+ "		}");
		
		for(int i = 1; i <= totalRowCount; i ++) {
			Record rec = rb.buildRecord(i);
			ps.setInt(1, rec.getId());
			ps.setBoolean(2, rec.isBool());
			ps.setString(3, rec.getText());
			ps.setString(4, rec.getInsCode());
			ps.setTimestamp(5, new Timestamp(rec.getCreateDateTime().getTime()));
			ps.setDate(6, new Date(rec.getCreateDate().getTime()));	
			ps.executeUpdate();
			connection.commit();
		}
		
		finishMilis = System.currentTimeMillis();

	}
}
