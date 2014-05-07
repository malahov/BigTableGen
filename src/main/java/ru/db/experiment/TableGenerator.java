package ru.db.experiment;

import java.sql.SQLException;

public interface TableGenerator {
	
	void generate() throws SQLException;
	
	void printStatistic();

}
