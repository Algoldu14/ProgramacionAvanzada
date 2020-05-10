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
    private int menores;
    private Paso paso;

    public Parque(ArrayList<Atracciones> listaAtracciones, JTextField colaEsperaP, boolean detenido, Paso paso) {
        this.listaAtracciones = listaAtracciones;
        colaEspera = new ListaHilos(colaEsperaP);
        this.detenido = detenido;
        this.semaforo = new Semaphore(100, true);
        this.menores = 0;
        this.paso = paso;
    }

    public void entrarP(Visitante visitante) throws InterruptedException {
        paso.miron();
        this.colaEspera.insertar(visitante);
        paso.miron();
        if (visitante.getEdad() < 18) {
            menores++;
        }
        try {
            paso.miron();
            this.semaforo.acquire();
        } catch (InterruptedException e) {
            
        }
        paso.miron();
        visitante.sleep(1000);
        paso.miron();
        this.colaEspera.extraer(visitante);
        paso.miron();
    }

    public void atraccionar(Visitante visitante, int atraccion) throws InterruptedException { //Elige la atraccion que quiere ir en el array
        paso.miron();
        listaAtracciones.get(atraccion).entrarA(visitante);
        paso.miron();
        listaAtracciones.get(atraccion).salirA(visitante);
        paso.miron();
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

    public ArrayList<Atracciones> getListaAtracciones() {
        return listaAtracciones;
    }

    public void setListaAtracciones(ArrayList<Atracciones> listaAtracciones) {
        this.listaAtracciones = listaAtracciones;
    }

    public void salirP(Visitante visitante) {
        paso.miron();
        semaforo.release();
        paso.miron();
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

    public String[] buscarUbicacion(String id) {
        String[] parametros = new String[]{"Not found", "Not found"};
        for (int i = 0; i < listaAtracciones.size(); i++) {
            if (listaAtracciones.get(i).getColaEspera().getLista().contains(id)) {
                parametros[0] = listaAtracciones.get(i).getNombre();
                parametros[1] = listaAtracciones.get(i).getColaEspera().getLista().get(this.getPosicion(listaAtracciones.get(i).getColaEspera().getLista(), id)).toString();
            }
            if (listaAtracciones.get(i).getDentro().getLista().contains(id)) {
                parametros[0] = listaAtracciones.get(i).getNombre();
                parametros[1] = listaAtracciones.get(i).getColaEspera().getLista().get(this.getPosicion(listaAtracciones.get(i).getDentro().getLista(), id)).toString();
            }
        }
        return parametros;
    }

    public int getPosicion(ArrayList<Thread> lista, String visitante) {
        for (int i = 0; i < lista.size(); i++) {
            Visitante v = (Visitante) lista.get(i);
            if (v.getIdentificacion().equals(visitante)) {
                return i;
            }
        }
        return -1;
    }

    public int getMenores() {
        return menores;
    }

    public String[] mirarToboganes() {
        String[] parametros = new String[]{"Nadie", "Nadie", "Nadie"};
        if (!listaAtracciones.get(5).getDentro().getLista().isEmpty()) {
            parametros[0] = listaAtracciones.get(5).getDentro().getLista().get(0).toString(); //Tobogan A
        }
        if (!listaAtracciones.get(6).getDentro().getLista().isEmpty()) {
            parametros[1] = listaAtracciones.get(6).getDentro().getLista().get(0).toString(); //Tobogan B
        }
        if (!listaAtracciones.get(5).getDentro().getLista().isEmpty()) {
            parametros[2] = listaAtracciones.get(7).getDentro().getLista().get(0).toString(); //Tobogan C
        }

        return parametros;
    }

    public String[] mirarAforos() {
        String[] parametros = new String[]{"", "", "", "", "", "", "", "", ""};
        for (int i = 0; i < listaAtracciones.size(); i++) {
            parametros[i] = Integer.toString(listaAtracciones.get(i).getDentro().getLista().size());
        }
        parametros[8] = Integer.toString(listaAtracciones.get(5).getDentro().getLista().size()
                + listaAtracciones.get(6).getDentro().getLista().size() + listaAtracciones.get(7).getDentro().getLista().size());
        return parametros;
    }

    public synchronized void notificar() {
        notifyAll();
    }

}
