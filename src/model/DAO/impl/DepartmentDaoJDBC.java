package model.DAO.impl;

import db.DB;
import db.DbException;
import model.DAO.DepartmentDAO;
import model.Department;
import utils.Instantiation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT Id, Name FROM department WHERE Id = ?");
            ps.setInt(1, id);;
            rs = ps.executeQuery();

            if (rs.next()){
                return Instantiation.instantiateDepartment(rs);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Department> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT Id, Name FROM department");
            rs = ps.executeQuery();

            while (rs.next()){
                list.add(Instantiation.instantiateDepartment(rs));
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
