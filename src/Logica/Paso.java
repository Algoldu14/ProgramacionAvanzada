/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author alvaro y patricia
 */
public class Paso {

    private boolean parado = false;
    private final JButton boton;

    public Paso(JButton boton) {
        this.boton = boton;
        this.boton.setBackground(Color.red);
    }

    public synchronized void miron() {
        while (parado) {
            try {
                wait();
            } catch (Exception e) {
                
            }
        }
    }

    public synchronized void alternon() {
        if (parado) {
            parado = false;
            boton.setBackground(Color.red);
            boton.setText("Detener");
            notifyAll();
        } else {
            boton.setBackground(Color.green);
            boton.setText("Reanudar");
            parado = true;
        }
    }

    public synchronized boolean isAcabado() {
        return parado;
    }
}
