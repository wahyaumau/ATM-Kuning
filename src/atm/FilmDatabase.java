/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.Calendar;
import java.util.HashSet;

/**
 *
 * @author protege
 */
public class FilmDatabase {
    private HashSet<Film> listFilm;
    
    public FilmDatabase(){
        listFilm = new HashSet<>();    
        listFilm.add(new Film("Aquaman", 1, 1, 2019));
        listFilm.add(new Film("Avenger", 2, 1, 2019));
        listFilm.add(new Film("Ralph", 3, 1, 2019));
        listFilm.add(new Film("Expire", 25, 6, 2018));
        listFilm.add(new Film("Contest", 21, 8, 2018));    
    }
    
    public Film getFilm(String title) {
      for(Film a : listFilm){
          if(a.getTitle().equalsIgnoreCase(title)) return a;
      }
      return null; // if no matching account was found, return null
   }
    
    public Calendar getEndDay(String title){
        return getFilm(title).getEndDay();        
    }
   
   public void getFilm_all() {
      Screen screen = new Screen();
      for (Film a : listFilm){          
        screen.displayMessage("\nTitle = " + a.getTitle()+ " End day = " + a.getDate()+"-"+a.getMonth()+"-"+a.getYear());
      }
   }
    
    
}
