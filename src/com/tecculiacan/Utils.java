package com.tecculiacan;

public class Utils {

  public static boolean esNumerico(String numero) {
    try {
      Integer.parseInt(numero);
    } catch (NumberFormatException excepcion) {
      return false;
    }
    return true;
  }

}
