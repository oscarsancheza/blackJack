package com.tecculiacan;

import java.util.ArrayList;
import java.util.List;

public class Baraja {

  private List<Carta> cartas;

  public Baraja() {
    construirBaraja();
  }

  public void construirBaraja() {
    this.cartas = new ArrayList<>();
    Carta carta;

    for (int a = 0; a < Carta.tipos.length; a++) {
      for (int b = 0; b < Carta.cartas.length; b++) {
        carta = new Carta(Carta.cartas[b], Carta.tipos[a]);
        this.cartas.add(carta);
      }
    }
  }

  public List<Carta> getCartas() {
    return cartas;
  }
}
