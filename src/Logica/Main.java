/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author alvaro y patricia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Monitor m1 = new Monitor("IDX");
        Monitor m2 = new Monitor("IDX");
        Monitor m3 = new Monitor("IDX");
        Monitor m4 = new Monitor("IDX");
        Monitor m5 = new Monitor("IDX");
        Monitor m6 = new Monitor("IDX");
        Monitor m7 = new Monitor("IDX");
        Monitor m8 = new Monitor("IDX");
    
        JTextField colaEspera = new JTextField();
        JTextField colaDentro = new JTextField();

        ArrayList<Atracciones> listaAtracciones = null;
        Atracciones vestuario = new Atracciones("Vestuario", 30, 6000, 0, colaEspera, colaDentro, m1, false);
        Atracciones piscina_Olas = new Atracciones("Piscina de olas", 20, 2000, 3000, colaEspera, colaDentro, m2, false);
        Atracciones piscina_Ninos = new Atracciones("Piscina de ninos", 15, 1000, 300, colaEspera, colaDentro, m3, false);
        Atracciones piscina_Grande = new Atracciones("Piscina grande", 50, 3000, 2000, colaEspera, colaDentro, m4, false);
        Atracciones tumbonas = new Atracciones("Tumbonas", 20, 2000, 2000, colaEspera, colaDentro, m5, false);
        Atracciones toboganA = new Atracciones("Tobogan A", 1, 2000, 1000, colaEspera, colaDentro, m6, false);
        Atracciones toboganB = new Atracciones("Tobogan B", 1, 2000, 1000, colaEspera, colaDentro, m7, false);
        Atracciones toboganC = new Atracciones("Tobogan C", 1, 2000, 1000, colaEspera, colaDentro, m8, false);

        listaAtracciones.add(0, vestuario);
        listaAtracciones.add(1, piscina_Olas);
        listaAtracciones.add(2, piscina_Ninos);
        listaAtracciones.add(3, piscina_Grande);
        listaAtracciones.add(4, tumbonas);
        listaAtracciones.add(5, toboganA);
        listaAtracciones.add(6, toboganB);
        listaAtracciones.add(7, toboganC);
        
        Parque parque = new Parque(listaAtracciones,colaEspera,false);
    }
}
