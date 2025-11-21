package application;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        Department d = new Department(1,"TI");
        Seller s = new Seller(1, d, "Joshua", "josh@gmail.com", LocalDate.now(), 2500.54);
        System.out.println(d);
        System.out.println(s);
        Connection conn = null;
        try {
            conn = DB.openConnection();


        } catch (DbException err) {
            System.out.println(err.getMessage());

        } finally {

            DB.closeConnection();
            System.out.println("EOP");
        }

    }
}
