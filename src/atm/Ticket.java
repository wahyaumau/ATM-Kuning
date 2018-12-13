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
public class Ticket extends Transaction {
    private final Keypad keypad;
    public Ticket(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }


    @Override
    public void execute() {
        Screen screen = getScreen();
        screen.displayMessage("\n1. Tiket Pesawat");
        screen.displayMessage("\n2. Tiket Kereta Api");
        screen.displayMessage("\nPilihan : ");
        int pilihan = keypad.getInput(); 
        if(pilihan == 1){
            screen.displayMessage("\n1. Garuda Indonesia");
            screen.displayMessage("\n2. Lion Air");
            screen.displayMessage("\nPilihan : ");
            int pil = keypad.getInput(); 
            if(pil == 1 || pil == 2){
                screen.displayMessage("\nMasukkan Nomor kode pemesanan anda : ");
                int Kd =  keypad.getInput();
                if(getBankDatabase().validasiKdPesawat(Kd)){
                    double amount = super.getBankDatabase().getHargaPembayaranPesawat(Kd);
                    int booking = super.getBankDatabase().getKodeBookingPesawat(Kd);
                    String NamaP = super.getBankDatabase().getNamaP(Kd);
                    int tempat = super.getBankDatabase().getJumlahSeatPesawat(Kd);
                    String Rute = super.getBankDatabase().getRutePesawat(Kd);
                    String M = super.getBankDatabase().getMaskapaiPesawat(Kd);
                    if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                        LocalDateTime now = LocalDateTime.now();
                        super.getBankDatabase().debit(getAccountNumber(),amount); // Balance akun saya berkurang
                        if (pil==1){
                            super.getBankDatabase().credit(34567,amount);
                            super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Membayar tiket pesawat Garuda Indonesia seharga $" + amount 
       + " " + dtf.format(now));
                        } //Asumsi : Akun penjual Tiket Garuda Indonesia
                        else{
                            super.getBankDatabase().credit(45678,amount);
                            super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Membayar tiket pesawat Lion Air seharga $" + amount 
       + " " + dtf.format(now));
                        } //Asumsi : Akun penjual Tiket Lion Air
                        screen.displayMessageLine("\nKode Pembayaran : " + Kd);
                        screen.displayMessageLine("Kode Booking : " + booking);
                        screen.displayMessageLine("Nama : " + NamaP);
                        screen.displayMessageLine("Jumlah Tempat duduk : " + tempat);
                        screen.displayMessageLine("Jumlah Bayar : $" + amount);
                        screen.displayMessageLine("Rute : " + Rute);
                        screen.displayMessageLine("Maskapai : " + M);
                        screen.displayMessageLine("\n ~~ berhasil transfer sebayak $" + amount);
                    }else screen.displayMessageLine("Balance tidak cukup");      
                }else screen.displayMessageLine("Kode tiket pesawat tidak tidak valid");
            }else screen.displayMessageLine("Nomor tidak tidak valid");
        }else if (pilihan == 2){
            screen.displayMessage("\nMasukkan Nomor kode pemesanan anda : ");
            int Kd =  keypad.getInput();
            if(getBankDatabase().validasiKdKereta(Kd)){
                double amount = super.getBankDatabase().getHargaPembayaran(Kd);
                int booking = super.getBankDatabase().getKodeBooking(Kd);
                String Nama = super.getBankDatabase().getNama(Kd);
                int tempat = super.getBankDatabase().getJumlahSeat(Kd);
                String Rute = super.getBankDatabase().getRute(Kd);
                if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber()) >= amount){                   
                   super.getBankDatabase().debit(getAccountNumber(),amount); // Balance akun saya berkurang
                   super.getBankDatabase().credit(23456,amount); //Asumsi : Akun penjual Tiket kereta == 23456
                   screen.displayMessageLine("\nKode Pembayaran : " + Kd);
                   screen.displayMessageLine("Kode Booking : " + booking);
                   screen.displayMessageLine("\nNama : " + Nama);
                   screen.displayMessageLine("Jumlah Tempat duduk : " + tempat);
                   screen.displayMessageLine("Jumlah Bayar : $" + amount);
                   screen.displayMessageLine("Rute : " + Rute);
                   screen.displayMessageLine("\n ~~ berhasil transfer sebayak $" + amount);
               }else screen.displayMessageLine("Balance tidak cukup");      
            }else screen.displayMessageLine("Kode Pembayaran anda tidak tidak valid");
        }else screen.displayMessage("\nPilihan Anda tidak valid");
          
    }
    
}

