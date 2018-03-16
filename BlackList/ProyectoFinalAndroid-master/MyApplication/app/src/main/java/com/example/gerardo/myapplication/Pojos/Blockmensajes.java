package com.example.gerardo.myapplication.Pojos;

/**
 * Created by Gerardo on 11/03/2018.
 */

public class Blockmensajes {
    private int ID;
    private String Contacto;
    private String Telefono;
    private String Mensaje;
    private String Hora;
    private String Fecha;

    public Blockmensajes(){}

    public Blockmensajes(String contacto, String telefono, String mensaje, String hora, String fecha) {
        Contacto = contacto;
        Telefono = telefono;
        Mensaje = mensaje;
        Hora = hora;
        Fecha = fecha;
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

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
