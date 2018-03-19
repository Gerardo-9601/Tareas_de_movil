package com.example.gerardo.mapasregresoacasa.Pojos;

/**
 * Created by Gerardo on 17/03/2018.
 */

public class Ruta {
    private int ID;
    private String Origen;
    private String Destino;
    private int imagen;

    public Ruta(){}
    public Ruta(String origen, String destino,int imagen) {
        Origen = origen;
        Destino = destino;
        this.imagen = imagen;
    }

    public Ruta(String origen, String destino) {
        Origen = origen;
        Destino = destino;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
