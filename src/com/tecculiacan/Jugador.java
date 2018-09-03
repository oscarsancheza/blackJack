package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {

  public static final Double DINERO_INICIAL = 100.0;
  public static final int MAX_JUGADORES = 3;
  protected String nombre;
  private Double apuesta;
  private Double dinero;
  protected List<Carta> cartas;
  protected int puntos;

  public Jugador() {}

  /**
   * Método que pide al usuario las apuestas de los jugadores
   */
  public void apostar() {
    boolean aposto = false;
    while (!aposto) {
      String apuestaIngresada =
          JOptionPane.showInputDialog(
              null,
              "Jugador " + nombre + "\nTienes disponible:" + this.dinero + "\nApostar:",
              "Apostar",
              JOptionPane.QUESTION_MESSAGE);

      if (apuestaIngresada != null
          && !apuestaIngresada.isEmpty()
          && Utils.esNumeroDecimal(apuestaIngresada)
          && Double.valueOf(apuestaIngresada) > 0) {
        if (Double.valueOf(apuestaIngresada) <= this.dinero) {
          this.apuesta = Double.valueOf(apuestaIngresada);
          aposto = true;
        } else {
          JOptionPane.showMessageDialog(
              null,
              "No cuenta con ese dinero para apostar",
              "BlackJack",
              JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(
            null, "Ingrese un cantidad valida", "Blackjack", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Método que realiza la estrategia del jugador, preguntando por pantalla si el jugador desea mas
   * cartas, validando si el jugador se paso de 21 o por el contrario tiene un puntaje de 21
   *
   * @param croupier El parametro Croupier se manda para poder repartir mas cartas al jugador
   */
  public void pedirCarta(Croupier croupier) {
    boolean terminojugada = false;

    while (!terminojugada) {
      String mensaje = this.obtenerDatosgenerales();

      if (this.puntos > 21) {
        mensaje += "\n\nEl total de los puntos se paso de 21, la jugada termino";
        JOptionPane.showMessageDialog(null, mensaje, "Blackjack", JOptionPane.INFORMATION_MESSAGE);
        terminojugada = true;
      } else if (this.puntos == 21) {
        JOptionPane.showMessageDialog(
            null, mensaje + "\n\nLa jugada termino", "Blackjack", JOptionPane.INFORMATION_MESSAGE);
        terminojugada = true;
      } else {
        String[] respuesta = {"Si", "No"};
        String resultadoSeleccionado =
            (String)
                JOptionPane.showInputDialog(
                    new JFrame(),
                    mensaje + "\nPedir una carta mas:",
                    "BlackJack",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    respuesta,
                    "1");
        if (resultadoSeleccionado.equals("Si")) {
          this.cartas.add(croupier.entregarCarta());
          validarAs();
          this.puntos = sumarPuntos();
        } else if (resultadoSeleccionado.equals("No")) {
          mensaje += "\n\nLa jugada termino";
          JOptionPane.showMessageDialog(
              null, mensaje, "Blackjack", JOptionPane.INFORMATION_MESSAGE);
          terminojugada = true;
        }
      }
    }
  }

  /**
   * Método que valida el valor del As que es 1 por default, dependiendo de l puntaje que tiene el
   * jugador, si su puntaje mas 10 no pasa de 21 se cambia el valor a 11.
   */
  public void validarAs() {
    if (this.cartas != null && !this.cartas.isEmpty()) {
      int total = sumarPuntos();
      for (Carta carta : cartas) {
        if (carta.isEsAs() && total > 21) {
          carta.setValor(1);
          total = sumarPuntos();
        } else if (carta.isEsAs() && (total + 10) <= 21) {
          carta.setValor(11);
          total = sumarPuntos();
        }
      }
    }
  }

  /**
   * Método que regresa las cartas que tiene un jugador
   *
   * @return retorna las cartas que tiene un jugador
   */
  public String obtenerDatosgenerales() {
    StringBuilder mensaje = new StringBuilder("Jugador " + nombre + "\n" + "Cartas:\n");
    if (cartas != null && !cartas.isEmpty()) {
      for (Carta carta : cartas) {
        mensaje
            .append("- ")
            .append(carta.getNombre())
            .append(" ")
            .append(carta.getTipo())
            .append("\n");
      }
    }

    mensaje.append("puntos:").append(puntos);

    return mensaje.toString();
  }

  /**
   * Método que obtiene el puntaje de las cartas de un jugador
   *
   * @return el total de puntos que tiene un jugador
   */
  public int sumarPuntos() {
    int total = 0;
    if (cartas != null && !cartas.isEmpty()) {
      for (Carta carta : this.cartas) {
        total += carta.getValor();
      }
    }
    return total;
  }

  public void gano() {
    this.dinero += this.apuesta;
  }

  public void perdio() {
    this.dinero -= apuesta;
  }

  public void iniciarJugador() {
    this.puntos = 0;
    this.setCartas(new ArrayList<>());
    this.setApuesta(0.0);
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

  public int getPuntos() {
    return puntos;
  }

  public void setPuntos(int puntos) {
    this.puntos = puntos;
  }
}
