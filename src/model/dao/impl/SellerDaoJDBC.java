package model.dao.impl;

import db.DB;
import model.dao.SellerDao;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    public SellerDaoJDBC() {

    }
    @Override
    public void insert(Seller obj) throws SQLException{
        PreparedStatement ps = null;
        try {

            Connection conn = DB.openConnection();

            ps = conn.prepareStatement("INSERT INTO Seller VALUES" +
                    "(?, ?, ?, ?, ?, ?)");

            int rows = ps.executeUpdate();
            if(rows > 0) System.out.println("Added " + rows + " successfully");

        } catch (SQLException err) {

        } finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }
    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
