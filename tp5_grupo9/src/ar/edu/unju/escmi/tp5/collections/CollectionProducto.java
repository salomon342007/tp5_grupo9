package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Producto;

public class CollectionProducto {
    public static List<Producto> productos = new ArrayList<>();

    public static void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public static Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public static void actualizarStock(int codigo, int cantidadVendida) {
        Producto p = buscarPorCodigo(codigo);
        if (p != null) {
            int nuevoStock = p.getStock() - cantidadVendida;
            p.setStock(nuevoStock);
        }
    }

    public static List<Producto> listarProductos() {
        return productos;
    }
}