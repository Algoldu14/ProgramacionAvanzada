/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author alvaro y patricia
 */
public class Parque {

    private ArrayList<Atracciones> listaAtracciones;
    private Semaphore semaforo;
    private ListaHilos colaEspera;
    private boolean detenido;

    public Parque(ArrayList<Atracciones> listaAtracciones, JTextField colaEsperaP, boolean detenido) {
        this.listaAtracciones = listaAtracciones;
        colaEspera = new ListaHilos(colaEsperaP);
        this.detenido = detenido;
        this.semaforo = new Semaphore(100, true);
    }

    public void entrarP(Visitante visitante) throws InterruptedException {
        this.colaEspera.insertar(visitante);
        try {
            this.semaforo.acquire();
        } catch (InterruptedException e) {
        }
        visitante.sleep(1000);
        this.colaEspera.extraer(visitante);
    }

    public void atraccionar(Visitante visitante, int atraccion) { //Elige la atraccion que quiere ir en el array
        listaAtracciones.get(atraccion).entrarA(visitante);
        listaAtracciones.get(atraccion).salirA(visitante);
    }

    public int cogerAtraccion() { //Coge un elemento aleatorio de las atracciones que no sean los vestuarios
        int min = 1;
        int max = listaAtracciones.size();
        int num = ((int) (max * Math.random()) + min);
        //System.out.println(num);
        return num;
    }

    public void entrarVestuario(Visitante visitante) {
        listaAtracciones.get(0).entrarA(visitante);
    }

    public void salirP(Visitante visitante) {
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
