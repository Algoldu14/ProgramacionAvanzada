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
    private Paso paso;

    public Atracciones(String nombre, int aforo, int t_disfrute, int t_disfruteAlea, JTextField tfEspera, JTextField tfDentro, JTextField puesto, Monitor monitor, boolean detenido, Paso paso) {
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
        this.paso = paso;
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

    public ListaHilos getColaEspera() {
        return colaEspera;
    }

    public void setColaEspera(ListaHilos colaEspera) {
        this.colaEspera = colaEspera;
    }

    public ListaHilos getDentro() {
        return dentro;
    }

    public void setDentro(ListaHilos dentro) {
        this.dentro = dentro;
    }

    public void entrarA(Visitante visitante) {
        paso.miron();
        colaEspera.insertar(visitante);
        paso.miron();
        try {
            semaforo.acquire();
            colaEspera.extraer(visitante);
            paso.miron();
            if (this.monitor.directrices(visitante, this.getNombre())) {
                paso.miron();
                //System.out.println("Entro en la atraccion" + visitante.getIdentificacion());
                dentro.insertar(visitante);
                //System.out.println(this.getNombre() + "-" + dentro.getLista().size());
                this.tiempoAtraccion();
                this.salirA(visitante);
                visitante.setCansancio(visitante.getCansancio() + 1);
                //System.out.println("Salgo de la atraccion " + visitante.getIdentificacion());
            } else {
                paso.miron();
                //semaforo.release();
                colaEspera.extraer(visitante);
            }
        } catch (InterruptedException e) {

        } finally {
            paso.miron();
            semaforo.release();
        }
    }

    public void salirA(Visitante visitante) throws InterruptedException {
        paso.miron();
        dentro.extraer(visitante);
        paso.miron();
        if (this.getNombre().equals("Tobogan A") || this.getNombre().equals("Tobogan B") || this.getNombre().equals("Tobogan C")) {
            paso.miron();
            this.pasarToboganAPiscina(visitante);
        }
    }

    public void pasarToboganAPiscina(Visitante visitante) throws InterruptedException {

        visitante.getParque().getListaAtracciones().get(3).getSemaforo().acquire();
        paso.miron();
        visitante.getParque().getListaAtracciones().get(3).getDentro().insertar(visitante);
        paso.miron();
        visitante.getParque().getListaAtracciones().get(3).tiempoAtraccion();
        paso.miron();
        visitante.getParque().getListaAtracciones().get(3).salirA(visitante);
        paso.miron();
        visitante.getParque().getListaAtracciones().get(3).getSemaforo().release();

    }

    public Semaphore getSemaforo() {
        return this.semaforo;
    }

    public void sacarAleatoriamente() throws InterruptedException {
        paso.miron();
        if (!dentro.getLista().isEmpty()) {
            paso.miron();
            int num = ((int) (dentro.getLista().size() * Math.random()));
            Visitante visitanteS = (Visitante) dentro.getLista().get(num);
            System.out.println("El monitor de la piscina grande va a sacar a: " + visitanteS.getIdentificacion());
            this.salirA(visitanteS);
            semaforo.release();
            paso.miron();
        }
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
