package FinanceTracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Wallet {
    private User owner;
    private List<Transaction> transactions;

    public Wallet(User owner){
        this.owner=owner;
        this.transactions=new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction t){
        this.transactions.add(t);
        System.out.println("Transaction successfully added");
    }
    public double checkBalance(){
        double balance=0.0;
        for(Transaction t: this.transactions){
            if(t.isIncome())
                balance+=t.getAmount();
            else
                balance-=t.getAmount();
            }
            return balance;
        }

    public User getOwner() {
        return this.owner;
    }
    public List<Transaction> getTransactions(){
        return Collections.unmodifiableList(this.transactions);
    }
    }


