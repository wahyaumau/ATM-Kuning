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
public class Admin {
    private CashDispenser cashDispenser;
    private Keypad keypad; // reference to keypad
    private Screen screen;
    private BankDatabase bankDatabase;

    public Admin(CashDispenser cashDispenser, Keypad keypad, Screen screen, BankDatabase bankDatabase) {
        this.cashDispenser = cashDispenser;
        this.keypad = keypad;
        this.screen = screen;
        this.bankDatabase = bankDatabase;
        performTransactions();
    }
   
    
    private void performTransactions() {
      
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while (!userExited) {
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenu();

         // decide how to proceed based on user's menu selection
         switch (mainMenuSelection) {
            // user chose to perform one of three transaction types
            case 1:    
                   money_in_atm();   
//                   untuk fitur ini class yang berubah hanya 2 1 atm java, dan cashdispenser java.
//                   perubahan hanya penambahan method get dan set count
               break; 
            case 2:    
                   money_to_atm();
//                   untuk fitur ini class yang berubah hanya 2 1 atm java, dan cashdispenser java.
//                   perubahan hanya penambahan method get dan set count
               break; 
            case 3:    
                   bankDatabase.getAccount_all();
//                  hanya pemanggilan method pada databse yang sudah di tambahkan
               break;
            case 4: // user chose to terminate session
               screen.displayMessageLine("\nExiting the system...");
//               bankDatabase.credit_validate(currentAccountNumber,depositSlot.getMoney()); // 
//               depositSlot.reset();
               userExited = true; // this ATM session should end
               break;
            default: // 
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      } 
   } 
    

    
    
    private int displayMainMenu() {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View available cash dispenser");
      screen.displayMessageLine("2 - Add amount to cash dispenser");
      screen.displayMessageLine("3 - See list of account");
      screen.displayMessageLine("4 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput(); // return user's selection
   } 
    
    private void money_in_atm() {
      double money = (double)cashDispenser.getCount()*20;        
      screen.displayMessageLine("\nAvailable cash dispenser " );
      screen.displayDollarAmount(money);
      screen.displayMessageLine(" with denomination of $20 with amount of = " + cashDispenser.getCount());
   } 
     private void money_to_atm() {       
      screen.displayMessageLine("\n*************************************NOTE*************************************");
      screen.displayMessageLine("\n1. Uang yang di masukkan harus di keluarkan semuanya");
      screen.displayMessageLine("\n2. Total lembaran uang dibawah, adalah bnyak jumlah $20 yag anda masukkan");
      screen.displayMessageLine("\n3. Berhati hati lah dalam memasukkan uang");
      screen.displayMessageLine("\n******************************************************************************");
      screen.displayMessageLine("\nTotal lembaran uang $20 yang dimasukkan ");
      int money = keypad.getInput();
      cashDispenser.setCount(money);
      screen.displayMessageLine("\nSukses");
   } 
    
    
    
}