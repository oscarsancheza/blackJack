package com.tecculiacan;

import javax.swing.*;
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
   *
   * @return verdadero si ocurrio un error
   */
  public boolean apostar() {
    boolean esError = false;

    String apuestaIngresada =
        JOptionPane.showInputDialog(
            null,
            "Jugador " + nombre + "\nTienes disponible:" + this.dinero + "\nApostar:",
            "Apostar",
            JOptionPane.QUESTION_MESSAGE);

    if (apuestaIngresada != null) {
      if (!apuestaIngresada.isEmpty()
          && Utils.esNumeroDecimal(apuestaIngresada)
          && Double.valueOf(apuestaIngresada) > 0) {
        if (Double.valueOf(apuestaIngresada) <= this.dinero) {
          this.apuesta = Double.valueOf(apuestaIngresada);
        } else {
          JOptionPane.showMessageDialog(
              null,
              "no cuenta con ese dinero para apostar",
              "BlackJack",
              JOptionPane.INFORMATION_MESSAGE);
          apostar();
        }
      } else {
        JOptionPane.showMessageDialog(
            null, "ingresa un cantidad valida", "Blackjack", JOptionPane.ERROR_MESSAGE);
        apostar();
      }
    } else {
      esError = true;
    }
    return esError;
  }

  /**
   * Método recursivo que realiza la estrategia del jugador, preguntando por pantalla si el jugador
   * desea mas cartas, validando si el jugador se paso de 21 o por el contrario tiene un puntaje de
   * 21
   *
   * @param croupier El parametro Croupier se manda para poder repartir mas cartas al jugador
   */
  public void pedirCarta(Croupier croupier) {
    String mensaje = this.obtenerDatosgenerales();

    if (this.puntos > 21) {
      mensaje += "\nEl total de los puntos se paso de 21, la jugada finalizo";
      JOptionPane.showMessageDialog(null, mensaje, "blackjack", JOptionPane.INFORMATION_MESSAGE);
    } else if (this.puntos == 21) {
      JOptionPane.showMessageDialog(
          null, mensaje + "\nla jugada finalizo", "blackjack", JOptionPane.INFORMATION_MESSAGE);
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

      if (resultadoSeleccionado != null) {
        if (resultadoSeleccionado.equals("Si")) {
          this.cartas.add(croupier.entregarCarta());
          validarAs();
          this.puntos = sumarPuntos();
          pedirCarta(croupier);
        } else if (resultadoSeleccionado.equals("No")) {
          mensaje += "\nLa jugada termino";
          JOptionPane.showMessageDialog(
              null, mensaje, "blackjack", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        pedirCarta(croupier);
      }
    }
  }

  /**
   * funcion que valida el valor del As que es 1 por default, dependiendo de l puntaje que tiene el
   * jugador, si su puntaje mas 10 no pasa de 21 se cambia el valor a 11.
   */
  public void validarAs() {
    if (this.cartas != null && !this.cartas.isEmpty()) {
      boolean esAs = false;
      int total = 0;
      for (Carta carta : cartas) {
        if (carta.isEsAs()) {
          esAs = true;
        }
        total += carta.getValor();
      }

      if (esAs && (total + 10) <= 21) {
        cambiarValorAs();
      }
    }
  }

  /**
   * Método que cambia el valor del AS a 11
   */
  private void cambiarValorAs() {
    if (this.cartas != null && !this.cartas.isEmpty()) {
      for (Carta carta : cartas) {
        if (carta.isEsAs() && carta.getValor() == 1) {
          carta.setValor(11);
          break;
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
    String mensaje = "Jugador " + nombre + "\n" + "Cartas:\n";
    if (cartas != null && !cartas.isEmpty()) {
      for (Carta carta : cartas) {
        mensaje += carta.getNombre() + " " + carta.getTipo() + "\n";
      }
    }

    mensaje += "puntos:" + puntos;

    return mensaje;
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
