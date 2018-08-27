package com.tecculiacan;

public class Main {

  public static void main(String[] args) {

    Juego juego = new Juego();

    juego.ingresarNumeroJugadores();
    juego.ingresarJugadores();

    Baraja baraja = new Baraja();
    juego.setBaraja(baraja);

    juego.getBaraja().construirBaraja();

    juego.getBaraja().barajarCartas();

    System.exit(0);
  }
}
