package com.miguelnohuc.geoapp.modelo;

public class Pregunta {
    private int mIdResTexto;
    private boolean mVerdadera;

    public Pregunta (int idResTexto, boolean verdadera){
        mIdResTexto= idResTexto;
        mVerdadera = verdadera;

    }
    public int getmIdResTexto() {return mIdResTexto;}

    public void setmIdResTexto(int idResTexto) {mIdResTexto= idResTexto;}

    public boolean isVerdadera() {return mVerdadera;}

    public void setVerdadera(boolean verdadera) {mVerdadera = verdadera;}

    public boolean isRespuestaVerdadera() {return mVerdadera;}
}