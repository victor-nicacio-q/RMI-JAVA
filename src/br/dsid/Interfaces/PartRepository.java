package br.dsid.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote {

	public void listp() throws RemoteException;
	public void addp(Part p) throws RemoteException;
    public void getQuant() throws RemoteException;

	public Part busca(long id) throws RemoteException;

	public String getName() throws RemoteException;
}
