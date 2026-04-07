package FinanceTracker;

import java.time.LocalDate;


public class Transaction {
    private double amount;
    private String category;
    private boolean isIncome;
    private LocalDate date;

//init
    public Transaction(double amount,String category,boolean isIncome,LocalDate date){
    this.amount=amount;
    this.category=category;
    this.isIncome=isIncome;
    this.date=LocalDate.now();
    }

//getters
    public double getaAmount(){
    return this.amount;
    }
    public String getCategory(){
    return this.category;
    }
    public boolean isIncome(){
    return this.isIncome;
    }

    public LocalDate getDate(){
        return this.date;
    }

}


