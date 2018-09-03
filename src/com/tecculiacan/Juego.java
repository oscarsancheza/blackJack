package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {
  private List<Jugador> jugadores;
  private Croupier croupier;
  private int numeroJugadores;

  public Juego() {}

  /**
   * Método que inicia el juego blackjack, tanto el ingresar los jugadores como las reglas del juego
   */
  public void iniciar() {

    boolean terminarJuego = false;
    this.croupier = new Croupier(new Baraja());
    ingresarNumeroJugadores();
    ingresarJugadores();

    while (!terminarJuego) {
      ingresarApuestaJugadores();
      getCroupier().barajarCartas();
      entregarCartasJugadores();
      mostrarResumen();
      inicioJugadores();
      getCroupier().realizarJugada(jugadoresPerdieron());
      mostrarGanadores();
      validarDineroJugadores();
      if (jugadores == null || jugadores.isEmpty()) {
        terminarJuego = true;
      } else {
        terminarJuego = !volverAJugar();
        this.croupier = new Croupier(new Baraja());
      }
    }
  }

  /**
   * Método que pregunta al usuario si desea volver a jugar.
    * @return true si desea volver a jugar
   */
  private boolean volverAJugar() {
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

    return (resultadoSeleccionado != null && resultadoSeleccionado.equals("Si"));
  }

  /** Método que pide la apuesta al usuario de los jugadores */
  private void ingresarApuestaJugadores() {
    if (jugadores != null && jugadores.size() > 0) {
      for (Jugador jugador : this.jugadores) {
        jugador.apostar();
      }
    }
  }

  /**
   * Método que valida si el jugador puede seguir jugando en base a su dinero
   */
  private void validarDineroJugadores() {
    if (this.jugadores != null && !this.jugadores.isEmpty()) {
      List<Jugador> jugadoresSinDinero = new ArrayList<>();
      for (Jugador jugador : jugadores) {
        if (jugador.getDinero() <= 0) {
          jugadoresSinDinero.add(jugador);
        }
      }
      eliminarJugadores(jugadoresSinDinero);
    }
  }

  /**
   * Método que elimina jugadores
   *
   * @param jugadores jugadores que seran eliminados
   */
  private void eliminarJugadores(List<Jugador> jugadores) {
    if (jugadores != null && !jugadores.isEmpty()) {
      for (Jugador jugador : jugadores) {
        if (jugador != null) {
          this.jugadores.remove(jugador);
        }
      }
    }
  }

  /** Método que pregunta al usuario el nombre de todos los jugadores. */
  private void ingresarJugadores() {
    Jugador jugador;
    jugadores = new ArrayList<>();

    for (int x = 0; x < numeroJugadores; x++) {
      boolean nombreCorrecto = false;
      while (!nombreCorrecto) {
        String nombre =
            JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre del jugador " + (x + 1) + ":",
                "Información jugadores",
                JOptionPane.QUESTION_MESSAGE);

        if (nombre != null && !nombre.isEmpty()) {
          jugador = new Persona();
          jugador.setNombre(nombre);
          jugador.setDinero(Jugador.DINERO_INICIAL);
          jugadores.add(jugador);
          nombreCorrecto = true;
        } else {
          JOptionPane.showMessageDialog(
              null, "Ingresar un nombre correcto", "Blackjack", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
  }

  /** Método que entrega dos cartas a cada jugador incluyendo Croupier */
  private void entregarCartasJugadores() {
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

  /** Método que pregunta al usuario por pantalla cuantos jugadores tendrá el juego */
  private void ingresarNumeroJugadores() {
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
      JOptionPane.showMessageDialog(
          null, "Termino el Juego...", "Blackjack", JOptionPane.PLAIN_MESSAGE);
      System.exit(1);
    }
  }

  /** Método que imprime los datos de los jugadores tanto nombre como sus cartas */
  private void mostrarResumen() {
    if (this.jugadores != null && !this.jugadores.isEmpty()) {

      StringBuilder resumen = new StringBuilder();
      for (Jugador jugador : this.jugadores) {
        jugador.validarAs();
        jugador.setPuntos(jugador.sumarPuntos());
        resumen.append(jugador.obtenerDatosgenerales()).append("\n\n");
      }

      resumen.append(croupier.obtenerDatos());

      JOptionPane.showMessageDialog(
          null, resumen.toString(), "Blackjack", JOptionPane.INFORMATION_MESSAGE);
      System.out.println(resumen);
    }
  }

  /**
   * Método que retorno si todos los jugadores se pasaron de 21 puntos
   *
   * @return Si todos los jugadores se pasaron de 21 puntos
   */
  private boolean jugadoresPerdieron() {
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

  /** Método que inicia la estrategia para los jugadores */
  private void inicioJugadores() {
    if (this.jugadores != null && !this.jugadores.isEmpty()) {
      for (Jugador jugador : this.jugadores) {
        if (jugador.getPuntos() == 21) {
          jugador.setPuntos(21);
          JOptionPane.showMessageDialog(
              null,
              "-- Tienes blackjack --" + "\n" + jugador.obtenerDatosgenerales(),
              "Blackjack",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          jugador.pedirCarta(this.croupier);
        }
      }
    }
  }

  /** Método que recorre a todos los jugadores para imprimir quien fue el que gano al croupier */
  private void mostrarGanadores() {
    if (jugadores != null && !jugadores.isEmpty()) {
      StringBuilder mensaje = new StringBuilder();
      for (Jugador jugador : jugadores) {
        if (jugador.puntos == 21
            || (jugador.puntos < 21 && croupier.getPuntos() > 21)
            || (jugador.puntos < 21 && jugador.puntos >= croupier.puntos)) {
          if (jugador.puntos == croupier.puntos) {
            mensaje
                .append(" -- Empato con croupier --\n")
                .append(jugador.obtenerDatosgenerales())
                .append("\nDinero:")
                .append(jugador.getDinero())
                .append("\ngano:")
                .append(0)
                .append("\n\n");
          } else if (jugador.puntos > croupier.puntos || croupier.getPuntos() > 21) {
            jugador.gano();
            mensaje
                .append(" -- Gano partida --\n")
                .append(jugador.obtenerDatosgenerales())
                .append("\nDinero:")
                .append(jugador.getDinero())
                .append("\ngano:")
                .append(jugador.getApuesta())
                .append("\n\n");
          }
        } else {
          jugador.perdio();
          mensaje
              .append(" -- Perdio partida --\n")
              .append(jugador.obtenerDatosgenerales())
              .append("\nDinero:")
              .append(jugador.getDinero())
              .append("\nPerdio:")
              .append(jugador.getApuesta())
              .append("\n\n");
        }
        jugador.iniciarJugador();
      }

      mensaje.append(croupier.obtenerDatosgenerales());
      JOptionPane.showMessageDialog(
          null, mensaje.toString(), "Blackjack", JOptionPane.INFORMATION_MESSAGE);
      System.out.println(mensaje);
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
