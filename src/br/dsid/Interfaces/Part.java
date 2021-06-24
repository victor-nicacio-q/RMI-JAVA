package br.dsid.Interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Part implements Serializable {
    public long id;
    public String nome_p;
    public ArrayList<Part> sub_pecas = new ArrayList<>();

    public Part(String nome_p, ArrayList<Part> sp){
        Random r = new Random();
        this.id = r.nextInt();
        this.nome_p = nome_p;

        for (int i = 0; i < sp.size(); i++) {
            this.sub_pecas.add(sp.get(i));
        }
    }

    public Part(String nome_p) {
        Random r = new Random();
        this.id = r.nextInt();
        this.nome_p = nome_p;
    }

    public long getId(){
        return this.id;
    }

    public String getNome_p(){
        return this.nome_p;
    }

    public String toString(){
        return "[Nome:" + this.nome_p + "\nID:" + this.id + "~" + this.sub_pecas.size()  +"]";
    }
}
