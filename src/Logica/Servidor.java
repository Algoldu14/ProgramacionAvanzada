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
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author luismiguelvillanueva
 */
public class Servidor extends UnicastRemoteObject implements InterfazVigilancia {

    private final Parque parque;

    public Servidor(Parque parque) throws AlreadyBoundException, MalformedURLException, RemoteException {
        this.parque = parque;
        LocateRegistry.createRegistry(1099);
        Naming.bind("//localhost/ModuloDeControl", this);
        System.out.println("El controlador ha quedado registrado");
    }

    @Override
    public String[] controlUbicacion(String id) throws RemoteException {
        return parque.buscarUbicacion(id);
    }

    @Override
    public String buscarMenores() throws RemoteException {
        return Integer.toString(parque.getMenores());
    }

    @Override
    public String[] controltoboganes() throws RemoteException {
        return parque.mirarToboganes();
    }

    @Override
    public String[] usuariosZona() throws RemoteException {
        return parque.mirarAforos();
    }

}
