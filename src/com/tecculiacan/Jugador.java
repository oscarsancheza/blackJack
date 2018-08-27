package com.tecculiacan;

public class Jugador {

  public final static int MIN_JUGADORES = 1;
  public final static int MAX_JUGADORES = 5;

  private String nombre;
  private Double dinero;

  public Jugador() {
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
