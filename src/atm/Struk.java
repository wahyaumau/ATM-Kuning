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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Struk {
    private double amount;
    private int account;
    private final int WITHDRAWAL = 1;
    private final int TRANSFER = 3;
    private final int DEPOSIT = 2;
    private final int BAYARUKT = 4;
    private final int PULSE = 5;
    private final int ETOKEN = 6;
    private final int ZFITRAH = 7;
    private final int VA = 8;
    public Struk (double Amount, int userAccountNumber)
    {
        this.amount = Amount;
        this.account = userAccountNumber;
        
    }
    public Struk (int userAccountNumber)
    {
        this.account = userAccountNumber;
    }
    private void bar()
    {
        System.out.println("        Struk Transaksi              ");
        System.out.println("=====================================");
    }
    
    private void akun()
    {
        System.out.println("Akun: " + account);
    }
    
    private void thx()
    {
        System.out.println("Terima kasih telah berlayanan");
    }
    
        
    public void CetakStruk (int strukFor, int receiver)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        switch(strukFor)
        {
            case WITHDRAWAL:
                bar();
                System.out.println("Akun: " + account);
                System.out.println("Anda telah menarik uang sebesar: $" + amount);
                System.out.println("pada waktu " + dtf.format(now));
                System.out.println("Terima kasih telah berlayanan");
                break;
            case DEPOSIT:
                bar();
                System.out.println("Akun: " + account);
                System.out.println("Anda telah menyimpan uang sebesar: $" + amount);
                System.out.println("pada waktu " + dtf.format(now));
                System.out.println("Terima kasih telah berlayanan");
                break;
            case TRANSFER:
                bar();
                System.out.println("Akun: " + account);
                System.out.println("Anda telah mentransfer uang sebesar: $" + amount);
                System.out.println("ke akun: " + receiver);
                System.out.println("pada waktu: " + dtf.format(now));
                System.out.println("Terima kasih telah berlayanan");
                break;
            case BAYARUKT:
                bar();
                System.out.println("Akun: " + account);
                System.out.println("Anda telah membayar UKT sebesar: $" + amount);
                System.out.println("Ke akun: " + receiver);
                System.out.println("pada waktu: " + dtf.format(now));
                System.out.println("Terima kasih telah berlayanan");
                break;
            case PULSE:
                bar();
                System.out.println("Akun: " + account);
                System.out.println("Anda telah membeli pulsa dengan nominal: " + amount);
                System.out.println("pada waktu: " + dtf.format(now));
                System.out.println("Terima kasih telah berlayanan");
                break;
            case ETOKEN:
                bar();
                akun();
                System.out.println("Anda telah membeli token seharga: " + amount);
                System.out.println("pada waktu: " + dtf.format(now));
                thx();
                break;
            case ZFITRAH:
                bar();
                akun();
                System.out.println("Anda Telah membayar Zakat Fitrah");
                thx();
                break;
            case VA:
                bar();
                break;
        }
    }
    
    public void PrintBioskop(int jumlah , String title)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        bar();
        akun();
        System.out.println("Anda telah membeli tiket " + title + "seharga: " + amount);
        System.out.println("Dengan jumlah: " + jumlah + " buah tiket");
        System.out.println("pada waktu: " + dtf.format(now));
        thx();
    }
    
    public void PrintZMal()
            
    {
        bar();
        akun();
        System.out.println("Anda telah berhasil membayar zakat mal");
        thx();
    }
        
}