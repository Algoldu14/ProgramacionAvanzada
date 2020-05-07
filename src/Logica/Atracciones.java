/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author alvar
 */
public class Atracciones {
    
    private String nombre;
    private int aforo, edadMin, edadMax, t_espera;
    private Monitor monitor;
    private ListaHilos colaEspera;
    private ListaHilos dentro;
    private Semaphore semaforo;
    private boolean detenido;
    
    public Atracciones(String nombre, int aforo, int edadMin, int edadMax, int t_espera, JTextField tfEspera, JTextField tfDentro, Monitor monitor, boolean detenido) {
        this.nombre = nombre;
        this.aforo = aforo;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.t_espera = t_espera;
        this.monitor = monitor;
        this.semaforo = new Semaphore(aforo, true);
        this.dentro = new ListaHilos(tfDentro);
        this.colaEspera = new ListaHilos(tfEspera);
        this.detenido = detenido;
    }
    
    public void entrarA(Visitante visitante) {
        
        colaEspera.insertar(visitante);
        
        try {
            semaforo.acquire();
            visitante.setCansancio(visitante.getCansancio() + 1);
            colaEspera.extraer(visitante);
            if (this.monitor.directrices(visitante, this)) {
                dentro.insertar(visitante);
            } else {
                semaforo.release();
                
            }
        } catch (InterruptedException e) {
        }
    }
    
    public void salirA(Visitante visitante) {
        dentro.extraer(visitante);
        semaforo.release();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public boolean isDetenido() {
        return detenido;
    }
    
    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
    
    public synchronized void detener() {
        if (this.detenido == true) {
            try {
                wait();
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}
