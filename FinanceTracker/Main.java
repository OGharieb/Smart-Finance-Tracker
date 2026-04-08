package FinanceTracker;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User myUser = new User("Alex", "EG-2026-X", "alex@cce.edu.eg");
        
        
        FileHandling storage = new FileHandling(myUser);
        Wallet myWallet = new Wallet(myUser);

        List<Transaction> pastData = storage.loadedTransactions();
        for (Transaction t : pastData) {
            myWallet.addTransaction(t);
        }

        boolean isRunning = true;
        System.out.println("--- Smart Finance Tracker  ---");
        System.out.println("Welcome back, " + myUser.getName() + "!");

        while (isRunning) {
            // Display Dashboard
            System.out.println("\n===============================");
            System.out.println("   " + myUser.getName().toUpperCase() + "'S CURRENT STATUS");
            System.out.println("   Balance: $" + myWallet.checkBalance());
            System.out.println("===============================");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View History (with IDs)");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1 || choice == 2) {
                
                System.out.print("Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Category: ");
                String category = scanner.nextLine();

                boolean isIncome = (choice == 1);
                
                
                Transaction t = new Transaction(amount, category, isIncome);
                myWallet.addTransaction(t);
                
                storage.saveTransactions(myWallet.getTransactions());
                System.out.println("Done! File updated.");

            } else if (choice == 3) {
                // VIEWING HISTORY
                System.out.println("\nID | DATE       | TYPE    | CATEGORY   | AMOUNT");
                System.out.println("-------------------------------------------------");
                
                List<Transaction> history = myWallet.getTransactions();
                if (history.isEmpty()) {
                    System.out.println("   (No transactions recorded yet)");
                } else {
                    for (int i = 0; i < history.size(); i++) {
                        Transaction t = history.get(i);
                        String type = t.isIncome() ? "INCOME " : "EXPENSE";
                        System.out.println(i + "  | " + t.getDate() + " | " + type + " | " + t.getCategory() + " | $" + t.getAmount());
                    }
                }

            } else if (choice == 4) {
                // EXIT
                System.out.println("Session ended. Your data is saved in " + myUser.getName() + "_transactions.csv");
                isRunning = false;
            } else {
                System.out.println("Invalid selection. Please enter 1-4.");
            }
        }

        scanner.close();
    }
}