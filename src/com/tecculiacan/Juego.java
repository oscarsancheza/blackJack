package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {
  private List<Jugador> jugadores;
  private Croupier croupier;
  private int numeroJugadores;

  public Juego() {}

  public void iniciar() {

    this.croupier = new Croupier(new Baraja());

    ingresarNumeroJugadores();
    ingresarJugadores();
    getCroupier().barajarCartas();
    entregarCartasJugadores();

    mostrarResumen();
    inicioJugadores();
    getCroupier().realizarJugada(jugadoresPerdieron());
    mostrarGanadores();

    volverAJugar();
  }

  private void volverAJugar() {
    String[] posiblesRespuestas = {"Si", "No"};

    String resultadoSeleccionado =
        (String)
            JOptionPane.showInputDialog(
                new JFrame(),
                "¿Volver a jugar?",
                "Blackjack",
                JOptionPane.QUESTION_MESSAGE,
                null,
                posiblesRespuestas,
                "1");
    if (resultadoSeleccionado != null && resultadoSeleccionado.equals("Si")) {
      iniciar();
    } else {
      System.exit(1);
    }
  }

  public void ingresarJugadores() {
    jugadores = new ArrayList<>();
    Jugador jugador;

    for (int x = 0; x < numeroJugadores; x++) {
      String nombre =
          JOptionPane.showInputDialog(
              null,
              "Ingrese el nombre del jugador " + (x + 1) + ":",
              "Información jugadores",
              JOptionPane.QUESTION_MESSAGE);

      if (nombre != null && !nombre.isEmpty()) {
        jugador = new Persona();
        jugador.setNombre(nombre);
        jugadores.add(jugador);
      } else {
        JOptionPane.showMessageDialog(
            null, "ingresar un nombre correcto", "Blackjack", JOptionPane.INFORMATION_MESSAGE);
        ingresarJugadores();
      }
    }

    croupier.setNombre(Croupier.NOMBRE);
  }

  public void entregarCartasJugadores() {
    if (jugadores != null && !jugadores.isEmpty()) {
      List<Carta> cartas;
      for (Jugador jugador : jugadores) {
        cartas = new ArrayList<>();
        cartas.add(croupier.entregarCarta());
        cartas.add(croupier.entregarCarta());
        jugador.setCartas(cartas);
      }

      cartas = new ArrayList<>();
      cartas.add(croupier.entregarCarta());
      cartas.add(croupier.entregarCarta());
      croupier.setCartas(cartas);
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
    if (resultadoSeleccionado != null) {
      this.numeroJugadores = Integer.parseInt(resultadoSeleccionado);
    } else {
      System.exit(1);
    }
  }

  public void mostrarResumen() {
    if (this.jugadores != null && !this.jugadores.isEmpty()) {

      String resumen = "";
      for (Jugador jugador : this.jugadores) {
        jugador.validarAs();
        jugador.setPuntos(jugador.sumarPuntos());
        resumen += jugador.obtenerDatosgenerales() + "\n\n";
      }

      resumen += croupier.obtenerDatos();

      JOptionPane.showMessageDialog(null, resumen, "Blackjack", JOptionPane.INFORMATION_MESSAGE);
      System.out.println(resumen);
    }
  }

  public boolean jugadoresPerdieron() {
    if (jugadores != null && !jugadores.isEmpty()) {
      int totalPerdieron = 0;
      for (Jugador jugador : jugadores) {
        if (jugador.getPuntos() > 21) {
          totalPerdieron += 1;
        }
      }
      return totalPerdieron == jugadores.size();
    }

    return false;
  }

  public void inicioJugadores() {
    if (this.jugadores != null && !this.jugadores.isEmpty()) {
      for (Jugador jugador : this.jugadores) {
        if (jugador.getPuntos() == 21) {
          jugador.setPuntos(21);
          JOptionPane.showMessageDialog(
              null,
              "-- Tienes blackjack --" + "\n" + jugador.obtenerDatosgenerales(),
              "blackjack",
              JOptionPane.INFORMATION_MESSAGE);
        } else {

          jugador.pedirCarta(this.croupier);
        }
      }
    }
  }

  public void mostrarGanadores() {
    if (jugadores != null && !jugadores.isEmpty()) {
      StringBuilder mensaje = new StringBuilder();
      for (Jugador jugador : jugadores) {
        if (jugador.puntos == 21
            || (jugador.puntos < 21 && croupier.getPuntos() > 21)
            || (jugador.puntos < 21 && jugador.puntos >= croupier.puntos)) {
          if (jugador.puntos == croupier.puntos) {
            mensaje
                .append(" -- empato con croupier --\n")
                .append(jugador.obtenerDatosgenerales())
                .append("\n\n");
          } else if (jugador.puntos > croupier.puntos || croupier.getPuntos() > 21) {
            mensaje
                .append(" -- gano partida --\n")
                .append(jugador.obtenerDatosgenerales())
                .append("\n\n");
          }
        } else {
          mensaje
              .append(" -- perdio partida --\n")
              .append(jugador.obtenerDatosgenerales())
              .append("\n\n");
        }
      }

      mensaje.append(croupier.obtenerDatosgenerales());
      JOptionPane.showMessageDialog(
          null, mensaje.toString(), "Blackjack", JOptionPane.INFORMATION_MESSAGE);
    }
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
