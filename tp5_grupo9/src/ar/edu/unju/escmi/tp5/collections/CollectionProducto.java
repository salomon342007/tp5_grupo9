package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import ar.edu.unju.escmi.tp5.dominio.Producto;

public class CollectionProducto {
    public static ArrayList<Producto> productos = new ArrayList<>();

    public static boolean agregarProducto(Producto p) {
        if (buscarPorCodigo(p.getCodigo()) != null) {
            return false; // código repetido
        }
        productos.add(p);
        return true;
    }

    public static Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    public static void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }
        System.out.println("Productos cargados:");
        for (Producto p : productos) System.out.println(p);
    }
}
