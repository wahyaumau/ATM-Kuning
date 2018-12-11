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
public class VirtualAccount {
    private int accountReceiver;
    private double bill;
    private String code;

    public VirtualAccount(int accountReceiver, double bill, String code) {
        this.accountReceiver = accountReceiver;
        this.bill = bill;
        this.code = code;
    }

    public int getAccountReceiver() {
        return accountReceiver;
    }

    public double getBill() {
        return bill;
    }

    public String getCode() {
        return code;
    }
    
    
}
