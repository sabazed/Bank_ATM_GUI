package atm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    // Keep count of accounts for providing IDs
    protected static int count = 0;
    protected static String nextId = "000000";

    // Account personal data
    private final String firstName, lastName;
    private final String idNumber;
    private int balance;
    private String pin; // String instead of int since the return type from the password field is char[]
    private boolean frozen;

    // Constructor providing default values and ID
    protected Account(ResultSet rs) throws SQLException {
        this.idNumber = rs.getString(1);
        this.pin = rs.getString(2);
        this.firstName = rs.getString(3);
        this.lastName = rs.getString(4);
        this.balance = rs.getInt(5);
        this.frozen = rs.getBoolean(6);
    }

    protected String getId() {
        return idNumber;
    }

    // Normal getters
    protected String getFirstName() {
        return firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected String getPin() {
        return pin;
    }

    protected int getBalance() {
        return balance;
    }

    // Checks if the account is frozen
    protected boolean isFrozen() {
        return frozen;
    }


    // Methods for depositing and withdrawing money
    protected void deposit(int amount) {
        if (!frozen && amount > 0) {
            balance += amount;
        }
    }

    protected boolean withdraw(int amount) {
        if (!frozen && amount <= balance && amount > 0) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Method for changing pin
    protected void setPin(String pin) {
        this.pin = pin;
    }

    // For (un)freezing account
    protected void toggleFreeze() {
        frozen = !frozen;
    }

    // Update count value of program launch
    protected static void updateCount(int num) {
        count = num;
        nextId = String.format("%06d", count);
    }

    protected static void update() {
        nextId = String.format("%06d", ++count);
    }

}
