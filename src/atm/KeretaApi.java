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
public class KeretaApi {
    int Kdpemp;
    int Kdbook;
    String nama;
    int tmptduduk;
    double bayar;
    String R;
    
    public KeretaApi(int KdPembayaran,int Kdbooking,String Nama,int JmlSeat,double jmlBayar,String Rute){
        Kdpemp = KdPembayaran;
        Kdbook = Kdbooking;
        nama = Nama;
        tmptduduk = JmlSeat;
        bayar = jmlBayar;
        R = Rute;
    }
    
    public int getKdPembayaran(){
        return Kdpemp;
    }
    
    public int getKdBooking(){
        return Kdbook;
    }
    
    public String getNama(){
        return nama;
    }
    
    public int getJumlahSeat(){
        return tmptduduk;
    }
    
    public double getJumlahBayar(){
        return bayar;
    }
    
    public String getRute(){
        return R;
    }
}

