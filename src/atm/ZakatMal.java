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
public class ZakatMal extends Transaction{
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad   
   private final static int CANCELED = 0; // constant for cancel option   
   private final static double nishab = 316.4; // constant for cancel option   

    public ZakatMal(Keypad keypad, int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase){
        super(userAccountNumber, atmScreen, atmBankDatabase);        
        this.keypad = keypad;        
   }

    @Override
    public void execute() {
        if(getBankDatabase().getTotalBalance(getAccountNumber())>nishab){
            getBankDatabase().debit(getAccountNumber(), nishab);
//            getBankDatabase().credit(11111, nishab);
            screen.displayMessageLine("Pembayaran zakat mal berhasil");            
        }else{
            screen.displayMessageLine("Harta anda belum mencapai nishab");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
       LocalDateTime now = LocalDateTime.now();
       super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Membayar Zakat Mal" 
       + " " + dtf.format(now));
        
    }    
}
