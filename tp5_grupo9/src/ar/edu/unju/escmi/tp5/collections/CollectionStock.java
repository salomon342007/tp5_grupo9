package ar.edu.unju.escmi.tp5.collections;

import java.util.HashMap;

public class CollectionStock {
    // clave: codigo producto -> valor: cantidad disponible
    public static HashMap<Integer, Integer> stock = new HashMap<>();

    public static void setStock(int codigo, int cantidad) {
        stock.put(codigo, cantidad);
    }

    public static Integer getStock(int codigo) {
        return stock.get(codigo);
    }

    public static boolean existeProducto(int codigo) {
        return stock.containsKey(codigo);
    }

    public static void disminuirStock(int codigo, int cantidad) {
        stock.put(codigo, Math.max(0, stock.getOrDefault(codigo, 0) - cantidad));
    }

    public static void incrementarStock(int codigo, int cantidad) {
        stock.put(codigo, stock.getOrDefault(codigo, 0) + cantidad);
    }
}
