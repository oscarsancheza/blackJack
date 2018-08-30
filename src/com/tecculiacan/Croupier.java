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


  /**
   *  Ordena una lista de cartas de forma aleatoria
   *
   */
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

  /**
   * Método que saca una carta de la baraja
   *
   * @return  una carta de la baraja
   */
  public Carta entregarCarta() {
    Carta carta = null;

    if (this.baraja.getCartas() != null && !this.baraja.getCartas().isEmpty()) {
      carta = this.baraja.getCartas().remove(0);
    }

    return carta;
  }

  /**
   * Método que realiza la jugada del croupier. Saca cartas mientras no sobrepase los 17 puntos
   * y valida si los jugadores pasaron los 21 entonces no realiza nada.
   *
   * @param jugadoresPerdieron
   *        Define si todos los jugadores se pasaron de 21
   */
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

  /**
   * Método que muestra la carta inicial del croupier
   *
   * @return La informacion del croupier, nomas mostrando una carta
   */
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
