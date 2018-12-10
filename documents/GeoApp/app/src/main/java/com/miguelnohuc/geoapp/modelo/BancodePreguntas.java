package com.miguelnohuc.geoapp.modelo;

import java.util.ArrayList;
import java.util.List;

public class BancodePreguntas {
    private final List<Pregunta> banco;
    private int posicionActual;
    private int numElementos;


    public BancodePreguntas() {
        banco = new ArrayList<>();
        posicionActual = -1;
    }
    public int getPosicionActual(){
        return posicionActual;
    }

    public void add(Pregunta pregunta) {
        banco.add(pregunta);
        if (size() == 1) {
            posicionActual = 0;
        }
    }

    public int size() {
        return banco.size();
    }

    public Pregunta get(int posicion) {
        return banco.get(posicion);
    }

    public boolean isEmpty() {
        return banco.isEmpty();
    }

    public Pregunta next() {
        if (isEmpty()) {
            return null;
        }
        else {
            if (posicionActual == size() - 1) {
                posicionActual = 0;
            }
            else {
                posicionActual++;
            }
            return banco.get(posicionActual);
        }
    }
    public Pregunta previous (){
        if (isEmpty()){
            return null;
        }
        else{
            if (posicionActual == 0){
                posicionActual = size() -1;
            }
            else {
                posicionActual--;
            }
            return banco.get(posicionActual);
        }
    }
}
