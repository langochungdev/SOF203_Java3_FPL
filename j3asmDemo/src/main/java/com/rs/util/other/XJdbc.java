/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rs.util.other;

//import utils.MsgBox;
//import encrypt.AES;
//import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import utils.XFile;

/**
 *
 * @author ndhlt
 */
public final class XJdbc {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/java3_asm";
	private static Connection con = null;

	static {
		try {
			Class.forName(DRIVER);
			openConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}
    }

	private static final void openConnection() throws SQLException {
		if(con==null||con.isClosed()) {
			System.out.println("Connect successful");
			con = DriverManager.getConnection(url, Accounts.SQL_ACC.getUsername(), Accounts.SQL_ACC.getPassword());
		}
	}

	private static List<Map<String, Object>> parseResultSet(ResultSet rs) throws SQLException {
		List<Map<String, Object>> maps = new ArrayList<>();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<>();
			ResultSetMetaData data = rs.getMetaData();
			for (int i = 0; i < data.getColumnCount(); i++) {
				map.put(data.getColumnName(i + 1), rs.getObject(i + 1));
			}
			maps.add(map);
		}
		return maps;
	}

	public static final List<Map<String, Object>> select(String sql, Object... args)
			throws SQLException, ClassNotFoundException {
		List<Map<String, Object>> maps = new ArrayList<>();
		try (PreparedStatement pst = con.prepareStatement(sql)) {
			if (args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					pst.setObject(i + 1, args[i]);
				}
			}
			try (ResultSet rs = pst.executeQuery()) {
				return parseResultSet(rs);
			}
		}
    }

	public static final Object getValue(String sql, Object... args) throws SQLException, ClassNotFoundException {
		List<Map<String, Object>> list = select(sql, args);
		if (!list.isEmpty()) {
			return list.get(0).values().iterator().next(); // Lấy giá trị đầu tiên từ bản ghi đầu tiên
		}
		throw new SQLException("No results found for query: " + sql); // Ném ngoại lệ nếu không có kết quả
	}

	public static final int IUD(String sql, Object... args) throws SQLException, ClassNotFoundException {
		try (PreparedStatement pst = con.prepareStatement(sql)) {
			if (args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					pst.setObject(i + 1, args[i]);
				}
			}
			return pst.executeUpdate();
		}
	}

	public static Object callProcedure(String sql, int output, SQLType sqlType, Object... args)
			throws SQLException {
		boolean result;
		try (CallableStatement cst = con.prepareCall(sql)) {
			if (args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					cst.setObject(i + 1, args[i]);
				}
				if(output>0&&sqlType!=null){
					cst.registerOutParameter(output, sqlType);
				}
			}
			result = cst.execute();
			List<Map<String, Object>> maps = parseResultSet(cst.getResultSet());
			int updateCount = cst.getUpdateCount();
			return result ? maps : updateCount;
		}

	}

	/**
	 * Tạo bean và đọc giá trị các column vào các property cùng tên của bean <br>
	 * Ví dụ:
	 * 
	 * <pre>
	 * String sql = "SELECT * FROM Users";
	 * Object[] values = {};
	 * ResultSet resultSet = Jdbc.executeQuery(sql, values);
	 * while (resultSet.next()) {
	 * 	User user = XJdbc.getBean(resultSet, User.class);
	 * }
	 * </pre>
	 */
	private static <T> T getBean(Class<T> beanClass, Map<String, Object> map) {
		try {
			T bean = beanClass.getDeclaredConstructor().newInstance();
//			BeanUtils.populate(bean, map);
			Method[] methods = beanClass.getMethods();
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					String column = method.getName().substring(3);
					Object value = map.get(column);
					if(value!=null) {
						method.invoke(bean, value);
					}
				}
			}
			return bean;
		} catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException
				| SecurityException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Truy vấn các bản ghi và chuyển đổi sang &lt;T&gt; <br>
	 * Ví dụ:
	 * 
	 * <pre>
	 * String sql = "SELECT * FROM Users";
	 * Object[] values = {};
	 * List&lt;User&gt; list = XJdbc.getResultList(User.class, sql, values);
	 * </pre>
	 */
	public static <T> List<T> getResultList(Class<T> beanClass, String sql, Object... values) {
		try {
			List<T> entities = new ArrayList<>();
			List<Map<String, Object>> list = select(sql, values);
			for (Map<String, Object> map : list) {
				entities.add(getBean(beanClass, map));
			}
			return entities;
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Chuyển đổi danh sách map sang &lt;T&gt; <br>
	 * Ví dụ:
	 *
	 * <pre>
	 * List&lt;Map&lt;String,Object&gt;&gt; maps
	 * List&lt;User&gt; list = XJdbc.getResultList(User.class, maps);
	 * </pre>
	 */
	public static <T> List<T> getResultList(Class<T> beanClass, List<Map<String, Object>> maps) {
        List<T> entities = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            entities.add(getBean(beanClass, map));
        }
        return entities;
    }

	/**
	 * Truy vấn bản ghi và chuyển đổi sang Bean <br>
	 * Ví dụ:
	 * 
	 * <pre>
	 * String sql = "SELECT * FROM Users WHERE Username=?";
	 * Object[] values = { "NghiemN" };
	 * User user = XJdbc.getSingleResult(User.class, sql, values);
	 * </pre>
	 */
	public static <T> T getSingleResult(Class<T> beanClass, String sql, Object... values) {
		List<T> lists = getResultList(beanClass, sql, values);
		if (!lists.isEmpty()) {
			return lists.get(0);
		}
		return null;
	}
	
	public static void closeConnection() throws SQLException {
		if(con!=null&&!con.isClosed()) {
			con.close();
		}
	}

	public static void main(String[] args) {

			if (con != null) {
				System.out.println("Kết nối cơ sở dữ liệu thành công!");
			} else {
				System.out.println("Kết nối cơ sở dữ liệu thất bại!");
			}
	}
}
