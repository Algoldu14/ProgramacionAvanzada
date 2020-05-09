/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author alvaro y patricia
 */
public class Parque {

    private int maxVisitiantes = 100;
    private ArrayList<Atracciones> listaAtracciones;
    private Semaphore semaforo;
    private ListaHilos colaEspera;
    private boolean detenido;

    public Parque(ArrayList<Atracciones> listaAtracciones, JTextField colaEsperaTF, boolean detenido) {
        this.listaAtracciones = listaAtracciones;
        colaEspera = new ListaHilos(colaEsperaTF);
        this.detenido = detenido;
        this.semaforo = new Semaphore(maxVisitiantes, true);

    }

    public void entrarP(Visitante visitante) {
        colaEspera.insertar(visitante);
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
        }
        colaEspera.extraer(visitante);
    }

    public void atraccionar(Visitante visitante, Atracciones atraccion) { //Elige la atraccion que quiere ir en el array

        atraccion.entrarA(visitante);
        atraccion.salirA(visitante);
    }

    public Atracciones cogerAtraccion() { //Coge un elemento aleatorio de las atracciones que no sean los vestuarios
        int min = 1;
        int max = listaAtracciones.size();
        Random rand = null;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return listaAtracciones.get(randomNum);
    }

    public void entrarVestuario(Visitante visitante) {
        listaAtracciones.get(0).entrarA(visitante);

    }

    public void salir(Visitante visitante) {
        semaforo.release();
    }

    public boolean isDetenido() {
        return detenido;
    }

    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }

    public synchronized void parar() {
        if (detenido) {
            try {
                wait();
            } catch (InterruptedException ex) {

            }
        }
    }

    public void notificar() {
        notifyAll();
    }

}
