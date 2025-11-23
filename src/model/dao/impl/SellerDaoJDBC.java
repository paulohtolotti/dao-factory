package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public SellerDaoJDBC() {

    }
    @Override
    public void insert(Seller obj) throws SQLException {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement("DELETE FROM seller WHERE Id = ?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if(rows == 0) System.out.println("Seller already deleted or never registered");
            else System.out.println(rows + " deleted successfully.");

        } catch(SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs=  null;

        try {

            ps = this.conn.prepareStatement("SELECT seller.*, department.Id as DepartmentId, department.Name as DepartmentName" +
                    " FROM seller JOIN department ON seller.DepartmentId = department.Id" +
                    " WHERE seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department d = instantiateDepartment(rs);
                return  instantiateSeller(rs, d);
            }

            return null;

        } catch (SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Seller> findAll() {
        Statement ps = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();

        try {
            ps = this.conn.createStatement();
            rs = ps.executeQuery("SELECT seller.*, department.Id as DepartmentId, department.Name as DepartmentName" +
                    " FROM seller JOIN department ON seller.DepartmentId = department.Id");

            Map<Integer, Department> depMap = new HashMap<>();

            while(rs.next()) {
                if(!depMap.containsKey(rs.getInt("DepartmentId"))) {
                    depMap.put(rs.getInt("DepartmentId"),instantiateDepartment(rs));
                }
                sellers.add(instantiateSeller(rs, depMap.get(rs.getInt("DepartmentId"))));
            }

            return sellers;

        } catch(SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department d) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT seller.*, department.Id as DepartmentId, department.Name as DepartmentName " +
                    "FROM seller JOIN department ON department.Id = seller.DepartmentId WHERE " +
                    "department.Id = ? ORDER BY Name");

            ps.setInt(1, d.getId());
            rs = ps.executeQuery();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null) {
                    map.put(rs.getInt("DepartmentId"), instantiateDepartment(rs));
                }

                sellers.add(instantiateSeller(rs, map.get(rs.getInt("DepartmentId"))));
            }

            return sellers;

        } catch(SQLException err) {
            throw new DbException(err.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }

    private Seller instantiateSeller(ResultSet rs, Department d) throws SQLException {
        return new Seller(rs.getInt("Id"), d, rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate")
                .toLocalDate(),rs.getDouble("BaseSalary"));
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        return new Department(rs.getInt("DepartmentId"), rs.getString("DepartmentName"));
    }


}
