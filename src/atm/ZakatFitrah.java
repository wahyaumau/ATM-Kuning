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
public class ZakatFitrah extends Transaction{
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad   
   private final static int CANCELED = 0; // constant for cancel option   
   private final static double hargaPokok = 1.73; // constant for cancel option   
   private int anggotaKeluarga;

    public ZakatFitrah(Keypad keypad, int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase){
        super(userAccountNumber, atmScreen, atmBankDatabase);        
        this.keypad = keypad;        
   }

    @Override
    public void execute() {
        boolean wrong = false;
        boolean start = true;
        screen.displayMessage("Masukkan jumlah anggota keluarga : ");
        
        if (keypad.validate()){
          try { 
            anggotaKeluarga = keypad.getInput();
           if(getBankDatabase().getTotalBalance(getAccountNumber())>hargaPokok*anggotaKeluarga){
            getBankDatabase().debit(getAccountNumber(), hargaPokok*anggotaKeluarga);
//            getBankDatabase().credit(11111, nishab);
            screen.displayMessageLine("Pembayaran zakat fitrah berhasil");
            wrong =false;
            start =false;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
       LocalDateTime now = LocalDateTime.now();
       super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Membayar Zakat fitrah sebesar $" + hargaPokok*anggotaKeluarga 
       + " " + dtf.format(now));
        }else{
            screen.displayMessageLine("Uang tidak cukup");
            start = false;
            wrong = false;
        }
        }catch (Exception e){  
        screen.displayMessage("Wrong data type, please insert integer");
        screen.displayMessage("Please input again,remember input an integer!!!");
        execute();
                }
        }
   
        }
     
        
        }
     

