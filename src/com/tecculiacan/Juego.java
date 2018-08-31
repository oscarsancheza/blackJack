package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {

  private final Double DINERO_INICIAL = 100.0;

  private boolean volverAJugar = false;
  private List<Jugador> jugadores;
  private Croupier croupier;
  private int numeroJugadores;

  public Juego() {}

  /**
   * Método que inicia el juego blackjack, tanto el ingresar los jugadores como las reglas del
   * juego
   */
  public void iniciar() {

    this.croupier = new Croupier(new Baraja());

    if (!volverAJugar) {
      ingresarNumeroJugadores();
    }

    ingresarJugadores();
    ingresarApuestaJugadores();

    if (jugadores != null && jugadores.size() > 0) {
      getCroupier().barajarCartas();
      entregarCartasJugadores();
      mostrarResumen();
      inicioJugadores();
      getCroupier().realizarJugada(jugadoresPerdieron());
      mostrarGanadores();
      volverAJugar();
    }
  }

  /**
   * Método que pregunta al usuario si desea volver a jugar.
   */
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
      volverAJugar = true;
      iniciar();
    } else {
      JOptionPane.showMessageDialog(null, "Termino el Juego...", "Blackjack", JOptionPane.PLAIN_MESSAGE);
      System.exit(1);
    }
  }

  /**
   * Método que pide la apuesta al usuario de los jugadores
   */
  private void ingresarApuestaJugadores() {
    if (jugadores != null && jugadores.size() > 0) {

      List<Jugador> indicesJugadores = new ArrayList<>();
      boolean esErrror;

      for (Jugador jugador : this.jugadores) {

        if (volverAJugar) {
          jugador.setApuesta(0.0);
          jugador.setPuntos(0);
          jugador.setCartas(new ArrayList<>());
        }

        if (jugador.getDinero() <= 0) {
          indicesJugadores.add(jugador);
          esErrror = false;
        } else {
          esErrror = jugador.apostar();
        }

        if (esErrror) {
          indicesJugadores.add(jugador);
        }
      }
      eliminarJugadores(indicesJugadores);
    }
  }

  /**
   * Método que elimina jugadores
   *
   * @param indicesJugadores indice de los jugadores que seran eliminados
   */
  private void eliminarJugadores(List<Jugador> indicesJugadores) {
    if (indicesJugadores != null && !indicesJugadores.isEmpty()) {
      for (Jugador jugador : indicesJugadores) {
        if (jugador != null && jugadores != null && !jugadores.isEmpty()) {
          this.jugadores.remove(jugador);
        }
      }
    }
  }

  /** Método Recursivo que pregunta al usuario el nombre de todos los jugadores. */
  private void ingresarJugadores() {
    Jugador jugador;

    if (!volverAJugar) {
      jugadores = new ArrayList<>();

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
          jugador.setDinero(DINERO_INICIAL);
          jugadores.add(jugador);
        } else {
          JOptionPane.showMessageDialog(
              null, "ingresar un nombre correcto", "Blackjack", JOptionPane.INFORMATION_MESSAGE);
          ingresarJugadores();
        }
      }
    }
    croupier.setNombre(Croupier.NOMBRE);
  }

  /**
   * Método que entrega dos cartas a cada jugador incluyendo Croupier
   */
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

  /**
   * Método que pregunta al usuario por pantalla cuanto jugadores tendrá el juego
   */
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
      JOptionPane.showMessageDialog(null, "Termino el Juego...", "Blackjack", JOptionPane.PLAIN_MESSAGE);
      System.exit(1);
    }
  }

  /**
   * Método que imprime los datos de los jugadores tanto nombre como sus cartas
   */
  private void mostrarResumen() {
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

  /**
   * Método que inicia la estrategia para los jugadores
   */
  private void inicioJugadores() {
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

  /**
   * Método que recorre a todos los jugadores para imprimir quien fue el que gano al croupier
   */
  private void mostrarGanadores() {
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
                .append("\nDinero:")
                .append(jugador.getDinero())
                .append("\ngano:")
                .append(0)
                .append("\n\n");
          } else if (jugador.puntos > croupier.puntos || croupier.getPuntos() > 21) {
            jugador.gano();
            mensaje
                .append(" -- gano partida --\n")
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
              .append(" -- perdio partida --\n")
              .append(jugador.obtenerDatosgenerales())
              .append("\nDinero:")
              .append(jugador.getDinero())
              .append("\nPerdio:")
              .append(jugador.getApuesta())
              .append("\n\n");
        }
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
