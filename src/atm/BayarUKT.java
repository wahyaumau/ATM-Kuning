/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author protege
 */
public class BayarUKT extends Transaction {
    private int UKT;
    private int AccountReceiver; 
     private Keypad keypad;
   public BayarUKT (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad){       
       super(userAccountNumber,atmScreen, atmBankDatabase);
       keypad= atmKeypad;
     
    }
    @Override  
   public void execute(){                     
      AccountReceiver=34567;
   if(super.getBankDatabase().isAccountExist(AccountReceiver)){
       UKT =  displayMenuOfAmounts();
       super.getBankDatabase().debit(getAccountNumber(), UKT);
       super.getBankDatabase().credit(AccountReceiver, UKT);
   }
   }
   
    private int displayMenuOfAmounts(){  
     int userChoice = 0;   // local variable to store return value  
     Screen screen = getScreen();  // get screen references  
     // array of amounts to correspond to menu numbers  
     int[] amounts = {0, 200, 250, 300, 350, 400, 450, 500};  
     // loop while no valid choice has been made  
     while(userChoice == 0){  
       // display the withdrawal menu  
       screen.displayMessageLine("\nWithdrawal Menu : ");  
       screen.displayMessageLine("1 - UKT 1 ($200)");  
       screen.displayMessageLine("2 - UKT 2 ($250)");  
       screen.displayMessageLine("3 - UKT 3 ($300)");  
       screen.displayMessageLine("4 - UKT 4 ($350)");  
       screen.displayMessageLine("5 - UKT 5 ($400)");  
       screen.displayMessageLine("6 - UKT 6 ($450)");  
       screen.displayMessageLine("7 - UKT 7 ($500)");  
       screen.displayMessage("\nChoose a withdrawal amount : ");  
       int input = keypad.getInput();   // get user input through keypad  
       // determine how to proceed based on the input value  
       switch(input){  
         // if the user choose a withdrawal amount  
         // i.e choose option 1, 2, 3, 4 or 5  
         // return the corresponding amount from amounts's array  
         case 1 :  
         case 2 :  
         case 3 :  
         case 4 :  
         case 5 : 
         case 6 :
         case 7 :
           userChoice = amounts[input];    // save user's choice
            screen.displayMessageLine("\nTransaction Succes");
            screen.displayMessageLine("\nPembayar UKT "+input+" Total Biaya : $"+userChoice+"\n");
           break;             
         default :  
           // the user did not enter value between 1-6  
           screen.displayMessageLine("\nInvalid selection.");  
           screen.displayMessageLine("Try again.");  
       }  // end switch  
     }  // end while  
     return userChoice;   // return withdrawal amount or CANCELED  
   }  
}