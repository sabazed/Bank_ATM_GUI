package atm;

import java.sql.*;

public class ATM_Main {

    // db will be the access point to the database;
    protected static Connection db;
    private static final String url = "jdbc:postgresql://localhost:5432/bankatm";
    private static final String user = "postgres";
    private static final String pswd = "changeme";

    // Method for creating new accounts
    protected static void addAccount(String firstName, String lastName, String pin) {
        try {
            PreparedStatement insert = db.prepareStatement("INSERT INTO accounts(id, pin, \"firstName\", \"lastName\") VALUES(?, ?, ?, ?)");
            insert.setString(1, Account.nextId);
            insert.setString(2, pin);
            insert.setString(3, firstName);
            insert.setString(4, lastName);
            insert.executeUpdate();
            insert.close();
            Account.update();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method for updating account data
    protected static void updateAccount(Account acc) {
        try {
            PreparedStatement update = db.prepareStatement("UPDATE accounts SET " +
                                                                            "pin = ?," +
                                                                            "balance = ?," +
                                                                            "frozen = ?" +
                                                                "WHERE id = ?");
            update.setString(1, acc.getPin());
            update.setInt(2, acc.getBalance());
            update.setBoolean(3, acc.isFrozen());
            update.setString(4, acc.getId());
            update.executeUpdate();
            update.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // The main function to run the program
    public static void main(String[] args) throws SQLException {
        // Connect to the database
        try {
            Class.forName("org.postgresql.Driver");
            db = DriverManager.getConnection(url, user, pswd);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot establish connection! Aborting...");
            return;
        }
        System.out.println("Connected to the database...");

        // Update the Account count by counting database rows
        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(id) AS num FROM accounts");
        if (rs.next()) Account.updateCount(rs.getInt(1));
        rs.close();
        stmt.close();

        // Start the program
        ATM_Welcome.open();


    }

}
