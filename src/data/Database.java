package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getDatabaseConnection() throws SQLException {
        String protocol = "jdbc";
        String dbServer = "mysql";
        String host = "localhost";
        String port = "3306";
        String dbName = "world";

        String dbUrl = String.format("%s:%s://%s:%s/%s", protocol, dbServer, host, port, dbName);
        System.out.println(dbUrl);

        /**
         * TODO - change your login credentials if necessary
         */
        return DriverManager.getConnection(dbUrl, "root", "minivan.333");

    }

}
