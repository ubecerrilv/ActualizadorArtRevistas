package com.act.demo;

import java.util.ArrayList;
import java.util.HashSet;

public class Revista {

    private String revista;
    private ArrayList<String> articulos;

    public Revista(String revista, ArrayList<String> articulos) {
        this.revista = revista;
        this.articulos = articulos;
    }

    public String getRevista() {
        return revista;
    }

    public ArrayList<String> getArticulos() {
        return articulos;
    }
}