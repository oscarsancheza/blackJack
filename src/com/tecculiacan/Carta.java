package com.tecculiacan;

public class Carta {

  public static final String CARTA_AS = "As";

  public static final String[] TIPOS = {"Corazon", "Picas", "Trebol", "Diamante"};
  public static final String[] CARTAS = {
    "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Reyna", "Rey"
  };

  private String nombre;
  private String tipo;
  private int valor;
  private boolean esAs;

  public Carta(String nombre, String tipo) {
    this.nombre = nombre;
    this.tipo = tipo;
    encontrarValor(nombre);
  }

  /**
   * asigna el valor a partir del nombre de la carta
   *
   * @param nombre
   *        tipo de carta
   *
   */
  private void encontrarValor(String nombre) {
    if (nombre.equals(CARTA_AS)) {
      this.esAs = true;
      this.valor = 1;
    } else if (Utils.esNumeroEntero(nombre)) {
      int numero = Integer.parseInt(nombre);
      this.valor = numero;
    } else {
      this.valor = 10;
    }
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public int getValor() {
    return valor;
  }

  public void setValor(int valor) {
    this.valor = valor;
  }

  public boolean isEsAs() {
    return esAs;
  }

  public void setEsAs(boolean esAs) {
    this.esAs = esAs;
  }
}
