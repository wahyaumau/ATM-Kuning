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
    private String pil;
    
    public Ticket(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        Screen screen = getScreen();
        screen.displayMessage("\n1. Tiket Pesawat");
        screen.displayMessage("\n2. Tiket Kereta Api");
        pil = getPilihan();
        
        if(pil.matches("1")){
            screen.displayMessage("\n1. Garuda Indonesia");
            screen.displayMessage("\n2. Lion Air");
            pil = getPilihan();
            if(pil.matches("1") || pil.matches("2")){
                int Kd =  getKdPembayaran();
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
                        if (pil.matches("1")){
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
        }else if (pil.matches("2")){
            int Kd =  getKdPembayaran();
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
    
    private String getPilihan(){
        String pilihan;
        screen.displayMessage("\nPilihan : ");
        pilihan = keypad.getString();
        while((pilihan.length()>1) || (!pilihan.matches("[0-9]*"))){
              if ((pilihan.length()>1) || (!pilihan.matches("[0-9]*"))){
                 super.getScreen().displayMessage("Input Tidak Valid...");
                }
                screen.displayMessage("\nPilihan : ");
                pilihan = keypad.getString();
        }
        return pilihan;
    }
    
    private int getKdPembayaran(){
        screen.displayMessage("\nMasukkan Nomor kode pembayaran anda : ");
        String Kd =  keypad.getString();

        while((Kd.length()>13) || (!Kd.matches("[0-9]*"))){
              if ((Kd.length()>13) || (!Kd.matches("[0-9]*"))){
                 super.getScreen().displayMessage("Input Tidak Valid...");
                }
                screen.displayMessage("\nMasukkan Nomor kode pembayaran anda : ");
                Kd =  keypad.getString();
        }
        int Kode = Integer.parseInt(Kd);
        return Kode;
    }
    
}

