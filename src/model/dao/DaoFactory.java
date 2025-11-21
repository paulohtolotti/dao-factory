package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

import static db.DB.conn;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.openConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC();
    }
}
