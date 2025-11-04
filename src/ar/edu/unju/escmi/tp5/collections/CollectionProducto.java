package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Producto;

public class CollectionProducto {
    public static List<Producto> productos = new ArrayList<>();

    public static Producto buscar(int codProducto) {
        for (Producto p : productos) {
            if (p.getCodigo() == codProducto) return p;
        }
        return null;
    }

    public static int verificarStock(int codProducto) {
        Producto p = buscar(codProducto);
        return (p == null) ? -1 : p.getStock();
    }

    public static boolean agregar(Producto p) {
        if (buscar(p.getCodigo()) != null) return false;
        productos.add(p);
        return true;
    }

    public static void precargarProductos() {
        productos.clear();
        productos.add(new Producto(1001, "Fideo Knorr Spaghetti x 500 gr", 1200.00, 0, 5000));
        productos.add(new Producto(1002, "Arroz Largo Añejo 1kg", 950.00, 25, 2000));
        productos.add(new Producto(1003, "Aceite Girasol 1.5L", 2200.00, 30, 1500));
    }
}
