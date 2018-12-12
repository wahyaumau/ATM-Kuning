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
public class BankDatabase {
   private Account[] accounts; // array of Accounts
   private KeretaApi[] kereta;
   private Pesawat[] pesawat;
   
   public BankDatabase() {
      accounts = new Account[8]; // just 2 accounts for testing
      accounts[0] = new Account(1234, 4321, 4000.0,5000.0);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0);  
      accounts[2] = new Account(12345, 54321, 1000.0, 1200.0);  
      accounts[3] = new Account(9998, 9999, 0, 0); //akun admin
      accounts[4] = new Account(23456,65432, 0, 0);//penjual tiket kereta
      accounts[5] = new Account(34567,76543, 0, 0);//penjual akun Garuda Indonesia
      accounts[6] = new Account(45678,87654, 0, 0);//penjual akun Lion Air
      accounts[7] = new Account(11111,11111, 0, 0);//Virtual account
   }   
   
   public Account getAccount(int accountNumber) {
      for(Account a : accounts){
          if(a.getAccountNumber()==accountNumber) return a;
      }
      return null; // if no matching account was found, return null
   } 
   
   public boolean isAccountExist(int accountNumber) {
      for(Account a : accounts){
          if(a.getAccountNumber()== accountNumber) return true;
      }
      return false; // if no matching account was found, return null
   } 

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   } 

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }
   
   public void creditForTransfer(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).creditForTransfer(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 
   
   //IKRAM
   public void blockAccount(int userAccountNumber){
       getAccount(userAccountNumber).blockAccount();
   }
   
   public void RecoverAccount(int userAccountNumber){
       getAccount(userAccountNumber).recoverAccount();
   }
   
   public boolean getAccountStatus(int userAccountNumber){
       return getAccount(userAccountNumber).getAccountStatus();
   }
   
   //JAKA
   public void tulisHistory(int userAccountNumber,String history)
   {
       getAccount(userAccountNumber).addHistory(history);
   }
   
   public void tampilHistory(int userAccountNumber)
   {
       while (getAccount(userAccountNumber).getHistory().isEmpty() != true)
       {
           System.out.println(getAccount(userAccountNumber).getHistory().pop());
       }
   }
   
   
   //RAYHAN
   public void getAccount_all() {
      Screen screen = new Screen();
      for (Account a : accounts){
          if(a.getAccountNumber()!= 9999){
              screen.displayMessage("\nAccountNumber = " + a.getAccountNumber() + " Available balance = " + a.getAvailableBalance() + " Total balance = " + a.getTotalBalance() + "\n");
          }
          
      }
   }
   //wilda
   public void DataBaseKereta(){
      kereta = new KeretaApi[2];
      kereta[0] = new KeretaApi(123,321,"WILDA",5,50,"Bandung - Jakarta");
      kereta[1] = new KeretaApi(234,432,"RIZKI",3,30,"Jakarta - Bandung");
   }
   
   public void DataBasePesawat(){
      pesawat = new Pesawat[2];
      pesawat[0] = new Pesawat(1234,4321,"RAZINI",5,500,"Jakarta - Seoul","Garuda Indonesia");
      pesawat[1] = new Pesawat(2345,5432,"RAZY",3,300,"Seoul - Jakarta","Lion Air");
   }
   
   private KeretaApi getKode(int kode){
      DataBaseKereta();
      for(KeretaApi a : kereta){
          if(a.getKdPembayaran() == kode){
              return a;
          }
      }
      return null;
   }
   
   private Pesawat getKodePesawat(int kode){
      DataBasePesawat();
      for(Pesawat a : pesawat){
          if(a.getKdPembayaran() == kode){
              return a;
          }
      }
      return null;
   }
   
   public boolean validasiKdKereta(int kode) {
       KeretaApi k = getKode(kode);
       if(k != null)return true;
       else return false;
   }
   
   public boolean validasiKdPesawat(int kode){
       Pesawat P = getKodePesawat(kode);
       if(P != null)return true;
       else return false;
   }
   public double getHargaPembayaran(int Kd){
      return getKode(Kd).getJumlahBayar();
   }
   
   public double getHargaPembayaranPesawat(int Kd){
      return getKodePesawat(Kd).getJumlahBayar();
   }
   public int getKodeBooking(int Kd){
        return getKode(Kd).getKdBooking();
   }
   
   public int getKodeBookingPesawat(int Kd){
        return getKodePesawat(Kd).getKdBooking();
   }
   
   public String getNama(int Kd){
        return getKode(Kd).getNama();
   }
   
   public String getNamaP(int Kd){//memperoleh nama yang membeli tiket pesawat
        return getKodePesawat(Kd).getNama();
   }
   
   public int getJumlahSeat(int Kd){
        return getKode(Kd).getJumlahSeat();
   }
   
   public int getJumlahSeatPesawat(int Kd){
        return getKodePesawat(Kd).getJumlahSeat();
   }
   
   public String getRute(int Kd){
        return getKode(Kd).getRute();
   }
   
   public String getRutePesawat(int Kd){
        return getKodePesawat(Kd).getRute();
   }
   
   public String getMaskapaiPesawat(int Kd){
        return getKodePesawat(Kd).getMaskapai();
   }
} 
