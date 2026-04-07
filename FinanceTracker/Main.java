package FinanceTracker;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. BOOT SEQUENCE: Create User (Name, UID, Mail)
        // Your User class requires these 3 specific arguments
        User myUser = new User("Alex", "EG-2026-X", "alex@cce.edu.eg");
        
        // 2. SERVICES: Initialize Storage and the Wallet
        FileHandling storage = new FileHandling(myUser);
        Wallet myWallet = new Wallet(myUser);

        // 3. RESTORE: Load past data from the CSV file immediately
        List<Transaction> pastData = storage.loadedTransactions();
        for (Transaction t : pastData) {
            myWallet.addTransaction(t);
        }

        boolean isRunning = true;
        System.out.println("--- Smart Finance Tracker v1.0 ---");
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
            scanner.nextLine(); // Clear the input buffer

            if (choice == 1 || choice == 2) {
                // ADDING TRANSACTION
                System.out.print("Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Category: ");
                String category = scanner.nextLine();

                boolean isIncome = (choice == 1);
                
                // Create object (sets date to .now() automatically)
                Transaction t = new Transaction(amount, category, isIncome);
                myWallet.addTransaction(t);
                
                // Save to disk immediately using your 'saveTransactions' method
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
                        // Using 'i' as the ID so you know which one to reverse later!
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