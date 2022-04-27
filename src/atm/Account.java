package atm;

public class Account {

    protected static int count = 0;

    private final String firstName,lastName;
    private final int idNumber;
    private int balance;
    private char[] pin;
    private boolean frozen;

    protected Account(String firstName, String lastName, char[] pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.idNumber = count++;
        this.balance = 0;
        this.frozen = false;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected char[] getPin() {
        return pin;
    }

    protected int getBalance() {
        return balance;
    }

    protected boolean isFrozen() {
        return frozen;
    }

    protected void deposit(int amount) {
        if (!frozen && amount > 0) {
            balance += amount;
        }
    }

    protected void withdraw(int amount) {
        if (!frozen && amount <= balance && amount > 0) {
            balance -= amount;
        }
    }

    protected void setPin(char[] pin) {
        this.pin = pin;
    }

    protected void toggleFreeze() {
        frozen = !frozen;
    }

    protected boolean matchPin(char[] other) {
        for (int i = 0; i < 4; i++) if (pin[i] != other[i]) return false;
        return true;
    }

}
