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
public class VATransaction extends Transaction{
   private Keypad keypad; // reference to keypad   
   private final static int CANCELED = 0; // constant for cancel option    
   private String code;      

    public VATransaction(Keypad keypad, int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        this.keypad=new Keypad();
    }
        
        

    @Override
    public void execute() {
        screen.displayMessage("Input code : ");
        code = keypad.getString();
        if(getBankDatabase().getAccount(getAccountNumber()).getVA(code)!=null){            
            if(getBankDatabase().getTotalBalance(getAccountNumber())>
                    getBankDatabase().getAccount(getAccountNumber()).getVA(code).getBill()){
                getBankDatabase().debit(getAccountNumber(), getBankDatabase().
                    getAccount(getAccountNumber()).getVA(code).getBill());
                getBankDatabase().credit(getBankDatabase().getAccount(getAccountNumber()).getVA(code).getAccountReceiver(), 
                    getBankDatabase().getAccount(getAccountNumber()).getVA(code).getBill());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Membayar transaksi virtual akun sebesar $" + getBankDatabase().getAccount(getAccountNumber()).getVA(code).getBill()
       + " " + dtf.format(now));
                Struk struk = new Struk(getBankDatabase().getAccount(getAccountNumber()).getVA(code).getBill(),super.getAccountNumber());
                struk.CetakStruk(8,0);
            }else screen.displayMessageLine("Insufficient fund");
        }else screen.displayMessageLine("You don't have that bill");
    }
    
}
