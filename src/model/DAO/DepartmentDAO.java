package model.DAO;

import model.Department;

import java.util.List;

public interface DepartmentDAO {
    void insert(Department obj);
    void update(Department obj);
    boolean deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
