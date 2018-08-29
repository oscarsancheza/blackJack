package com.tecculiacan;

import javax.swing.*;
import java.util.List;

public abstract class Jugador {

  public static final int MAX_JUGADORES = 2;

  protected String nombre;
  private Double apuesta;
  private Double dinero;
  protected List<Carta> cartas;
  protected int puntos;
  private boolean gano;

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
      if (Double.valueOf(apuestaIngresada) > 0 && Double.valueOf(apuestaIngresada) <= this.dinero) {
        this.apuesta = Double.valueOf(apuestaIngresada);
      } else {
        apostar();
      }
    } else {
      apostar();
    }
  }

  public void pedirCarta(Croupier croupier) {
    String mensaje = this.obtenerDatosgenerales();

    if (this.puntos > 21) {
      mensaje += "\nEl total de los puntos se paso de 21";
      JOptionPane.showMessageDialog(null, mensaje, "blackjack", JOptionPane.INFORMATION_MESSAGE);
    } else if (this.puntos == 21) {
      JOptionPane.showMessageDialog(null, mensaje, "blackjack", JOptionPane.INFORMATION_MESSAGE);
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
          this.puntos = sumarPuntos();
          pedirCarta(croupier);
        } else if (resultadoSeleccionado.equals("No")) {
          mensaje += "\nLa jugada ha terminado";
          JOptionPane.showMessageDialog(
              null, mensaje, "blackjack", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        pedirCarta(croupier);
      }
    }
  }

  public boolean validarBlackJack(List<Carta> cartas) {
    if (cartas != null && cartas.size() > 1) {

      int valorCartaUno = cartas.get(0).getValor();
      int valorCartaDos = cartas.get(1).getValor();

      if ((valorCartaUno == 10 && valorCartaDos == 11)
          || (valorCartaUno == 11 && valorCartaDos == 10)) {
        return true;
      }
    }
    return false;
  }

  public String obtenerDatosgenerales() {
    String mensaje = "Jugador " + nombre + "\n" + "Cartas:\n";
    if (cartas != null && !cartas.isEmpty()) {
      for (Carta carta : cartas) {
        mensaje += carta.getNombre() + " " + carta.getTipo() + "\n";
      }
    }

    if (!mensaje.isEmpty()) {
      mensaje += "puntos:" + puntos;
    }

    return mensaje;
  }

  public int sumarPuntos() {
    int total = 0;

    if (cartas != null && !cartas.isEmpty()) {
      for (Carta carta : this.cartas) {
        total += carta.getValor();
      }
    }
    return total;
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

  public boolean isGano() {
    return gano;
  }

  public void setGano(boolean gano) {
    this.gano = gano;
  }
}
