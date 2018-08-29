package com.tecculiacan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Croupier extends Jugador {

  public static final String NOMBRE = "Croupier";

  private Baraja baraja;

  public Croupier(Baraja baraja) {
    this.baraja = baraja;
  }

  public void barajarCartas() {
    List<Carta> barajaAleatoria = new ArrayList<>();
    int indice;

    while (this.baraja.getCartas().size() > 0) {
      Random aleatorio = new Random();
      indice = aleatorio.nextInt(this.baraja.getCartas().size());
      Carta cartaAleatoria = this.baraja.getCartas().remove(indice);
      barajaAleatoria.add(cartaAleatoria);
    }
    this.baraja.getCartas().addAll(barajaAleatoria);
  }

  public Carta entregarCarta() {
    Carta carta = null;

    if (this.baraja.getCartas() != null && !this.baraja.getCartas().isEmpty()) {
      carta = this.baraja.getCartas().remove(0);
    }

    return carta;
  }

  public void realizarJugada(boolean jugadoresPerdieron) {
    validarAs();
    puntos = sumarPuntos();
    if (!jugadoresPerdieron) {
      while (puntos < 17) {
        cartas.add(entregarCarta());
        validarAs();
        puntos = sumarPuntos();
      }
    }
  }

  public String obtenerDatos() {
    String mensaje = nombre + "\n" + "Cartas:\n";
    if (cartas != null && !cartas.isEmpty()) {
      mensaje +=
          cartas.get(0).getNombre()
              + " "
              + cartas.get(0).getTipo()
              + "\npuntos:"
              + cartas.get(0).getValor();
    }

    return mensaje;
  }

  public Baraja getBaraja() {
    return baraja;
  }

  public void setBaraja(Baraja baraja) {
    this.baraja = baraja;
  }
}
