/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author alvaro y patricia
 */

/* La clase ListaThreads permite gestionar las listas de threads en los monitores,

con métodos para meter y sacar threads en ella. Cada vez que una lista se modifica,

se imprime su nuevo contenido en el JTextField que toma como parámetro el constructor. */
public class ListaHilos {

    private ArrayList<Thread> lista;
    private JTextField tf;

    public ListaHilos(JTextField tf) {
        this.tf = tf;
        lista = new ArrayList<>(); 
    }

    public synchronized void insertar(Thread t) {
        lista.add(t);
        imprimir();
    }

    public synchronized void extraer(Thread t) {
        lista.remove(t);
        imprimir();
    }

    public void imprimir() {
        String contenido = "";
        for (int i = 0; i < lista.size(); i++) {
            contenido = contenido + lista.get(i).toString() + " ";
        }
        tf.setText(contenido);
    }
}
