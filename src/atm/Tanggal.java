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
import java.util.Date;  
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.lang.String;   
public class Tanggal{  
            private DateFormat  dateForma;
            private Date date;
            private DateFormat timeFormat;
    public Tanggal(){
         dateForma = new SimpleDateFormat("yyyy/MM/dd");  
        timeFormat = new SimpleDateFormat("HH:mm:ss");  
         date = new Date();
    }
        public String getTanggal() {  
        return dateForma.format(date);  
    }  
     
    public String getWaktu() {  
        return timeFormat.format(date);  
    } 
       
}