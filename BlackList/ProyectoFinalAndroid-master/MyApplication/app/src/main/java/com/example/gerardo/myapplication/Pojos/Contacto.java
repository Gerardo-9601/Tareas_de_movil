package com.example.gerardo.myapplication.Pojos;

/**
 * Created by Gerardo on 11/03/2018.
 */

public class Contacto {

    private int ID;
    private String Contacto;
    private String Telefono;
    private String BlockLlamadas;
    private String Blockmensajes;

    public Contacto() {}
    public Contacto(String contacto, String telefono, String blockLlamadas, String blockmensajes) {
        Contacto = contacto;
        Telefono = telefono;
        BlockLlamadas = blockLlamadas;
        Blockmensajes = blockmensajes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getBlockLlamadas() {
        return BlockLlamadas;
    }

    public void setBlockLlamadas(String blockLlamadas) {
        BlockLlamadas = blockLlamadas;
    }

    public String getBlockmensajes() {
        return Blockmensajes;
    }

    public void setBlockmensajes(String blockmensajes) {
        Blockmensajes = blockmensajes;
    }
}
