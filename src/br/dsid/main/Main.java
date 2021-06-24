package br.dsid.main;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import br.dsid.Client.Client;
import br.dsid.Interfaces.Part;
import br.dsid.Server.MasterServer;

public class Main {

	private static Scanner in = new Scanner(System.in);
	private static final Client client = new Client();
	private static final MasterServer ms = new MasterServer();
	private static String message;
	
	 public static void status() {
			System.out.println("Quantidade de servidores ativos: "+ms.getQuantServers());
			try {
				System.out.println("Lista de servers: ");
				String [] list = ms.getServers();
				for(int i = 0; i < list.length;i++) System.out.println(list[i]);
				System.out.println("");
			} catch (RemoteException e) {
				e.printStackTrace();
			}

	 }
	 
	public static void commands() {
		System.out.println("Comandos:\n");
		System.out.println("Preferencialmente após criar algum servidor\n");
		System.out.println("	bind - conecta ao servidor");
		System.out.println("	listp - lista peças do repositorio corrente");
		System.out.println("	addp - adiciona peça ao repositorio corrente");
		System.out.println("	getq - quantidade de peças no repositório");
		System.out.println("	getp - buscar peça");
		System.out.println("	showp - atributos peça corrente [Apenas após getp]");
		System.out.println("	clearlist - limpar lista de sub-peças[N]");
		System.out.println("	back");
		System.out.println();

	}
	
	public static void list() {
		System.out.println("\n\n[addserver] - Inserir novo servidor");
		System.out.println("[command] - Comandos");
		System.out.println("[lists] - Lista de servidores ativos");
		System.out.println("[help] - Printar menu principal");
		System.out.println("[quit] - Encerrar o programa\n");

	}
	
	public static void messages() {
		message = in.next();
		
		while(message.equals("")) {
			System.out.println();
			message = in.nextLine();
		}
	}
	
	public static void processCmd() throws RemoteException {
		boolean done = false;
		
		while(!done) {
			if (done) System.out.print("[command] > [" + client.stub.getName() + "]");
			else System.out.print("[command] > ");
			String str = in.next();

			if(str.equals("bind")) {
				System.out.print("[command | bind] > Nome do servidor: ");
				str = in.next();
				client.bind(str);
			}
			if(str.equals("listp")) {
				client.listPart();
			}
			if(str.equals("addp")) {
				System.out.print("[command : addp] > Digite o nome da peça primitiva: ");
				String part = in.next();

				System.out.print("[command : addp] > Tem sub-peças? [S/N] ");
				String s = in.next();

				if(s.toLowerCase(Locale.ROOT).equals("s")){
					System.out.print("[command : addp] > Digite suas sub-peças (separando por ,): ");
					String q = in.next();
					String[] sp = q.split(",");

					ArrayList<Part> sub = new ArrayList<>();

					for (int i = 0; i < sp.length; i++) {
						Part aux = new Part(sp[i]);
						sub.add(aux);
					}
					Part p = new Part(part, sub);
					client.addPart(p);
				}else {
					Part p = new Part(part);
					client.addPart(p);
				}
			}
			if(str.equals("getq")){
				client.getQuant();
			}
			if(str.equals("getp")){
				System.out.print("\n[command : getp] > Insira o ID da peça: ");
				String id = in.next();
				client.buscaPeca(Long.parseLong(id));
			}
			if(str.equals("showp")){
				System.out.println("\n"+client.peca);
				System.out.println("\nRepositório fonte: " + client.stub.getName());
				System.out.println("\nSub-peças " + client.peca.sub_pecas);
			}
			if(str.equals("back")){
				done = true;
				break;
			}
		}
	}
	
	public static void main(String[] args) {
	 	boolean saida = true;
		list();
		while(saida) {
			System.out.print(">");
			messages();
			if(message.equals("addserver")) {
				System.out.print("Nome: ");
				try {
					ms.criarServidor(in.next());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(message.equals("command")) {
				try {
					commands();
					processCmd();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(message.equals("lists")) {
				status();
				continue;
			}
			if(message.equals("help")){
				list();
			}
			if(message.equals("quit")) {
				System.out.println("END...");
				System.exit(0);
			}

		}
		in.close();
	}
}
