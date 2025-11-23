# DAO AND FACTORY PATTERN DEMO

Demo project for studying the DAO and Factory Pattern in Java, using the MySQL and JDBC.

## REQUIREMENTS
A) MySQL Community 8+, MySQL Java Connector 9.5.0+ and JDK17+;

B) Create a Database and create two tables: Client and Product;

C) Set up the db.properties file in the root of the project. 

## SQL Tables

### Seller
+--------------+--------------+------+-----+---------+-------+
| Field        | Type         | Null | Key | Default | Extra |
+--------------+--------------+------+-----+---------+-------+
| Id           | int          | NO   | PRI | NULL    |       |
| Name         | varchar(250) | YES  |     | NULL    |       |
| Email        | varchar(250) | YES  |     | NULL    |       |
| BirthDate    | date         | YES  |     | NULL    |       |
| BaseSalary   | double       | YES  |     | NULL    |       |
| DepartmentId | int          | YES  | MUL | NULL    |       |
+--------------+--------------+------+-----+---------+-------+

## Department
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| Id    | int         | NO   | PRI | NULL    |       |
| Name  | varchar(60) | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+
