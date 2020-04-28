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

    public void directrices() {

        switch (this.atraccion.getNombre()) {
            case "Vestuario":
                
                break;
            case "Piscina de olas":
                
                break;
            case "Piscina de ninos":
                
                break;
            case "Piscina grande":
                
                break;
            case "Tumbonas":
                
                break;
            case "Tobogan A":
                
                break;
            case "Tobogan B":
                
                break;
            case "Tobogan C":
                
                break;
        }
    }
    
    
}
