package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public DepartmentDaoJDBC() {

    }

    @Override
    public void insert(Department obj) {
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement("INSERT INTO department (Id, Name) VALUES (?, ?)");

            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());

            Department dep = this.findById(obj.getId());

            if(dep != null) throw new DbException("Department already registered");

            ps.executeUpdate();

            System.out.println("Department successfully added");

        } catch (SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement ps = null;

        try {
            ps = this.conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");


            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getId());

            int rows = ps.executeUpdate();

            if(rows == 0) System.out.println("Department not registered");
            else System.out.println("Department " + obj.getName() + " successfully updated");

        } catch(SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if(rows == 0) System.out.println("Department already deleted or never registered");
            else System.out.println(rows + " deleted successfully.");

        } catch(SQLException err) {
            throw new DbException(err.getMessage());

        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = this.conn.prepareStatement("SELECT * FROM department WHERE Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                return new Department(rs.getInt("Id"), rs.getString("Name"));
            }

        } catch (SQLException err) {
            throw new DbException(err.getMessage());

        } finally {

            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

        return null;

    }

    @Override
    public List<Department> findAll() {

         Statement st = null;
         ResultSet rs = null;
         List<Department> departments = new ArrayList<>();

         try {
             st = this.conn.createStatement();

             rs = st.executeQuery("SELECT * FROM department");

             while(rs.next()) {
                 Department d = instantiateDepartment(rs.getInt("Id"), rs.getString("Name"));
                 departments.add(d);
             }

             return departments;
         } catch(SQLException err) {

         } finally {
             DB.closeResultSet(rs);
             DB.closeStatement(st);
         }
         return null;
    }

    private static Department instantiateDepartment(Integer id, String name) {
        return new Department(id, name);
    }
}
