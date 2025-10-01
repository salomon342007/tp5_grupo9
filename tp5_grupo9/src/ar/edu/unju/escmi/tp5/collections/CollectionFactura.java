package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import ar.edu.unju.escmi.tp5.dominio.Factura;

public class CollectionFactura {
    public static ArrayList<Factura> facturas = new ArrayList<>();

    public static void agregarFactura(Factura f) {
        facturas.add(f);
    }

    public static Factura buscarFacturaPorNumero(int numero) {
        for (Factura f : facturas) {
            if (f.getNumero() == numero) return f;
        }
        return null;
    }

    public static void listarFacturas() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            for (Factura f : facturas) {
                System.out.println(f);
            }
        }
    }

    public static double calcularTotalVentas() {
        double total = 0;
        for (Factura f : facturas) {
            total += f.getTotal();
        }
        return total;
    }
}

