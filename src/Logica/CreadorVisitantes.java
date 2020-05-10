/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1428491
 */
public class CreadorVisitantes extends Thread {

    private Paso paso;
    private Parque parque;

    public CreadorVisitantes(Paso paso, Parque parque) {
        this.paso = paso;
        this.parque = parque;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            try {
                sleep(400 + (int) (200 * Math.random()));
            } catch (InterruptedException ex) {
                Logger.getLogger(CreadorVisitantes.class.getName()).log(Level.SEVERE, null, ex);
            }
            int edad = ((int) (1 + 49 * Math.random()));
            paso.miron();
            Visitante visitante = new Visitante(edad, "ID" + i + "-" + edad, this.parque, false, paso);
            visitante.start();
        }
    }
}
