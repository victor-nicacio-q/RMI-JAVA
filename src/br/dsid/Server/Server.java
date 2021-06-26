package br.dsid.Server;

import br.dsid.Interfaces.PartRepository;
import br.dsid.Interfaces.Part;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements PartRepository {

	private String name;
	ArrayList<Part> rep = new ArrayList<>();

	@Serial
	private static final long serialVersionUID = 1L;
	
	public Server() throws RemoteException {
		super();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() throws RemoteException{
		return this.name;
	}
	
	
	public void listp() throws RemoteException {
		for (Part p : rep) {
			System.out.println("Repositório do Servidor: " + this.name);
			System.out.println(p);
			System.out.println("==================");
		}
	}

	public void addp(Part str) throws RemoteException {
		System.out.println("\nPeça\n"+ str + " adicionada\n\n");
		this.rep.add(str);
	}

	public void getQuant() throws RemoteException {
		System.out.println(this.rep.size());
	}

	public Part busca(long id) throws RemoteException {
		for (Part atual : this.rep) {
			if (atual.id == id)
				return atual;
		}
		return null;
	}
}