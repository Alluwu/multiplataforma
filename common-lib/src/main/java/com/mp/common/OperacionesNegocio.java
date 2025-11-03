package com.mp.common;

public class OperacionesNegocio {

    public static double calcularTotalConIVA(double subtotal) {
        return subtotal * 1.12;
    }

    public static double aplicarDescuento(double total, double porcentaje) {
        return total - (total * (porcentaje / 100));
    }

    public static boolean validarCodigo(String codigo) {
        return codigo != null && codigo.matches("[A-Z]{3}-\\d{4}");
    }
}
