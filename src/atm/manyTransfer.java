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
public class manyTransfer {
    private int loop;
    private Keypad keypad;
    private Screen screen;
    public manyTransfer(){
        screen=new Screen();
        screen.displayMessage("\nhow many account you want transfer :");
        keypad=new Keypad();
        loop=keypad.getInput();
        
    }
    public int getLoop(){
        return loop;
    }
}
