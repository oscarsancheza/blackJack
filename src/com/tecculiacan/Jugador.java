package com.tecculiacan;

import javax.swing.*;
import java.util.List;

public abstract class Jugador {

  public static final int MAX_JUGADORES = 2;

  private String nombre;
  private Double apuesta;
  private Double dinero;
  private List<Carta> cartas;

  public Jugador() {}

  public void apostar() {
    String apuestaIngresada =
        JOptionPane.showInputDialog(
            null,
            "Tienes disponible:" + this.dinero + "\nApostar:",
            "Apostar",
            JOptionPane.QUESTION_MESSAGE);

    if (apuestaIngresada != null
        && !apuestaIngresada.isEmpty()
        && Utils.esNumerico(apuestaIngresada)) {
      if (Double.valueOf(apuestaIngresada) <= this.dinero) {
        this.apuesta = Double.valueOf(apuestaIngresada);
      } else {
        apostar();
      }
    } else {
      apostar();
    }
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Double getApuesta() {
    return apuesta;
  }

  public void setApuesta(Double apuesta) {
    this.apuesta = apuesta;
  }

  public Double getDinero() {
    return dinero;
  }

  public void setDinero(Double dinero) {
    this.dinero = dinero;
  }

  public List<Carta> getCartas() {
    return cartas;
  }

  public void setCartas(List<Carta> cartas) {
    this.cartas = cartas;
  }
}
