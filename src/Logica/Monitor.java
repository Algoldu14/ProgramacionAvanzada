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

    public boolean directrices(Visitante visitante, Atracciones atraccion) {
        boolean tienePermiso = false;
        try {
            switch (atraccion.getNombre()) {
                case "Vestuario":
                     this.sleep(1000);
                    if (atraccion.getAforo() < 30) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina de olas":
                    this.sleep(1000);
                    if (atraccion.getAforo() < 20 && visitante.getEdad() > 5) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina de ninos":
                    this.sleep((int) (1000 + 500 * Math.random()));
                    if (atraccion.getAforo() < 15 && visitante.getEdad() <= 11) {
                        tienePermiso = true;
                    }
                    break;
                case "Piscina grande":
                   this.sleep((int) (500 * Math.random()));
                    if (atraccion.getAforo() < 50) {
                        tienePermiso = true;
                    }
                    break;
                case "Tumbonas":
                    this.sleep((int) (500 + 400 * Math.random()));
                    if (atraccion.getAforo() < 20 && visitante.getEdad() >= 15) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan A":
                    this.sleep((int) (400 + 100 * Math.random()));
                    if (atraccion.getAforo() <= 1 && 11 <= visitante.getEdad() && visitante.getEdad() <= 14) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan B":
                    this.sleep((int) (400 + 100 * Math.random()));
                    if (atraccion.getAforo() <= 1 && 15 <= visitante.getEdad() && visitante.getEdad() <= 17) {
                        tienePermiso = true;
                    }
                    break;
                case "Tobogan C":
                    this.sleep((int) (400 + 100 * Math.random()));
                    if (atraccion.getAforo() <= 1 && 18 <= visitante.getEdad()) {
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
