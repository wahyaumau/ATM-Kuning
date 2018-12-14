/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author protege
 */
public class PembayaranBioskop extends Transaction{    
   private Keypad keypad; // reference to keypad   
   private final static int CANCELED = 0; // constant for cancel option 
   private FilmDatabase filmDatabase;
   private String title;   
   private double ticketCost;
   Calendar waktuMenonton;

    public PembayaranBioskop(Keypad keypad, int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, FilmDatabase
            filmDatabase){
        super(userAccountNumber, atmScreen, atmBankDatabase);        
        this.keypad = keypad;        
        this.filmDatabase=filmDatabase;   
        waktuMenonton=Calendar.getInstance();
   }

    public FilmDatabase getFilmDatabase() {
        return filmDatabase;
    }
    
    

    @Override
    public void execute(){
        getFilmDatabase().getFilm_all();
        screen.displayMessageLine("\nInput judul Film : ");
        title = keypad.getString();        
        if(getFilmDatabase().getFilm(title)!=null){
            screen.displayMessage("Tanggal Menonton : ");
            int date = keypad.getInput();
            screen.displayMessage("Bulan Menonton : ");
            int month = keypad.getInput();
            month-=1;
            System.out.println(month);
            screen.displayMessage("Tahun Menonton : ");
            int year = keypad.getInput();
            waktuMenonton.set(year, month, date);            
            System.out.println(waktuMenonton.getActualMaximum(Calendar.DAY_OF_MONTH));
            if(date>waktuMenonton.getActualMaximum(Calendar.DAY_OF_MONTH) || month>12){
               screen.displayMessageLine("Tanggal tidak valid");
            }else{
                if(getFilmDatabase().getEndDay(title).after(waktuMenonton)){
                screen.displayMessageLine("Berapa orang yang akan menonton :");
                int jumlah =keypad.getInput();
                switch(waktuMenonton.get(Calendar.DAY_OF_WEEK)){
                    case Calendar.SUNDAY : ticketCost=3.47; break;
                    case Calendar.SATURDAY : ticketCost=3.12; break;
                    case Calendar.FRIDAY : ticketCost=2.77; break;
                    default: ticketCost=2.08; break;
                }                                
                getBankDatabase().debit(getAccountNumber(), ticketCost*jumlah);
                screen.displayDollarAmount(ticketCost);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                super.getBankDatabase().tulisHistory(super.getAccountNumber(),"Membeli tiket " + title + "berjumlah" + jumlah + " dengan harga " + ticketCost * jumlah
       + " " + dtf.format(now));
                Struk struk = new Struk (jumlah * ticketCost, super.getAccountNumber());
                struk.PrintBioskop(jumlah, title);
            }else screen.displayMessageLine("Film sudah expire");
            }                        
        }else screen.displayMessageLine("Film tidak ada");
        
    }    
    
    
}
