package br.rmi.Interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public class Part implements Serializable {
    public int id;
    public String nome_p;
    public ArrayList<Part> sub_pecas = new ArrayList<>();

    public Part(String nome_p, ArrayList<Part> sp){
        this.id = nome_p.hashCode();
        this.nome_p = nome_p;

        for (int i = 0; i < sp.size(); i++) {
            this.sub_pecas.add(sp.get(i));
        }
    }

    public Part(String nome_p) {
        this.id = nome_p.hashCode();
        this.nome_p = nome_p;
    }

    public Part(String nome_p, Part sp) {
        this.id = nome_p.hashCode();
        this.nome_p = nome_p;
        this.sub_pecas.add(sp);
    }

    public long getId(){
        return this.id;
    }

    public String getNome_p(){
        return this.nome_p;
    }

    public String toString(){
        return "[Nome:" + this.nome_p + "\n  ID:" + this.id + " ~ " + this.sub_pecas.size()  +"]";
    }
}
