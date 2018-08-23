package com.tecculiacan;

public class Carta {
  private static final String TIPO_CORAZON = "Corazon";
  private static final String TIPO_PICAS = "Picas";
  private static final String TIPO_TREBOL = "Trebol";
  private static final String TIPO_DIAMANTE = "Diamante";

  private static final String FIGURA_JACK  = "Jack";
  private static final String TIPO_REYNA = "Reyna";
  private static final String TIPO_REY = "Rey";
  private static final String TIPO_AS = "As";
  private static final String TIPO_NUMERICA = "Numerica";

  private String color;
  private String tipo;
  private int valor;
  private String figura;

  public Carta(String color,String tipo,int valor,String figura) {
    this.color = color;
    this.tipo = tipo;
    this.valor = valor;
    this.figura = figura;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
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

  public String getFigura() {
    return figura;
  }

  public void setFigura(String figura) {
    this.figura = figura;
  }
}
