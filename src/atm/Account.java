/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author protege
 */
public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private boolean status;
   private Stack<String> history = new Stack<String>();
   private HashSet<VirtualAccount> listBill;   

   // Account constructor initializes attributes

    public Account(int accountNumber, int pin, double availableBalance, double totalBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.availableBalance = availableBalance;
        this.totalBalance = totalBalance;
        status=true;
        listBill = new HashSet<>();    
        listBill.add(new VirtualAccount(11111, 200, "KAIEX28"));
        listBill.add(new VirtualAccount(11111, 20, "BIOSRALPHA8"));
        listBill.add(new VirtualAccount(11111, 300, "LIONAIR2"));
        listBill.add(new VirtualAccount(11111, 10, "ELEC230"));
        listBill.add(new VirtualAccount(11111, 20, "PULSE212"));
        listBill.add(new VirtualAccount(11111, 10, "BIOSAVENGERE7"));
        listBill.add(new VirtualAccount(11111, 25, "LIONAIR3"));
    }
   

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      }
      else {
         return false;
      }
   } 

   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   } 

   public void credit(double amount) {
     this.totalBalance+=amount;
   }

   public void debit(double amount) {
       if(this.availableBalance>0 && getTotalBalance()>amount){
           this.availableBalance-=amount;
           this.totalBalance-=amount;
       }
       
   }   
   public void creditForTransfer(double amount){
       this.availableBalance+=amount;
   }
   public int getAccountNumber() {
      return accountNumber;  
   }
   
   public boolean getAccountStatus(){
        return status;
    }
    
    public void blockAccount(){
        status=false;
    }
    
    public void recoverAccount(){
          if(totalBalance > 0){
        status=true;
        totalBalance= totalBalance-totalBalance*0.1;
        }else System.out.println("\nYou have zero money in your Account please contact the operator");
    }
    
    public void addHistory(String riwayat){
        getHistory().push(riwayat);
   }
    
    public String toString(){
        return ("" + getHistory() +"\n");
    }
    
    public Stack<String> getHistory() {
        return history;
    }
    
    public void setHistory(Stack<String> history) {
        this.history = history;
    }
    
    public int getPin() {
        return pin;
    }
    
    public void setPin(int pin) {
        this.pin = pin;
    }
    
    //wahyu
    public VirtualAccount getVA(String code) {
      for(VirtualAccount a : listBill){
          if(a.getCode().equalsIgnoreCase(code)) return a;
      }
      return null; // if no matching account was found, return null
   } 
    
    public int getReceiver(String code){
        return getVA(code).getAccountReceiver();
    }
    
    public double getBill(String code){
        return getVA(code).getBill();
    }
    
    
   
   
} 