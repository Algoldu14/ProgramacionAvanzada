/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alvaro y patricia
 */
public interface InterfazVigilancia extends Remote {

    public String[] controlUbicacion(String id) throws RemoteException;

    public String buscarMenores() throws RemoteException;

    public String[] controlToboganes() throws RemoteException;

    public String[] usuariosZona() throws RemoteException;

    public void alternar() throws RemoteException;

}
