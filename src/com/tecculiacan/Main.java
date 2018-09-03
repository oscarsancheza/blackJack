package com.tecculiacan;

import javax.swing.*;

public class Main {

  public static void main(String[] args) {

    Juego juego = new Juego();
    juego.iniciar();

    JOptionPane.showMessageDialog(null, "Termino el Juego...", "Blackjack", JOptionPane.PLAIN_MESSAGE);

    System.exit(0);
  }
}
