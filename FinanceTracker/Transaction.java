package FinanceTracker;

import java.time.LocalDate;


public class Transaction {
    private double amount;
    private String category;
    private boolean isIncome;
    private LocalDate date;

//init
    public Transaction(double amount,String category,boolean isIncome){
    this.amount=amount;
    this.category=category;
    this.isIncome=isIncome;
    this.date=LocalDate.now();
    }

    public Transaction(double amount,String category,boolean isIncome,int year,int month,int day){
    this.amount=amount;
    this.category=category;
    this.isIncome=isIncome;
    this.date=LocalDate.of(year,month,day);
    }

//getters
    public double getAmount(){
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


