import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;

public class AdventureW {


    private static final String DB_HOST = "morfeusz.wszib.edu.pl";
    public static final int DB_PORT = 1433;
    public static final String DB_USER = "jmikulsk";
    public static final String DB_PASS = "";
    public static final String DB_NAME = "AdventureWorks";

    public static final String DB_TOP10_EMPLOYEE = "SELECT  TOP 10 FirstName, LastName from AdventureWorks.Person.Contact where LastName ='Anderson' ";
    public static final String DB_TITLE = "SELECT distinct(Title) as JobTitle from AdventureWorks.HumanResources.Employee";


    public static void main(String[] args) {
        AdventureW aw = new AdventureW();

        try (Connection conn = aw.connect(DB_USER, DB_PASS, DB_NAME);
             Statement stmt = conn.createStatement()) {

            ResultSet rs =stmt.executeQuery(DB_TOP10_EMPLOYEE);
            while(rs.next()){
                System.out.println(rs.getString("FirstName") +"   "+ rs.getString("LastName"));
            }
            System.out.println("------------------------------------------------");

             rs = stmt.executeQuery(DB_TITLE);
            while(rs.next()){
                System.out.println(rs.getString("JobTitle"));
            }




        } catch (SQLException e) {
            System.out.println("błąd :" + e.getMessage());
        }
    }


    private Connection connect(String userName, String password, String dbName) throws SQLServerException {


        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(userName);
        ds.setPassword(password);
        ds.setServerName(DB_HOST);
        ds.setPortNumber(DB_PORT);
        ds.setDatabaseName(dbName);
        return ds.getConnection();

    }

}





