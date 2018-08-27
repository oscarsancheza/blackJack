package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {

  private final Double montoDinero = 100.0;

  private List<Jugador> jugadores;
  private Coupier coupier;
  private int numeroJugadores;
  private Baraja baraja;

  public Juego() {}

  public void ingresarJugadores() {

    jugadores = new ArrayList<>();
    Jugador jugador;
    for (int x = 0; x < numeroJugadores; x++) {
      String nombre =
          JOptionPane.showInputDialog(
              null, "ingrese su nombre:", "Información Jugadores", JOptionPane.QUESTION_MESSAGE);

      jugador = new Jugador();
      jugador.setNombre(nombre);

      jugadores.add(jugador);
    }
  }

  public void ingresarNumeroJugadores() {
    String[] numeroJugadoresParaSeleccionar = new String[Jugador.MAX_JUGADORES];

    for (int x = 0; x < Jugador.MAX_JUGADORES; x++) {
      numeroJugadoresParaSeleccionar[x] = "" + (x + 1);
    }

    String resultadoSeleccionado =
        (String)
            JOptionPane.showInputDialog(
                new JFrame(),
                "Seleccione el numero de jugadores",
                "Jugadores",
                JOptionPane.QUESTION_MESSAGE,
                null,
                numeroJugadoresParaSeleccionar,
                "1");

    this.numeroJugadores = Integer.parseInt(resultadoSeleccionado);
  }

  public Double getMontoDinero() {
    return montoDinero;
  }

  public List<Jugador> getJugadores() {
    return jugadores;
  }

  public void setJugadores(List<Jugador> jugadores) {
    this.jugadores = jugadores;
  }

  public Coupier getCoupier() {
    return coupier;
  }

  public void setCoupier(Coupier coupier) {
    this.coupier = coupier;
  }

  public int getNumeroJugadores() {
    return numeroJugadores;
  }

  public void setNumeroJugadores(int numeroJugadores) {
    this.numeroJugadores = numeroJugadores;
  }

  public Baraja getBaraja() {
    return baraja;
  }

  public void setBaraja(Baraja baraja) {
    this.baraja = baraja;
  }
}

