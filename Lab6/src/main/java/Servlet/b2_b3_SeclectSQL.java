package Servlet;

import java.sql.ResultSet;

public class b2_b3_SeclectSQL {
	public static void main(String[] args) {
		try {
			String sql = "SELECT * FROM Departments";
			ResultSet resultSet = jdbc.execQuery(sql);
			while (resultSet.next()) {
				String value = resultSet.getString("name");
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		try {
//			String sql = "INSERT..., UPDATE..., DELETE…";
//			int rows = jdbc.execUpdate(sql);
//			System.out.println(rows);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
