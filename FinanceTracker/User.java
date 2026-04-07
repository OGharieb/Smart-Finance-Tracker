package FinanceTracker;

public class User {
    private String UID;
    private String mail;
    private String name;
    
    
    //init

    public User(String newName,String newUID,String newMail){
        this.UID=newUID;
        this.name=newName;
        this.mail=newMail;
    }
    
    
    
    //setters
    public void setMail(String newMail){
        this.mail=newMail;
    }
    public void setName(String newName){
        this.name=newName;
    }

    //getters
    public String getUID(){
        return this.UID;
    }

    public String getmail(){
        return this.mail;
    }

    public String getname(){
        return this.name;
    }

}
