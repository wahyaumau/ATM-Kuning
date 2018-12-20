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
public class WithdrawalWithCurrency extends Transaction {
   private int amount; // amount to withdraw
   private int display;
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;
    private int nominal;
    private int temp;

   // Withdrawal constructor
   public WithdrawalWithCurrency(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
      
   }
 
   // perform transaction
   @Override
   public void execute() {
       amount = Amount();
     //  System.out.println(amount);
       display = displayMenuOfCurrency();
       if(display == CANCELED)
           super.getScreen().displayMessageLine("Canceling Transaction ...");
       else {
           if(amount % 20!=0 || amount<20){
               super.getScreen().displayMessage("Transaction cannot be done...");
           }
           else if (cashDispenser.isSufficientCashAvailable(amount)){
               if (super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                   cashDispenser.dispenseCash(amount);
                   super.getBankDatabase().debit(super.getAccountNumber(), amount);
                   System.out.println("Your cash has been dispensed. please take your cash now");
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                    LocalDateTime now = LocalDateTime.now();
                    super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Mengambil uang di bank sebanyak $" + amount 
       + " " + dtf.format(now));
                    Struk struk = new Struk(amount, super.getAccountNumber());
                    struk.CetakStruk(1,0);

               } 
                   
            }
           else System.out.print("Money in dispenser not available");
       }

   } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   public int Amount(){ 
       Screen s = getScreen();
       s.displayMessage("\nPlease insert nominal that you want to take out (in dollar) :  ");
       nominal = keypad.getInput();
       return nominal;
   }
   public int displayMenuOfCurrency() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] choice = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nCurrency Menu:");
         screen.displayMessageLine("1 - $5");
         screen.displayMessageLine("2 - $10");
         screen.displayMessageLine("3 - $20");
         screen.displayMessageLine("4 - $50");
         screen.displayMessageLine("5 - $100");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a Currency: ");

         int input = keypad.getInput(); // get user input through keypad
         long cur;
         // determine how to proceed based on the input value
         switch (input) {
            case 1:             
               cur = nominal/5;
               System.out.println(nominal+"$ denomination of "+cur+ "pieces"); 
               break;
            case 2:
               cur = nominal/10;
               if(nominal%10!=0){
                   temp = (int) (nominal-(cur*10));
                   System.out.println(nominal+"$ denomination of :"); 
                   System.out.println(cur+ "pieces of "+10+"$");
                   System.out.println(1+ "pieces of "+temp+"$");
               }
               else{ 
                   System.out.println(nominal+"$ denomination of :");
                   System.out.println(cur+ "pieces of "+10+"$");
               }
                break;
            case 3: 
               cur = nominal/20;
               if(nominal%20!=0){
                   temp = (int) (nominal-(cur*20));
                   System.out.println(nominal+"$ denomination of :"); 
                   System.out.println(cur+ "pieces of "+20+"$");
                   if (temp % 10==0){
                       System.out.println((temp/10)+ "pieces of "+10+"$");
                   }
                   else if (temp % 5==0){
                            System.out.println((temp/5)+ "pieces of "+5+"$");
                        }
               }
               else{ 
                    System.out.println(nominal+"$ denomination of :");
                    System.out.println(cur+ "pieces of "+20+"$");
                }
               
                break;
            case 4:
               cur = nominal/50;
               if(nominal%50!=0){
                   temp = (int) (nominal-(cur*50));
                   System.out.println(nominal+"$ denomination of :"); 
                   System.out.println(cur+ "pieces of "+50+"$");
                   if (temp % 20==0){
                       System.out.println((temp/20)+ "pieces of "+20+"$");
                   }
                   else if (temp % 10==0){
                       System.out.println((temp/10)+ "pieces of "+10+"$");
                   }
                   else if (temp % 5==0){
                       System.out.println((temp/5)+ "pieces of "+5+"$");
                   }
               }
               else{ 
                   System.out.println(nominal+"$ denomination of :");
                   System.out.println(cur+ "pieces of "+50+"$");
               }
                break;
            case 5:
               cur = nominal/100;
               if(nominal%100!=0){
                   temp = (int) (nominal-(cur*100));
                   System.out.println(nominal+"$ denomination of :"); 
                   System.out.println(cur+ "pieces of "+100+"$");
                   if (temp % 50==0){
                       System.out.println((temp/50)+ "pieces of "+50+"$");
                   }
                   else if (temp % 20==0){
                       System.out.println((temp/20)+ "pieces of "+20+"$");
                   }
                   else if (temp % 10==0){
                       System.out.println((temp/10)+ "pieces of "+10+"$");
                   }
                   else if (temp % 5==0){
                       System.out.println((temp/5)+ "pieces of "+5+"$");
                   }
               }
               else{ 
                   System.out.println(nominal+"$ denomination of :");
                   System.out.println(cur+ "pieces of "+100+"$");
               }
                break;
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
         userChoice=input;
      } 

      return userChoice;
   }
} 