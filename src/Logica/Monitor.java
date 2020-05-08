/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author alvar
 */
public class Monitor extends Thread {

    private String identificación;

    public Monitor(String identificación) {
        this.identificación = identificación;
    }

    public String getIdentificación() {
        return identificación;
    }

    public void setIdentificación(String identificación) {
        this.identificación = identificación;
    }

    public boolean directrices(Visitante visitante, Atracciones atraccion) {
        boolean tienePermiso = false;

        try {
            switch (atraccion.getNombre()) {
                case "Vestuario":
                    sleep(1000);
                    if (atraccion.getAforo() < 30) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina de olas":
                    sleep(1000);
                    if (atraccion.getAforo() < 20 && visitante.getEdad() > 5) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina de ninos":
                    sleep((int) (1000 + 500 * Math.random()));
                    break;
                case "Piscina grande":
                    sleep((int) (500 * Math.random()));
                    break;
                case "Tumbonas":
                    sleep((int) (500 + 400 * Math.random()));
                    break;
                case "Tobogan A":
                    sleep((int) (400 + 100 * Math.random()));
                    break;
                case "Tobogan B":
                    sleep((int) (400 + 100 * Math.random()));
                    break;
                case "Tobogan C":
                    sleep((int) (400 + 100 * Math.random()));
                    break;
                default:
                    tienePermiso = false;
                    break;
            }
        } catch (InterruptedException e) {

        }

        return tienePermiso;
    }

    @Override
    public void run() {
        while (true) {
        }
    }
}
