package DAO;

import java.sql.*;
import java.util.*;
import ENTITY.Employee;
import UTILS.Jdbc;
import java.sql.Date;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void create(Employee emp) {
	    String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = Jdbc.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, emp.getId());
	        pstmt.setString(2, emp.getPassword());
	        pstmt.setString(3, emp.getFullname());
	        pstmt.setString(4, emp.getPhoto());
	        pstmt.setBoolean(5, emp.isGender());

	        if (emp.getBirthday() != null) {
	            pstmt.setDate(6, new java.sql.Date(emp.getBirthday().getTime()));
	        } else {
	            pstmt.setNull(6, java.sql.Types.DATE); 
	        }

	        pstmt.setBigDecimal(7, emp.getSalary());
	        pstmt.setString(8, emp.getDepartmentId());

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	@Override
	public void update(Employee emp) {
	    String sql = "UPDATE Employees SET Password=?, Fullname=?, Photo=?, Gender=?, Birthday=?, Salary=?, DepartmentId=? WHERE Id=?";
	    try (Connection conn = Jdbc.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, emp.getPassword());
	        pstmt.setString(2, emp.getFullname());
	        pstmt.setString(3, emp.getPhoto());
	        pstmt.setBoolean(4, emp.isGender());
	        if (emp.getBirthday() != null) {
	            pstmt.setDate(5, new java.sql.Date(emp.getBirthday().getTime()));
	        } else {
	            pstmt.setNull(5, java.sql.Types.DATE); 
	        }

	        pstmt.setBigDecimal(6, emp.getSalary());
	        pstmt.setString(7, emp.getDepartmentId());
	        pstmt.setString(8, emp.getId());

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
//	        throw new RuntimeException(e);
	    	System.out.println(e);
	    }
	}


    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Employees WHERE Id=?";
        Jdbc.execUpdate(sql, id);
    }

    @Override
    public Employee findById(String id) {
        String sql = "SELECT * FROM Employees WHERE Id=?";
        ResultSet rs = Jdbc.execQuery(sql, id);
        try {
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employees";
        ResultSet rs = Jdbc.execQuery(sql);
        List<Employee> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getString("Id"));
        e.setPassword(rs.getString("Password"));
        e.setFullname(rs.getString("Fullname"));
        e.setPhoto(rs.getString("Photo"));
        e.setGender(rs.getBoolean("Gender"));
        e.setBirthday(rs.getDate("Birthday"));
        e.setSalary(rs.getBigDecimal("Salary"));
        e.setDepartmentId(rs.getString("DepartmentId"));
        return e;
    }
}
