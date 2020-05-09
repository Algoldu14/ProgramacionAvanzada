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
public class Visitante extends Thread {

    private int edad;
    private String identificacion;
    private Parque parque;
    private int cansancio;
    private boolean vestido;

    public Visitante(int edad, String identificacion, Parque parque, boolean vestido) {
        this.edad = edad;
        this.identificacion = identificacion;
        this.parque = parque;
        this.cansancio = 0;
        this.vestido = vestido;
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

    public boolean isVestido() {
        return vestido;
    }

    public void setVestido(boolean vestido) {
        this.vestido = vestido;
    }
    

    @Override
    public String toString() {
        return identificacion;
    }

    @Override
    public void run() {
        try {
            this.parque.entrarP(this); //Entra al parque
            while (true) {
                if (this.cansancio >= (5 + (int) (Math.random() * 10))) {//Accede a entre 5 y 15 atracciones
                    this.parque.salir(this); //Sale del parque
                    break;

                } else { //Si no esta cansado, atracciona
                    if (this.vestido) {
                        Atracciones atraccion = this.parque.cogerAtraccion();
                        this.parque.atraccionar(this, atraccion);
                    } else {
                        this.parque.entrarVestuario(this); //Va al vestuario
                        this.setVestido(true);
                    }

                }
            }
        } catch (Exception e) {

        }
    }

}
