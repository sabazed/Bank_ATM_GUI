package atm;

import java.util.ArrayList;

public class ATM_Main {

    protected static ArrayList<Account> database = new ArrayList<>();

    static {
        database.add(new Account("Saba", "Zed", new char[]{'0', '0', '0', '0'}));
        database.add(new Account("Giorgi", "Zed", new char[]{'0', '0', '0', '1'}));
    }

    public static void main(String[] args) {
        ATM_Welcome.open();
    }

}
