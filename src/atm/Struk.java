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
    public Struk (double Amount, int userAccountNumber)
    {
        this.amount = Amount;
        this.account = userAccountNumber;
        
    }
        
    public void CetakStruk (int strukFor, int receiver)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        switch(strukFor)
        {
            case WITHDRAWAL:
                System.out.println("        Struk Transaksi              ");
                System.out.println("=====================================");
                System.out.println("Akun: " + account);
                System.out.println("Anda telah menarik uang sebesar: $" + amount);
                System.out.println("pada waktu " + dtf.format(now));
                System.out.println("   Terima kasih telah berlayanan");
                break;
            case DEPOSIT:
                System.out.println("          Struk Transaksi           ");
                System.out.println("====================================");
                System.out.println("Akun: " + account);
                System.out.println("Anda telah menarik uang sebesar: $" + amount);
                System.out.println("pada waktu " + dtf.format(now));
                System.out.println("   Terima kasih telah berlayanan");
                break;
            case TRANSFER:
                System.out.println("        Struk Transaksi              ");
                System.out.println("===========================================");
                System.out.println("Akun: " + account);
                System.out.println("Anda telah mentransfer uang sebesar: $" + amount);
                System.out.println("ke akun: " + receiver);
                System.out.println("pada waktu: " + dtf.format(now));
                System.out.println("Terima kasih telah berlayanan");
                break;
        }
    }
    
        
}