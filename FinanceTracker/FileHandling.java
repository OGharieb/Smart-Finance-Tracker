package FinanceTracker;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandling {
    private User user;
    private String filename;

    public FileHandling(User user){
        this.user=user;
        this.filename= user.getName()+"_transactions.csv";
    }

    public void saveTransactions(List<Transaction> transactions){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            for(Transaction t : transactions){
                if(t.isIncome())
                    writer.println(t.getAmount()+","+t.getCategory()+",income,"+t.getDate()+","+t.isReversed());
                else 
                    writer.println(t.getAmount()+","+t.getCategory()+",expense,"+t.getDate()+","+t.isReversed());
            }
        }catch(IOException e){
            System.out.println("Couldn't Save file " + e.getMessage());
        }
    
    }

    public List<Transaction> loadedTransactions(){
        List<Transaction> loadedList = new ArrayList<>();
        File file= new File(filename);

        if(!file.exists())
            return loadedList;

        try(BufferedReader reader= new BufferedReader(new FileReader(filename))){
            String line;
            while ((line=reader.readLine())!=null) {
                String[] data=line.split(",");
                double amount= Double.parseDouble(data[0]);
                String category=data[1];
                boolean type=false;
                if(data[2].equals("income"))
                    type=true;
                LocalDate date = LocalDate.parse(data[3]);
                boolean reverse = Boolean.parseBoolean(data[4]);
                Transaction t = new Transaction(amount, category, type,date,reverse);
                loadedList.add(t);
            }

        }catch(IOException e ){
            System.out.println("Couldnt load file "+ e.getMessage());
        }
    return loadedList;
    }

}
