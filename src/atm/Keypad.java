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
import java.util.Scanner;

public class Keypad {
   private Scanner input; // reads data from the command line
                         
   public Keypad() {
      input = new Scanner(System.in);    
   } 

//   public int getInput() {
//      return input.nextInt(); // user enters an integer
//   } 
   // input untuk validasi int
   public int getInput() {
       int number;
        do {
            while (!input.hasNextInt()) {
                System.out.println("\nAGAIN,That's not a number!");
                input.next(); // this is important!
            }
            number = input.nextInt();
        } while (number <= 0);
      
      return number; // user enters an integer
   } 
   
   //IRSA
   public String getString(){
       return input.next();
   }
   
   public String getNextLine(){
       return input.nextLine();
   }

    boolean validate() {
        if (input.hasNextInt()){
       return true; 
       }
        return false;
    }
} 
