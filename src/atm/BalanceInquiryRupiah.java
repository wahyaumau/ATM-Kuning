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
public class BalanceInquiryRupiah extends Transaction {
   // BalanceInquiry constructor
    private final static double dollarToIDR = 14431.65; // constant for cancel option   
   public BalanceInquiryRupiah(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase) {

      super(userAccountNumber, atmScreen, atmBankDatabase);
   } 

   // performs the transaction
   @Override
   public void execute() {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      

      // get the available balance for the account involved
      double myAvailableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber()) *dollarToIDR ;

      // get the total balance for the account involved
      double myTotalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber())*dollarToIDR;
      
      // display the balance information on the screen
      screen.displayMessageLine("\nBalance Information in Rupiah:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayRupiahAmount(myAvailableBalance);
      screen.displayMessage("\n - Total balance:     ");
      screen.displayRupiahAmount(myTotalBalance);
      screen.displayMessageLine("");
   }
} 
