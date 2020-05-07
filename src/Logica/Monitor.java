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
    private Atracciones atraccion;
    private Parque parque;

    public Monitor(String identificación, Atracciones atraccion, Parque parque) {
        this.identificación = identificación;
        this.atraccion = atraccion;
        this.parque = parque;
    }

    public String getIdentificación() {
        return identificación;
    }

    public void setIdentificación(String identificación) {
        this.identificación = identificación;
    }

    public Atracciones getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atracciones atraccion) {
        this.atraccion = atraccion;
    }

    public Parque getParque() {
        return parque;
    }

    public void setParque(Parque parque) {
        this.parque = parque;
    }

    public boolean directrices(Visitante visitante, String atraccion) {

        try {
            switch (atraccion) {
                case "Vestuario":
                    sleep(1000);
                    return true;
                case "Piscina de olas":
                    sleep(1000);
                    return true;
                case "Piscina de ninos":
                    sleep((int) (1000 + 500 * Math.random()));
                    return true;
                case "Piscina grande":
                    sleep((int) (500 * Math.random()));
                    return true;
                case "Tumbonas":
                    sleep((int) (500 + 400 * Math.random()));
                    return true;
                case "Tobogan A":
                    sleep((int) (400 + 100 * Math.random()));
                    return true;
                case "Tobogan B":
                    sleep((int) (400 + 100 * Math.random()));
                    return true;
                case "Tobogan C":
                    sleep((int) (400 + 100 * Math.random()));
                    return true;
            }
        } catch (Exception e) {

        }
        return false;

    }

}
