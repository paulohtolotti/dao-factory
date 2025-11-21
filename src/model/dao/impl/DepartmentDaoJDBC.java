package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return List.of();
    }
}
