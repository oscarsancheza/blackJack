package com.tecculiacan;

import java.util.ArrayList;
import java.util.List;

public class Baraja {

  private List<Carta> cartas;

  public Baraja() {
    construirBaraja();
  }

  /**
   * Metodo que crea la baraja de 52 cartas, mediante los tipos y figuras de cartas
   */
  public void construirBaraja() {
    this.cartas = new ArrayList<>();
    Carta carta;

    for (int a = 0; a < Carta.TIPOS.length; a++) {
      for (int b = 0; b < Carta.CARTAS.length; b++) {
        carta = new Carta(Carta.CARTAS[b], Carta.TIPOS[a]);
        this.cartas.add(carta);
      }
    }
  }

  public List<Carta> getCartas() {
    return cartas;
  }
}
