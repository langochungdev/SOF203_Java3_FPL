package Servlet;

import java.sql.ResultSet;

public class b4_CallStatement {
	public static void main(String[] args) {
		try { 
			 String sql = "{CALL spSelectAll}"; 
			 ResultSet resultSet = jdbc.execQuery(sql); 
			 while(resultSet.next()) { 
			  String value = resultSet.getString("name"); 
			  System.out.println(value); 
			 } 
			} catch (Exception e) { 
			 e.printStackTrace(); 
			} 
			 
//			try { 
//			 String sql = "{CALL spInsert…, spUpdate...?, spDelete…?}"; 
//			 String values = {…}; 
//			 int rows = jdbc.execUpdate(sql, values); 
//			 System.out.println(rows); 
//			} catch (Exception e) { 
//			 e.printStackTrace(); 
//			}
	}
}
