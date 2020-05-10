/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alvaro y patricia
 */
public class Monitor extends Thread {

    private String identificación;
    private Atracciones atraccion;
    private Semaphore semaforo;
    private Paso paso;

    public Monitor(String identificación, Paso paso) {
        this.identificación = identificación;
        this.semaforo = new Semaphore(1, true);
        this.paso = paso;
    }

    public String getIdentificación() {
        return identificación;
    }

    public void setIdentificación(String identificación) {
        this.identificación = identificación;
    }

    @Override
    public String toString() {
        return identificación;
    }

    public Atracciones getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atracciones atraccion) {
        this.atraccion = atraccion;
    }

    public boolean directrices(Visitante visitante, String atraccion) {
        boolean tienePermiso = false;
        try {
            semaforo.acquire();
            paso.miron();
            switch (atraccion) {
                case "Vestuario":
                    sleep(1000);
                    tienePermiso = true;
                    break;
                case "Piscina de olas":
                    sleep(1000);
                    if (visitante.getEdad() > 5) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina de ninos":
                    sleep((int) (1000 + 500 * Math.random()));
                    if (visitante.getEdad() <= 11) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina grande":
                    sleep(500);
                    tienePermiso = true;
                    break;
                case "Tumbonas":
                    sleep((int) (500 + 400 * Math.random()));
                    if (visitante.getEdad() >= 15) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan A":
                    sleep((int) (400 + 100 * Math.random()));
                    if (11 <= visitante.getEdad() && visitante.getEdad() <= 14) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan B":
                    sleep((int) (400 + 100 * Math.random()));
                    if (15 <= visitante.getEdad() && visitante.getEdad() <= 17) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan C":
                    sleep((int) (400 + 100 * Math.random()));
                    if (18 <= visitante.getEdad()) {
                        tienePermiso = true;
                    }
                    break;
                default:
                    tienePermiso = false;
                    break;
            }
        } catch (InterruptedException e) {

        } finally {
            semaforo.release();
        }
        return tienePermiso;
    }

    public synchronized void detenerseM() {
        try {
            wait();
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (getAtraccion().getNombre().equals("Piscina grande")) { //saca aleatoriamente cada 10 sec a un visitante
                    this.atraccion.sacarAleatoriamente();
                    paso.miron();
                    sleep(10000);
                }
            }
        } catch (InterruptedException e) {

        }
    }
}
