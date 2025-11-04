package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Factura;

public class CollectionFactura {
    public static List<Factura> facturas = new ArrayList<>();

    public static void agregar(Factura f) {
        facturas.add(f);
    }

    public static Factura buscar(int nroFactura) {
        for (Factura f : facturas) if (f.getNumero() == nroFactura) return f;
        return null;
    }

    public static double totalVentas() {
        double t = 0;
        for (Factura f : facturas) t += f.getTotal();
        return t;
    }

    public static void mostrar() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            return;
        }
        for (Factura f : facturas) f.mostrarDetalle();
    }
}
