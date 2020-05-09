/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author alvaro y patricia
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

    @Override
    public String toString() {
        return identificación;
    }

    public boolean directrices(Visitante visitante, String atraccion) {
        boolean tienePermiso = false;
        try {
            switch (atraccion) {
                case "Vestuario":
                    this.sleep(1000);
                    tienePermiso = true;
                    break;
                case "Piscina de olas":
                    this.sleep(1000);
                    if (visitante.getEdad() > 5) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina de ninos":
                    this.sleep((int) (1000 + 500 * Math.random()));
                    if (visitante.getEdad() <= 11) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina grande":
                    this.sleep(500);
                    tienePermiso = true;
                    break;
                case "Tumbonas":
                    this.sleep((int) (500 + 400 * Math.random()));
                    if (visitante.getEdad() >= 15) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan A":
                    this.sleep((int) (400 + 100 * Math.random()));
                    if (11 <= visitante.getEdad() && visitante.getEdad() <= 14) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan B":
                    this.sleep((int) (400 + 100 * Math.random()));
                    if (15 <= visitante.getEdad() && visitante.getEdad() <= 17) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan C":
                    this.sleep((int) (400 + 100 * Math.random()));
                    if (18 <= visitante.getEdad()) {
                        tienePermiso = true;
                    }
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
