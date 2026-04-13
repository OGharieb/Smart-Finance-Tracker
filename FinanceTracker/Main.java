package FinanceTracker;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User myUser = new User("Alex", "EG-2026-X", "alex@cce.edu.eg");
        
        FileHandling storage = new FileHandling(myUser);
        Wallet myWallet = new Wallet(myUser);

        // RESTORE DATA
        for (Transaction t : storage.loadedTransactions()) {
            myWallet.addTransaction(t);
        }

        boolean isRunning = true;
        System.out.println("--- Smart Finance Tracker ---");

        while (isRunning) {
            System.out.println("\n===============================");
            System.out.println("   Balance: $" + myWallet.checkBalance());
            System.out.println("===============================");
            System.out.println("1. Add Income   2. Add Expense");
            System.out.println("3. View History 4. Reverse Item");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1 || choice == 2) {
                System.out.print("Amount: ");
                double amount = scanner.nextDouble(); scanner.nextLine();
                System.out.print("Category: ");
                String category = scanner.nextLine();

                Transaction t = new Transaction(amount, category, (choice == 1));
                myWallet.addTransaction(t);
                storage.saveTransactions(myWallet.getTransactions());

            } else if (choice == 3 || choice == 4) {
                List<Transaction> history = myWallet.getTransactions();
                
                System.out.println("\nID | DATE       | TYPE    | CATEGORY   | AMOUNT   | STATUS");
                System.out.println("----------------------------------------------------------");
                for (int i = 0; i < history.size(); i++) {
                    Transaction t = history.get(i);
                    String status = t.isReversed() ? "[REVERSED]" : "Active";
                    System.out.println(i + "  | " + t.getDate() + " | " + (t.isIncome() ? "INCOME " : "EXPENSE") + " | " + t.getCategory() + " | $" + t.getAmount() + " | " + status);
                }

                if (choice == 4 && !history.isEmpty()) {
                    System.out.print("\nEnter ID to reverse: ");
                    int id = scanner.nextInt();
                    if (id >= 0 && id < history.size()) {
                        myWallet.revTransaction(history.get(id));
                        storage.saveTransactions(myWallet.getTransactions());
                    } else {
                        System.out.println("Invalid ID.");
                    }
                }

            } else if (choice == 5) {
                isRunning = false;
            }
        }
        scanner.close();
    }
}