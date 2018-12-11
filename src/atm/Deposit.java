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
public class Deposit extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option
   private Struk struk;
   private int DEPOSIT = 2;

   // Deposit constructor

    public Deposit(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad=atmKeypad;
      depositSlot=atmDepositSlot;

   }
   

   // perform transaction
   @Override
   public void execute() {
       amount = promptForDepositAmount();
       if(amount==0){
           screen.displayMessageLine("Canceling transaction...");
       }else{
           if(depositSlot.isEnvelopeReceived()){               
               screen.displayMessage("Please insert a deposit envelope containing ");
               screen.displayDollarAmount(amount);
               screen.displayMessageLine("");
               super.getBankDatabase().credit(getAccountNumber(), amount);
               screen.displayMessageLine("Your envelope has been received.");
               screen.displayMessageLine("NOTE: The money just deposited will not be available until we verify the amount of any enclosed cash and your checks clear.");               
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
       LocalDateTime now = LocalDateTime.now();
       super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Menyimpan uang di bank sebanyak $" + amount 
       + " " + dtf.format(now));
       struk = new Struk(amount,super.getAccountNumber());
       struk.CetakStruk(DEPOSIT,0);
//               
           }          
       }
   }

   // prompt user to enter a deposit amount in cents 
   private double promptForDepositAmount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
      if (input == CANCELED) {
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
} 

