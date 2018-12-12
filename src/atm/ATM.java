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
public class ATM {
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
   private DepositSlot depositSlot;
   private BankDatabase bankDatabase; // account information database
   private FilmDatabase filmDatabase; // account information database
   private Admin admin; // account information database
   private Tanggal tanggal; 

   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int DEPOSIT = 3;
   private static final int TRANSFER = 4;
   private static final int BAYARUKT = 5;
   private static final int HISTORY = 6;
   private static final int CHANGE = 7;
   private static final int PULSE = 8;
   private static final int ETOKEN = 9;   
   private static final int BIOSKOP = 10;
   private static final int WITHDRAWALWITHCURRENCY = 11;
   private static final int ZAKATMAL = 12;   
   private static final int TICKET = 13;
   private static final int TRANSFERTOMANY = 14;
   private static final int ZAKATFITRAH = 15;
   private static final int BALANCE_INQUIRY_RUPIAH = 16;
   private static final int VIRTUALACCOUNT = 17;
   
   
   private static final int EXIT = 99;

   // no-argument ATM constructor initializes instance variables
   public ATM() {
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 0; // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      bankDatabase = new BankDatabase(); // create acct info database
      filmDatabase = new FilmDatabase();
      depositSlot= new DepositSlot();
      tanggal=new Tanggal();
   }

   // start ATM 
   public void run(){        
        while(true){
            while(!userAuthenticated){
                screen.displayMessageLine("\nWelcome!");
                screen.displayMessage("\nDate :");
            screen.displayMessage(tanggal.getTanggal());
            screen.displayMessage("\nTime :");
            screen.displayMessage(tanggal.getWaktu());
                authenticateUser();                    
            }                         
           if(bankDatabase.getAccountStatus(currentAccountNumber)==true){
            performTransactions();                  
           }
           userAuthenticated = false;   
               currentAccountNumber = 0;  
            screen.displayMessageLine("\nThank You!\nGoodbye!");                                    
        }
    }
   

