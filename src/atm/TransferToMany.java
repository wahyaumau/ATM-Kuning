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
        screen.displayMessageLine("input account for transfer (or 0 to cancel):");
        accountReceiver = promptForTransferReceiver();
        if(super.getBankDatabase().isAccountExist(accountReceiver)){
            screen.displayMessageLine("\ninput amount for transfer :");
            int amounts = keypad.getInput();
            if(getBankDatabase().getTotalBalance(getAccountNumber())>amounts){
                getBankDatabase().debit(getAccountNumber(), amounts);
                getBankDatabase().credit(accountReceiver, amounts);
                screen.displayMessageLine("your transfer succeed");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Mentransfer uang sebesar " + amounts +
                        "ke akun " + accountReceiver
       + " " + dtf.format(now));
                Struk struk = new Struk(amounts, super.getAccountNumber());
                struk.CetakStruk(3, accountReceiver);
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
