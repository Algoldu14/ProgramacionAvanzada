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
public class Visitante extends Thread {

    private String identificacion;
    private Parque parque;
    private int cansancio;

    public Visitante(String identificacion, Parque parque, int cansancio) {
        this.identificacion = identificacion;
        this.parque = parque;
        this.cansancio = cansancio;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Parque getParque() {
        return parque;
    }

    public void setParque(Parque parque) {
        this.parque = parque;
    }

    public int getCansancio() {
        return cansancio;
    }

    public void setCansancio(int cansancio) {
        this.cansancio = cansancio;
    }
    
    
    
}
