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
public class Pulse extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private String Number; // reference to Phone number
   private String Operator; // reference to Operator
   private int Nominal; // reference to Pulse nominal
   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Pulse(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
  

   }

    @Override
    public void execute() {
        Number = promptForPhoneNumber();
        Operator = promptForOperator();
        Nominal = promptForNominal();
           if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= Nominal){
                   super.getBankDatabase().debit(super.getAccountNumber(), Nominal);
                   super.getScreen().displayMessage("\nTransaction to number : ");
                   super.getScreen().displayMessage(Number);
                   super.getScreen().displayMessage("\nThe operator is : ");
                   super.getScreen().displayMessage(Operator);
                   super.getScreen().displayMessage("\nNominal : ");
                   System.out.print(Nominal);
                   super.getScreen().displayMessage("\nTotal Payment :");
                   super.getScreen().displayDollarAmount(Nominal);
                   super.getScreen().displayMessage(" is Succesfull...");
           }
           else super.getScreen().displayMessage("Sorry. Your amount is not enough...");
        
        
    }   
    private String promptForPhoneNumber(){
       Screen s = getScreen();
       s.displayMessage("\nPlease insert your phone number (10-13Character) : ");
       String input = keypad.getString();
       while((input.length()<10||input.length()>13) || (!input.matches("[0-9]*"))){
              if ((input.length()<10||input.length()>13) || (!input.matches("[0-9]*"))){
                 super.getScreen().displayMessage("Incorect Number...");
                }      
            s.displayMessage("\nPlease insert your phone number (10-13Character) : ");
            input = keypad.getString();

       }
       return input;
    }
    
        private String promptForOperator(){
       Screen s = getScreen();
       s.displayMessage("\nPlease insert the operator : ");
       String input = keypad.getString();
       return input;
   }
    
    private int promptForNominal(){
       Screen s = getScreen();
       s.displayMessage("\nPlease insert the pulse nominal : ");
       int input = keypad.getInput();
       return input/100;
    }    
}
