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

    private int edad;
    private String identificacion;
    private Parque parque;
    private int cansancio;

    public Visitante(int edad, String identificacion, Parque parque) {
        this.edad = edad;
        this.identificacion = identificacion;
        this.parque = parque;
        this.cansancio = 0;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

   

    @Override
    public void run() {
        try {

            parque.entrarP(this); //Entra al parque

            while (true) {

                if (this.cansancio >= (5 + (int) (Math.random() * 10))) {//Accede a entre 5 y 15 atracciones
                    parque.salir(this); //Sale del parque
                    break;

                } else { //Si no esta cansado, atracciona
                    Atracciones atraccion = parque.cogerAtraccion();
                    parque.atraccionar(this, atraccion);

                }

            }

        } catch (Exception e) {
        }
    }

}
