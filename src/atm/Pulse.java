/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;
import java.util.Scanner;
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
        String type = null;
        Number = promptForPhoneNumber();
        while (!"08".equals(Number.substring(0,2))){
            if (!"08".equals(Number.substring(0,2))){
                super.getScreen().displayMessage("Not Available Number ...");
            }
    //        Operator = promptForOperator();
            Number = promptForPhoneNumber();
        }
        while (("084".equals(Number.substring(0,3)))||("086".equals(Number.substring(0,3)))||("080".equals(Number.substring(0,3)))){
//            if (("084".equals(Number.substring(0,3)))||("086".equals(Number.substring(0,3)))||("080".equals(Number.substring(0,3)))){
                super.getScreen().displayMessage("Not Available Number ...");
//            }
            Number = promptForPhoneNumber();
        }
        switch (Number.substring(0,3)){
                    case "081" :
                            type = "Telkomsel";
                            break;
                    case "082" :
                            type = "Telkomsel";
                            break;
                    case "083" :
                            type = "Axis";
                            break;
                    case "085" :
                            type = "Telkomsel";
                            break;
                    case "087" :
                            type = "XL";
                            break;
                    case "088" :
                            type = "Smartfren";
                            break;
                    case "089" :
                            type = "Three";
                            break;
                    default : type = "Not available";
                }
        Nominal = promptForNominal();
           if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= Nominal){
                   super.getBankDatabase().debit(super.getAccountNumber(), Nominal);
                   super.getScreen().displayMessage("\nTransaction to number : ");
                   super.getScreen().displayMessage(Number);
                   super.getScreen().displayMessage("\nThe operator is : ");
                   super.getScreen().displayMessage(type);
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
       Scanner scanner = new Scanner(System.in);
       s.displayMessage("\nPlease insert your phone number : \n");
       String input = scanner.nextLine();
       while((input.length()<10||input.length()>13) || (!input.matches("[0-9]*"))){
              if ((input.length()<10||input.length()>13) || (!input.matches("[0-9]*"))){
                 super.getScreen().displayMessage("Incorect Number...");
                }      
            s.displayMessage("\nPlease insert your phone number : \n");
            input = scanner.nextLine();
       }
       
       return input;
    }
    
//       private String promptForOperator(){
//       Screen s = getScreen();
//       s.displayMessage("\nPlease insert the operator : ");
//       String input = keypad.getString();
//       return input;
//   }
    
    private int promptForNominal(){
       Screen s = getScreen();
       s.displayMessage("\nPlease insert the pulse nominal : ");
       int input = keypad.getInput();
       return input/100;
    }    
}
