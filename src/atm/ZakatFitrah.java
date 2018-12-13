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
        boolean isNumeric = true;
        boolean start = true;
        screen.displayMessage("Masukkan jumlah anggota keluarga : ");
        while(start){ 
        if (keypad.validate()){
           anggotaKeluarga = keypad.getInput();
           if(getBankDatabase().getTotalBalance(getAccountNumber())>hargaPokok*anggotaKeluarga){
            getBankDatabase().debit(getAccountNumber(), hargaPokok*anggotaKeluarga);
//            getBankDatabase().credit(11111, nishab);
            screen.displayMessageLine("Pembayaran zakat fitrah berhasil");
            start =false;
        }else{
            screen.displayMessageLine("Uang tidak cukup");
            start = false;
        }
        }else {
        screen.displayMessage("Wrong data type, please insert integer");
        
        }
     }
        
        }
    }    

