package DAO;
import java.util.List;
import ENTITY.Department;

public interface DepartmentDAO { 
	 List<Department> findAll();  
	 Department findById(String id); 
	 void create(Department item); 
	 void update(Department item); 
	 void deleteById(String id); 
	}
