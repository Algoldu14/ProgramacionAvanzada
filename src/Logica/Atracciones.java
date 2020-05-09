/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author alvaro y patricia
 */
public class Atracciones {

    private String nombre;
    private int aforo, t_disfrute, t_disfriteAlea;
    private Monitor monitor;
    private ListaHilos colaEspera;
    private ListaHilos dentro;
    private ListaHilos puesto;
    private Semaphore semaforo;
    private boolean detenido;

    public Atracciones(String nombre, int aforo, int t_disfrute, int t_disfruteAlea, JTextField tfEspera, JTextField tfDentro, JTextField puesto, Monitor monitor, boolean detenido) {
        this.nombre = nombre;
        this.aforo = aforo;
        this.t_disfrute = t_disfrute;
        this.t_disfriteAlea = t_disfriteAlea;
        this.monitor = monitor;
        this.semaforo = new Semaphore(aforo, true);
        this.colaEspera = new ListaHilos(tfEspera);
        this.dentro = new ListaHilos(tfDentro);
        this.puesto = new ListaHilos(puesto);
        this.detenido = detenido;
    }

    public void entrarA(Visitante visitante) {
        colaEspera.insertar(visitante);

        try {
            semaforo.acquire();
            colaEspera.extraer(visitante);
            if (this.monitor.directrices(visitante, this.getNombre())) {
                //System.out.println("Entro en la atraccion" + visitante.getIdentificacion());
                dentro.insertar(visitante);
                //System.out.println(this.getNombre() + "-" + dentro.getLista().size());
                this.tiempoAtraccion();
                this.salirA(visitante);
                visitante.setCansancio(visitante.getCansancio() + 1);
                //System.out.println("Salgo de la atraccion " + visitante.getIdentificacion());
            } else {
                //semaforo.release();
                colaEspera.extraer(visitante);
            }
        } catch (InterruptedException e) {
        } finally {
            semaforo.release();
        }
    }

    public void salirA(Visitante visitante) {
        dentro.extraer(visitante);
        if (this.getNombre().equals("Tobogan A") || this.getNombre().equals("Tobogan B") || this.getNombre().equals("Tobogan C")) {
            visitante.getParque().atraccionar(visitante, 3); //Entra directamente en la piscina si viene del tobogan
        }
    }

    public void sacarAleatoriamente() {
        if (!dentro.getLista().isEmpty()) {
            int num = ((int) (dentro.getLista().size() * Math.random()));
            Visitante visitanteS = (Visitante) dentro.getLista().get(num);
            System.out.println("El monitor de la piscina grande va a sacar a: " + visitanteS.getIdentificacion());
            this.salirA(visitanteS);
            semaforo.release();
        }

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

    public int getT_disfrute() {
        return t_disfrute;
    }

    public void setT_disfrute(int t_disfrute) {
        this.t_disfrute = t_disfrute;
    }

    public synchronized void detener() {
        if (this.detenido == true) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
    }

    public void tiempoAtraccion() {
        try {
            sleep((int) (t_disfrute + Math.random() * t_disfriteAlea));
        } catch (Exception e) {

        }
    }

    public void monitorTrabaja() {
        puesto.insertar(monitor);
    }
}
