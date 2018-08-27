package com.tecculiacan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baraja {

  private List<Carta> cartas;

  public Baraja() {}

  public List<Carta> getCarta() {
    return cartas;
  }

  public void setCarta(List<Carta> carta) {
    this.cartas = carta;
  }

  public Baraja construirBaraja() {
    cartas = new ArrayList<>();
    Carta carta;

    for (int a = 0; a < Carta.tipos.length; a++) {
      for (int b = 0; b < Carta.cartas.length; b++) {
        carta = new Carta(Carta.cartas[b], Carta.tipos[a]);
        cartas.add(carta);
      }
    }
    return new Baraja();
  }

  public void barajarCartas() {
    List<Carta> barajaAleatoria = new ArrayList<>();
    int indice;

    while (this.cartas.size() > 0) {
      Random aleatorio = new Random();
      indice = aleatorio.nextInt(this.cartas.size());
      Carta cartaAleatoria = this.cartas.remove(indice);
      barajaAleatoria.add(cartaAleatoria);
    }
    this.cartas.addAll(barajaAleatoria);
  }
}
