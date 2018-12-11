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
public class Transfer extends Transaction{
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private int accountReceiver;
   private final static int CANCELED = 0; // constant for cancel option
   private final int TRANSFER = 3;
    private Struk struk;
    
   public Transfer(Keypad keypad, int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase){
        super(userAccountNumber, atmScreen, atmBankDatabase);        
        this.keypad = keypad;        
   }
    @Override
    public void execute() {
       accountReceiver = promptForTransferReceiver();
       if(accountReceiver==0){
           screen.displayMessageLine("Canceling transaction...");
       }
       else if(getAccountNumber()==accountReceiver){
           screen.displayMessageLine("You cannot transfer to yourself");
       }else if (super.getBankDatabase().isAccountExist(accountReceiver)==false){
               screen.displayMessageLine("Account receiver not exist");
       }else{
           screen.displayMessageLine("Input amount of transfer : ");
           amount = keypad.getInput();
           if(super.getBankDatabase().getAvailableBalance(getAccountNumber())<amount){
               screen.displayMessageLine("Insufficient fund");               
           }else{
               super.getBankDatabase().debit(getAccountNumber(), amount);
               super.getBankDatabase().credit(accountReceiver, amount);           
               super.getBankDatabase().creditForTransfer(accountReceiver, amount);
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Mentransfer uang di bank sebanyak $" + amount + " ke akun " + accountReceiver + " " + 
                dtf.format(now));
        struk = new Struk(amount,super.getAccountNumber());
        struk.CetakStruk(TRANSFER,accountReceiver);
           }                          
       }              
    }    
    private int promptForTransferReceiver() {
      Screen screen = getScreen();
      screen.displayMessage("\nPlease enter receiver account (or 0 to cancel) : ");
      int input = keypad.getInput();
         return input;

   }
}