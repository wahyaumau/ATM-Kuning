/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.Calendar;

/**
 *
 * @author protege
 */
public class Film {
    private String title;
    private Calendar endDay;    
    
    public Film(String title, int date, int month, int year){
        this.title=title;
        endDay = Calendar.getInstance();
        endDay.set(year, month, date);
    }

    public String getTitle() {
        return title;
    }
    
    public int getDate(){
        return endDay.get(Calendar.DATE);
    }
    
    public int getMonth(){
        return endDay.get(Calendar.MONTH);
    }
    
    public int getYear(){
        return endDay.get(Calendar.YEAR);
    }

    public Calendar getEndDay() {
        return endDay;
    }
    
    
    
}
