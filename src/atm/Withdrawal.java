/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author protege
 */
// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   private Struk struk;

   // constant corresponding to menu option to cancel
   private int WITHDRAWAL = 1;
   private final static int CANCELED = 6;

   // Withdrawal constructor

    public Withdrawal(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      this.keypad=atmKeypad;
      this.cashDispenser=atmCashDispenser;
      
   }
   

   // perform transaction
   @Override
   public void execute() {
       amount = displayMenuOfAmounts();
       
       if(amount == CANCELED){
           screen.displayMessageLine("Canceling transaction...");
       }else{               
           if(cashDispenser.isSufficientCashAvailable(amount)){
               cashDispenser.dispenseCash(amount);
               getBankDatabase().debit(getAccountNumber(), amount);
           }
           screen.displayMessageLine("Your cash has been dispensed. Please take your cash now");           
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
       LocalDateTime now = LocalDateTime.now();
       super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Mengambil uang di bank sebanyak $" + amount 
       + " " + dtf.format(now));
       struk = new Struk(amount,super.getAccountNumber());
       struk.CetakStruk(WITHDRAWAL,0);
       }
       
       

   } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 1: 
                userChoice = amounts[input]; // save user's choice
               break;
            case 2:
                userChoice = amounts[input]; // save user's choice
               break;// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: 
                userChoice = amounts[input]; // save user's choice
               break;// corresponding amount from amounts array
            case 4:
                userChoice = amounts[input]; // save user's choice
               break;
            case 5:
               userChoice = amounts[input]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
} 
