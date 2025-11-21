package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public SellerDaoJDBC() {

    }
    @Override
    public void insert(Seller obj) throws SQLException{

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs=  null;

        try {

            ps = this.conn.prepareStatement("SELECT * FROM seller JOIN department ON seller.DepartmentId = department.Id" +
                    " WHERE seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department d = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, d);
                return seller;
            }

            return null;

        } catch (SQLException err) {
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
        return new Department(rs.getInt("DepartmentId"), rs.getString(8));
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
