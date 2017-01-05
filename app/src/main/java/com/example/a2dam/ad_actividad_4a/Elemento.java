package com.example.a2dam.ad_actividad_4a;

/**
 * Created by user on 03/01/2017.
 */

public class Elemento {
    private int identificador;
    private String nombre;

    public Elemento(int identificador, String nombre){
        this.identificador=identificador;
        this.nombre=nombre;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