   // attempts to authenticate user against database
   private void authenticateUser(){
        screen.displayMessage("\nPlease enter your account number (or 0 to recover account): ");
        int accountNumber = keypad.getInput();
        if(bankDatabase.isAccountExist(accountNumber)){
            int iBlok=0;
            while(iBlok<3){
                screen.displayMessage("\nEnter your PIN : ");                
                int pin = keypad.getInput();
                userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);        
                if(userAuthenticated) break;
                screen.displayMessageLine("Wrong PIN, you have "+(2-iBlok)+" chances left, otherwise we will suspend your account");
                iBlok++;
                
                
            }
        if(userAuthenticated){
            if(accountNumber == 9999){ // jika admin
                screen.displayMessageLine("Welcome, admin");
                    admin = new Admin(cashDispenser,keypad,screen,bankDatabase);
                    userAuthenticated= false;
                }else{
                    currentAccountNumber = accountNumber; // save user's account #
                }            
        }
        else{
            int confirm;
            screen.displayMessageLine("Your account has been suspended, please contact us to unsuspend your account");
          bankDatabase.blockAccount(accountNumber);
          screen.displayMessageLine("Do you want to recover your Account? It cost you 10% of your money");
          screen.displayMessageLine("1. Yes");
          screen.displayMessageLine("2. No");
         confirm = keypad.getInput();
         if( confirm == 1){
          bankDatabase.RecoverAccount(accountNumber);
         }
        }
        }else if (accountNumber == 0){
               screen.displayMessage("\nPlease enter account number you want to recover : ");
               accountNumber = keypad.getInput();
             if(bankDatabase.isAccountExist(accountNumber)){
                 int confirm;
                 screen.displayMessageLine("Your account has been suspended, please contact us to unsuspend your account");
                 bankDatabase.blockAccount(accountNumber);
                 screen.displayMessageLine("Do you want to recover your Account? It cost you 10% of your money");
                 screen.displayMessageLine("1. Yes");
                 screen.displayMessageLine("2. No");
                 confirm = keypad.getInput();
                 if( confirm == 1){
                 bankDatabase.RecoverAccount(accountNumber);
         }
             }
        }
   }

   // display the main menu and perform transactions
   private void performTransactions() {
      // local variable to store transaction currently being processed
      Transaction currentTransaction = null;
      
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while (!userExited) {
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenu();

         // decide how to proceed based on user's menu selection
         switch (mainMenuSelection) {
            // user chose to perform one of three transaction types
            case BALANCE_INQUIRY:         
            case WITHDRAWAL:                                
            case DEPOSIT:                               
            case TRANSFER:                
            case BAYARUKT:                
            case PULSE :            
            case WITHDRAWALWITHCURRENCY :
            case ETOKEN:
            case BIOSKOP:
            case ZAKATMAL:
            case TICKET:
            case TRANSFERTOMANY:
            case ZAKATFITRAH:
            case BALANCE_INQUIRY_RUPIAH:
            case VIRTUALACCOUNT:
                currentTransaction = createTransaction(mainMenuSelection);
                currentTransaction.execute(); break;
            case HISTORY:
                bankDatabase.tampilHistory(currentAccountNumber);
                break;
            case CHANGE:
                System.out.println("PIN baru: ");
                int x = keypad.getInput();
                while (x <= 10000 || x > 99999)
                {
                    System.out.println("PIN tak boleh lebih atau kurang dari 5 digit!");
                    System.out.println("Input kembali: ");
                    x = keypad.getInput();
                }
                bankDatabase.getAccount(currentAccountNumber).setPin(x);
                System.out.println("Penggantian PIN berhasil!");
                break;            
            case EXIT: // user chose to terminate session
               screen.displayMessageLine("\nExiting the system...");
               userExited = true; // this ATM session should end
               break;
            default: // 
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      } 
   } 

   // display the main menu and return an input selection
   private int displayMainMenu() {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View my balance");
      screen.displayMessageLine("2 - Withdraw cash");
      screen.displayMessageLine("3 - Deposit funds");
      screen.displayMessageLine("4 - Transfer");
      screen.displayMessageLine("5 - Bayar UKT");
      screen.displayMessageLine("6 - Riwayat Transaksi");
      screen.displayMessageLine("7 - Change PIN");
      screen.displayMessageLine("8 - Pulse");
      screen.displayMessageLine("9 - Electricity Token");
      screen.displayMessageLine("10 - Bioskop");
      screen.displayMessageLine("11 - Withdraw with currency");
      screen.displayMessageLine("12 - Zakat Mal");
      screen.displayMessageLine("13 - Ticket");
      screen.displayMessageLine("14 - Transfer To Many Account");
      screen.displayMessageLine("15 - Zakat Fitrah");
      screen.displayMessageLine("16 - View balance in rupiah");
      screen.displayMessageLine("17 - Virtual account transaction");
      screen.displayMessageLine("99 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput(); // return user's selection
   } 
         
   private Transaction createTransaction(int type) {
      Transaction temp = null; 
          
      switch (type) {
         case BALANCE_INQUIRY: 
            temp = new BalanceInquiry(
               currentAccountNumber, screen, bankDatabase);
            break;
            
         case WITHDRAWAL:
             temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
             break;
             
         case DEPOSIT:
             temp= new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
             break;
             
         case TRANSFER:
             temp= new Transfer(keypad, currentAccountNumber, screen, bankDatabase);
             break;
         case BAYARUKT:
             temp = new BayarUKT(currentAccountNumber, screen, bankDatabase, keypad);
                break;
         case PULSE :
             temp = new Pulse(currentAccountNumber, screen, bankDatabase, keypad);
             break;
         case ETOKEN :
             temp = new Etoken(currentAccountNumber, screen, bankDatabase, keypad);         
             break;
         case WITHDRAWALWITHCURRENCY:
             temp = new WithdrawalWithCurrency(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
            break;
         case ZAKATMAL:
             temp = new ZakatMal(keypad, currentAccountNumber, screen, bankDatabase);
            break;
         case BIOSKOP:
             temp=new PembayaranBioskop(keypad, currentAccountNumber, screen, bankDatabase, filmDatabase);
             break;
        case TICKET :
        temp = new Ticket(currentAccountNumber, screen, bankDatabase,keypad);
        temp.execute(); break;
        case TRANSFERTOMANY :
            temp = new TransferToMany(currentAccountNumber, screen, bankDatabase, keypad);
            temp.execute();
        break;
        case ZAKATFITRAH:
             temp = new ZakatFitrah(keypad, currentAccountNumber, screen, bankDatabase);
            break;
        case BALANCE_INQUIRY_RUPIAH: 
            temp = new BalanceInquiryRupiah(
               currentAccountNumber, screen, bankDatabase);
            break;
        case VIRTUALACCOUNT:
            temp = new VATransaction(keypad, currentAccountNumber, screen, bankDatabase);               
            break;
            
      }

      return temp;
   } 
}
