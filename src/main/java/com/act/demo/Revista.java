package com.act.demo;

import java.util.HashSet;

public class Revista {

    private String revista;
    private HashSet<String> articulos;

    public Revista(String revista, HashSet<String> articulos) {
        this.revista = revista;
        this.articulos = articulos;
    }

    public String getRevista() {
        return revista;
    }

    public HashSet<String> getArticulos() {
        return articulos;
    }
}