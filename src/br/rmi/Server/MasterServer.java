package br.rmi.Server;

import br.rmi.Interfaces.PartRepository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class MasterServer {

	private static final int port = 1099;
	private int quant;
	private Registry registry;
		
	public MasterServer() {
		try {
			this.registry = LocateRegistry.createRegistry(port);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}

		public String[] getServers() throws RemoteException {
			return this.registry.list();
		}
		
		public int getQuantServers() {
			return this.quant;
		}
		
		public boolean criarServidor(String nome) throws RemoteException {
		
				Server server = new Server();
				server.setName(nome + ":1099");
				UnicastRemoteObject.unexportObject(server, true);
				PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(server, port);
				
	            try {
	                registry.bind(server.getName(), stub);
	            } catch (AlreadyBoundException ae) {
	                registry.rebind(server.getName(), stub);
	            }
	            System.out.println("\nServidor stand by na porta: " + port + "\n");
	        quant++;
		 return true;
		}
}
