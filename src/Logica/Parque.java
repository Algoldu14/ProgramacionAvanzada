/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static java.lang.Thread.sleep;
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
        salir(visitante);

    }

    public void atraccionar(Visitante visitante, Atracciones atraccion) { //Elige la atraccion que quiere ir en el array
        atraccion.entrarA(visitante);
        atraccion.salirA(visitante);
    }

    public Atracciones cogerAtraccion() { //Coge un elemento aleatorio de las atracciones
        int random = new Random().nextInt(listaAtracciones.size());
        return listaAtracciones.get(random);
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

    public void generarVisitantes() {
        try {
            for (int i = 0; i < 5000; i++) {
                //sleep(400 + (int) (200 * Math.random()));
                int edad = ((int)(1 + 49 * Math.random()));
                Visitante visitante = new Visitante(edad, "ID" + i + "-" + edad, this);
                visitante.run();
            }
        } catch (Exception e) {

        }
    }
}
