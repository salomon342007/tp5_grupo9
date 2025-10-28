package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Factura;


 //Collection de facturas, Precarga, agregar y buscar.

public class CollectionFactura {
    public static List<Factura> facturas = new ArrayList<>();

    public static void precargarFacturas() {
        facturas.clear();
        // no agregamos facturas reales en precarga por simplicidad
    }

    public static void agregarFactura(Factura f) {
        facturas.add(f);
    }

    public static Factura buscarPorNumero(int numero) {
        for (Factura f : facturas) {
            if (f.getNumero() == numero)
                return f;
        }
        return null;
    }
}
