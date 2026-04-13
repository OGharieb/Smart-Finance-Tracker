    package FinanceTracker;

    import java.time.LocalDate;


    public class Transaction {
        private double amount;
        private String category;
        private boolean isIncome;
        private LocalDate date;
        private boolean reversed=false;

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
        public Transaction(double amount,String category,boolean isIncome,LocalDate date,boolean reversed){
        this.amount=amount;
        this.category=category;
        this.isIncome=isIncome;
        this.date=date;
        this.reversed=reversed;
        }
        public Transaction(double amount,String category,boolean isIncome,boolean isReversed){
        this.amount=amount;
        this.category=category;
        this.isIncome=isIncome;
        this.date=LocalDate.now();
        this.reversed=isReversed;
        }

        public void setReverse(){
            this.reversed=true;
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

        public boolean isReversed(){
            return this.reversed;
        }

    }


