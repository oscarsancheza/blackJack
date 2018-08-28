package com.tecculiacan;

public class Main {

  public static void main(String[] args) {

    Juego juego = new Juego(new Croupier(new Baraja()));

    juego.ingresarNumeroJugadores();
    juego.ingresarJugadores();
    juego.getCroupier().barajarCartas();
    juego.entregarCartasJugadores();




    System.exit(0);
  }
}
