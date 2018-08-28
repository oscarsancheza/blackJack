package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {

  private final Double DINERO_INICIAL = 100.0;

  private List<Jugador> jugadores;
  private Croupier croupier;
  private int numeroJugadores;

  public Juego(Croupier croupier) {
    this.croupier = croupier;
  }

  public void ingresarJugadores() {

    jugadores = new ArrayList<>();
    Jugador jugador;
    for (int x = 0; x < numeroJugadores; x++) {
      String nombre =
          JOptionPane.showInputDialog(
              null, "ingrese su nombre:", "Información Jugadores", JOptionPane.QUESTION_MESSAGE);

      if (nombre != null && !nombre.isEmpty()) {
        jugador = new Persona();
        jugador.setDinero(DINERO_INICIAL);
        jugador.setNombre(nombre);
        jugador.apostar();
        jugadores.add(jugador);
      }
    }

    croupier.setNombre(Croupier.NOMBRE);
    jugadores.add(croupier);
  }

  public void entregarCartasJugadores() {
    if (jugadores != null && jugadores.size() > 1) {

      List<Carta> cartas;

      for (Jugador jugador : jugadores) {
        cartas = new ArrayList<>();
        cartas.add(croupier.entregarCarta());
        cartas.add(croupier.entregarCarta());
        jugador.setCartas(cartas);
      }
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

  public List<Jugador> getJugadores() {
    return jugadores;
  }

  public void setJugadores(List<Jugador> jugadores) {
    this.jugadores = jugadores;
  }

  public Croupier getCroupier() {
    return croupier;
  }

  public void setCroupier(Croupier croupier) {
    this.croupier = croupier;
  }

  public int getNumeroJugadores() {
    return numeroJugadores;
  }

  public void setNumeroJugadores(int numeroJugadores) {
    this.numeroJugadores = numeroJugadores;
  }
}
