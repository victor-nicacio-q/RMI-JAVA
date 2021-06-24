package br.dsid.Client;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import br.dsid.Interfaces.PartRepository;
import br.dsid.Interfaces.Part;

public class Client implements Serializable {
	public PartRepository stub = null;
	public ArrayList<Part> rep_client;
	public Part peca;
	public ArrayList<Part> sub_pecas;
	
	public void bind(String name) {
		try {
			Registry registro_nomes = LocateRegistry.getRegistry(1099);
            PartRepository stub = (PartRepository) registro_nomes.lookup(name+":1099");
            this.stub = stub;
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean listPart() throws RemoteException {
		if(this.stub == null) return false;
		this.stub.listp();
		return true;
	}
	
	public boolean addPart(Part str) throws RemoteException {
		if(this.stub == null) return false;
		this.stub.addp(str);
		return true;
	}

	public boolean getQuant() throws RemoteException {
		if(this.stub == null) return false;
		this.stub.getQuant();
		return true;
	}

	public boolean buscaPeca(long id) throws RemoteException {
		if(this.stub == null) return false;
		this.peca = this.stub.busca(id);
		if (this.peca != null) {
			System.out.println("Peça adicionada no repositorio");
			return true;
		} else {
			return false;
		}
	}
}
