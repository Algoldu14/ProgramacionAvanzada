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
    private Paso paso;

    public Visitante(int edad, String identificacion, Parque parque, boolean vestido, Paso paso) {
        this.edad = edad;
        this.identificacion = identificacion;
        this.parque = parque;
        this.cansancio = 0;
        this.vestido = vestido;
        this.paso = paso;
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

    public synchronized void detenerseV() {
        try {
            wait();
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            paso.miron();
            this.parque.entrarP(this); //Entra al parque
            while (true) {
                paso.miron();
                if (this.cansancio >= (5 + (int) (Math.random() * 10))) {//Accede a entre 5 y 15 atracciones
                    paso.miron();
                    this.parque.salirP(this); //Sale del parque
                    paso.miron();
                    break;
                } else { //Si no esta cansado, atracciona
                    paso.miron();
                    if (vestido) {
                        paso.miron();
                        //System.out.println("Busca atraccion" + this.identificacion);
                        int nAtraccion = this.parque.cogerAtraccion(); //Coge una atraccion
                        //System.out.println("Entro en la atraccion" + nAtraccion + "este man" + this.identificacion);
                        this.parque.atraccionar(this, nAtraccion); //Entra en ella
                        paso.miron();
                    } else {
                        paso.miron();
                        this.parque.entrarVestuario(this); //Va al vestuario
                        this.vestido = true;
                        //System.out.println("Sale del vestuario" + this.identificacion);
                        paso.miron();
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
