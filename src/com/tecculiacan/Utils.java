package com.tecculiacan;

public class Utils {

  /**
   * Método que valida si un String es un numero entero
   *
   * @param numero cadena con un valor numérico o no numérico
   * @return verdadero o falso Si una Cadena es un numero entero
   */
  public static boolean esNumeroEntero(String numero) {
    try {
      Integer.parseInt(numero);
    } catch (NumberFormatException excepcion) {
      return false;
    }
    return true;
  }

  /**
   * Método que valida si un String es un numero decimal
   *
   * @param numero cadena con un valor numérico o no numérico
   * @return verdadero o falso Si una Cadena es un numero decimal
   */
  public static boolean esNumeroDecimal(String numero) {
    try {
      Double.valueOf(numero);
    } catch (NumberFormatException excepcion) {
      return false;
    }
    return true;
  }
}
