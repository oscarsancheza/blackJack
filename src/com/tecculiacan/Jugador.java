package com.tecculiacan;

import javax.swing.*;
import java.util.List;

public abstract class Jugador {

  public static final int MAX_JUGADORES = 3;

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
      mensaje += "\nEl total de los puntos se paso de 21, la jugada finalizo";
      JOptionPane.showMessageDialog(null, mensaje, "blackjack", JOptionPane.INFORMATION_MESSAGE);
    } else if (this.puntos == 21) {
      JOptionPane.showMessageDialog(null, mensaje + "\nla jugada finalizo", "blackjack", JOptionPane.INFORMATION_MESSAGE);
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
