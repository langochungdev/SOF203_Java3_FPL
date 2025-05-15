package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ENTITY.Department;
import UTILS.Jdbc;

public class DepartmentDAOImpl implements DepartmentDAO {
	@Override
	public List<Department> findAll() {
		String sql = "SELECT * FROM Departments";
		try {
			List<Department> entities = new ArrayList<>();
			Object[] values = {};
			ResultSet resultSet = Jdbc.execQuery(sql, values);
			while (resultSet.next()) {
				Department entity = new Department();
				entity.setId(resultSet.getString("Id"));
				entity.setName(resultSet.getString("Name"));
				entity.setDescription(resultSet.getString("Description"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Department findById(String id) {
		String sql = "SELECT * FROM Departments WHERE Id=?";
		try {
			Object[] values = { id };
			ResultSet resultSet = Jdbc.execQuery(sql, values);
			if (resultSet.next()) {
				Department entity = new Department();
				entity.setId(resultSet.getString("Id"));
				entity.setName(resultSet.getString("Name"));
				entity.setDescription(resultSet.getString("Description"));
				return entity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}

	@Override 
	 public void create(Department entity) { 
	  String sql = "INSERT INTO Departments(Id, Name, Description) VALUES(?, ?, ?)"; 
	  try { 
	   Object[] values = {  
	    entity.getId(),  
	    entity.getName(),  
	    entity.getDescription()  
	   }; 
	   Jdbc.execUpdate(sql, values); 
	  } catch (Exception e) { 
	   throw new RuntimeException(e); 
	  } 
	 }

	@Override 
	 public void update(Department entity) { 
		 String sql = "UPDATE Departments SET Name=?, Description=? WHERE Id=?"; 
				   try { 
				    Object[] values = {  
				     entity.getName(),  
				     entity.getDescription(),  
				     entity.getId()  
				    }; 
				    Jdbc.execUpdate(sql, values); 
				   } catch (Exception e) { 
				    throw new RuntimeException(e); 
				   } 
				  }

	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM Departments WHERE Id=?";
		try {
			Object[] values = { id };
			Jdbc.execUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}