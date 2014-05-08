package ru.db.experiment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Эксперимент по генерации большой таблицы");
		System.out.println("Каждая таблица должна сожержать по 100 000 записей, таблица 5 будет содержать 7 000 000 записей");
		System.out.println("Структура записи одинакова и содержит int id | bool | str | str | timstamp | date ");
		System.out.println("Таблица 1 (Tab1) будет генерироваться "
				+ "путем последовательного добавления по 1 строке и коммита.");
		System.out.println("Таблица 2 (Tab2) будет генерироваться "
				+ "путем последовательного добавления по 1 строке 1000 строк и коммита.");
		System.out.println("Таблица 3 (Tab3) будет генерироваться "
				+ "путем последовательного добавления по 100000 строк и 1 коммита.");
		System.out.println("Таблица 4 (Tab4) будет генерироваться "
				+ "путем последовательного добавления по 65000 строк через Butch и 1 комита.");	
		System.out.println("Таблица 5 (Tab5) будет генерироваться "
				+ "путем последовательного добавления по 65000 строк через Butch и 1 комита.");	
		
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println();
		
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		
		String connectionURL = "jdbc:derby://localhost:1527/C:/workspaces/db_exp/BigTableGen/exp_db;create=true";
		
		Connection conn = DriverManager.getConnection(connectionURL);
		
		conn.setAutoCommit(false);
		
//		System.out.println("Генерируем таблицу 1. Вставляем по 1 записи и делаем комит. "
//				+ "Всего будет вставлено 100 000 записей");
//		
//		TableGenerator gen = new CoomitPerRowGenerator(conn, 100000);
//		gen.generate();
//		gen.printStatistic();
//		
//		System.out.println("Генерируем таблицу 2. Вставляем по 1 записи и делаем комит после вставки каждых 1000 строк. "
//				+ "Всего будет вставлено 100 000 записей");
//		
//		gen = new CoomitPer1000RowGenerator(conn, 100000);
//		gen.generate();
//		gen.printStatistic();
//		
//		System.out.println("Генерируем таблицу 3. Вставляем по 1 записи и делаем комит после вставки всех строк. "
//				+ "Всего будет вставлено 100 000 записей");
//		
//		gen = new OneCoomitGenerator(conn, 100000);
//		gen.generate();
//		gen.printStatistic();
//		
//		System.out.println("Генерируем таблицу 4. Вставляем все записи в Butch по 65000 штук и делаем один коммит. "
//				+ "Всего будет вставлено 100 000 записей");
//		
//		gen = new OneCoomitButchGenerator(conn, 100000);
//		gen.generate();
//		gen.printStatistic();
//		
//		System.out.println("Генерируем таблицу 5. Вставляем все записи в Butch по 65000 штук и делаем один коммит. "
//				+ "Всего будет вставлено 7 000 000 записей");
//		
//		gen = new OneCoomitButchGenerator(conn, 7000000, "Tab5");
//		gen.generate();
//		gen.printStatistic();
		
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println();
		
		System.out.println("Тестируем выборку из 7 000 000 записей");
		
//		String query = "select count(*) from tab5";
//		
//		long start = System.currentTimeMillis();
//		Statement st = conn.createStatement();
//		ResultSet rs = st.executeQuery(query);
//		int count = 0;
//		if(rs.next()) {
//			count = rs.getInt(1);
//		}
//		long fi = System.currentTimeMillis();
//		System.out.println("Количество записей (select count(*) from tab5) равно " + count);
//		System.out.println("Вычеслено за  " + (fi - start) + "мс");
//		
//		conn.commit();
//		rs.close();
//		st.close();
		
		
//		Statement st = conn.createStatement();
//		st.executeUpdate("create index tab5_id_index on tab5 (id)");
//		st.close();
//		conn.commit();
		
//		String query = "select avg(id) from tab5";
//		
//		long start = System.currentTimeMillis();
//		Statement st = conn.createStatement();
//		ResultSet rs = st.executeQuery(query);
//		int count = 0;
//		if(rs.next()) {
//			count = rs.getInt(1);
//		}
//		long fi = System.currentTimeMillis();
//		System.out.println("Количество записей (select count(*) from tab5) равно " + count);
//		System.out.println("Вычеслено за  " + (fi - start) + "мс");
//		
//		conn.commit();
//		rs.close();
//		st.close();
		
		conn.close();
		
	}

}
