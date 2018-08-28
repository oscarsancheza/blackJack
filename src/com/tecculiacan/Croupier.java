package com.tecculiacan;

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

  public Baraja getBaraja() {
    return baraja;
  }

  public void setBaraja(Baraja baraja) {
    this.baraja = baraja;
  }
}
