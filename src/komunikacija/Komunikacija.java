/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class Komunikacija {
    
    private static Komunikacija instance;
    private Socket s; 
    //singleton da ne bismo omogucili jednom klijentu da salje uisto vreme dva zahteva nor.
    //dodaj knjigu i izmeni knjigu pa mu se prvo desi da se izmeni knjiga, pa da se doda
 
    private  Komunikacija() {
        try {
            s = new Socket("localhost",9000); //salje klijent zahtev na lokalhost port 9000
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Komunikacija getInstance(){
        if (instance == null){
            instance = new Komunikacija();
        }
        return instance;
    
    }
    
    public ServerskiOdgovor primiOdgovor(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            try {
                ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void posaljiZahtev(KlijentskiZahtev kz){
        try {
            ObjectOutputStream ous = new ObjectOutputStream(s.getOutputStream());
            ous.writeObject(kz);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
}
