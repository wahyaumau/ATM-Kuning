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
public class Etoken extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private String CustomerID; // reference to Customer ID
   private int Token; // reference to Nominal Token
   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Etoken(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
  

   }

    @Override
    public void execute() {
       CustomerID = promptForCustomerID();
       Token =promptForToken();
       if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= Token){
                   super.getBankDatabase().debit(super.getAccountNumber(), Token);
                   super.getScreen().displayMessage("\nTransaction to Customer ID : ");
                   super.getScreen().displayMessage(CustomerID);
                   super.getScreen().displayMessage("\nToken : ");
                   System.out.print(Token);
                   super.getScreen().displayMessage("\nTotal Payment :");
                   super.getScreen().displayDollarAmount(Token);
                   super.getScreen().displayMessage(" is Succesfull...");
           }
           else super.getScreen().displayMessage("Sorry. Your amount is not enough...");
    }
    private String promptForCustomerID(){
       Screen s = getScreen();
       s.displayMessage("\nPlease insert your Customer ID : ");
       String input = keypad.getString();
       while(input.length()!=16){
              if (input.length()!=16){
                 super.getScreen().displayMessage("Incorect ID...");
                }
            s.displayMessage("\nPlease insert your phone number : ");
            input = keypad.getString();

       }
       return input;
   }
       private int promptForToken(){
       Screen s = getScreen();
       s.displayMessage("\nPlease insert nominal electricity token : ");
       int input = keypad.getInput();
       return input/100;
    }
}
