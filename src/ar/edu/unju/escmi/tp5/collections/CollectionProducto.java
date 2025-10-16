package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Producto;

/**
 * Maneja la collection de productos. Atributo público y estático.
 */
public class CollectionProducto {
    public static List<Producto> productos = new ArrayList<>();

    // Precarga ejemplo
    public static void precargarProductos() {
        productos.clear();
        productos.add(new Producto(1001, "Fideo Knorr Spaghetti x500g", 1200.00, 0, 5000));
        productos.add(new Producto(1002, "Arroz Tipo1 1kg", 800.00, 25, 3000));
        productos.add(new Producto(1003, "Aceite 1L", 2500.00, 0, 1000));
    }

    // Agregar producto si no existe código
    public static boolean agregarProducto(Producto p) {
        if (buscarPorCodigo(p.getCodigoProducto()) != null)
            return false;
        productos.add(p);
        return true;
    }

    // Buscar por código
    public static Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigoProducto() == codigo)
                return p;
        }
        return null;
    }
}
