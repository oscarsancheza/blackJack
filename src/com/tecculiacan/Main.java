package com.tecculiacan;

public class Main {

  public static void main(String[] args) {

    Juego juego = new Juego(new Croupier(new Baraja()));
    juego.iniciar();

    System.exit(0);
  }
}
