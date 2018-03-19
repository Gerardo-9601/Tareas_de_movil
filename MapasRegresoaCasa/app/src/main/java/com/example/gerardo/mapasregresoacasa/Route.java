package com.example.gerardo.mapasregresoacasa;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Gerardo on 17/03/2018.
 */
public class Route {
    public Distancia distancia;
    public Duracion duracion;
    public String direccionfinal;
    public LatLng ubicacionfin;
    public String direccioninicial;
    public LatLng ubicacioninicial;

    public List<LatLng> puntos;
}
