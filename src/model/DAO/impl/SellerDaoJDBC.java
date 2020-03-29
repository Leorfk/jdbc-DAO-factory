package model.DAO.impl;

import db.DB;
import db.DbException;
import model.DAO.SellerDAO;
import model.Department;
import model.Seller;
import utils.Instantiation;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "INSERT INTO seller" +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                    "VALUES" +
                    "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4, obj.getBaseSalary());
            ps.setInt(5, obj.getDepartment().getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE seller " +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                            "WHERE Id = ?");

            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4, obj.getBaseSalary());
            ps.setInt(5, obj.getDepartment().getId());
            ps.setInt(6, obj.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "DELETE FROM seller WHERE Id = ?");

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0){
                return false;
            }
            return true;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName " +
                    "FROM seller " +
                    "INNER JOIN department ON seller.DepartmentId = department.ID " +
                    "Where seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                Department department = Instantiation.instantiateDepartment(rs);
                Seller seller = Instantiation.instantiateSeller(rs, department);
                return seller;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");

            ps.setInt(1, department.getId());
            rs = ps.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = Instantiation.instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Seller seller = Instantiation.instantiateSeller(rs, dep);
                sellers.add(seller);

            }
            return sellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "ORDER BY Name");
            rs = ps.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = Instantiation.instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Seller seller = Instantiation.instantiateSeller(rs, dep);
                sellers.add(seller);

            }
            return sellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
