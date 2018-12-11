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
public class TransferToMany extends Transaction{
    private Keypad keypad;
    private manyTransfer looping;
    private int accountReceiver;

    public TransferToMany(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, 
           Keypad atmKeypad) {
       
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad=atmKeypad;       
   }
    
    @Override
    public void execute() {        
        looping=new manyTransfer();
        int i = looping.getLoop();
        for(int a=0;a<i;a++){
        screen.displayMessageLine("input account for transfer :");
        accountReceiver = promptForTransferReceiver();
        if(super.getBankDatabase().isAccountExist(accountReceiver)){
            screen.displayMessageLine("\ninput amount for transfer :");
            int amounts = keypad.getInput();
            if(getBankDatabase().getTotalBalance(getAccountNumber())>amounts){
                getBankDatabase().debit(getAccountNumber(), amounts);
                getBankDatabase().credit(accountReceiver, amounts);
                screen.displayMessageLine("your transfer succeed");    
            }else screen.displayMessageLine("Insufficient cash");
        }else screen.displayMessageLine("Account doesn't exist");
    }
    }
    
    
    private int promptForTransferReceiver() {
      Screen screen = getScreen();
      screen.displayMessage("\nPlease enter receiver account (or 0 to cancel) : ");
      int input = keypad.getInput();
         return input;
   }
    
}
