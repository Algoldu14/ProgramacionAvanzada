/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Interfaz.*;
import static java.lang.Thread.sleep;

/**
 *
 * @author alvaro y patricia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        generarVisitantes();

    }

    public static void generarVisitantes() throws InterruptedException {
        VentanaPrin ventana = new VentanaPrin();
        ventana.setVisible(true);
       
        for (int i = 0; i < 5000; i++) {
            sleep(400 + (int) (200 * Math.random()));
            int edad = ((int) (1 + 49 * Math.random()));
            Visitante visitante = new Visitante(edad, "ID" + i + "-" + edad, ventana.getParque(), false);
            visitante.start();
        }

    }

}
