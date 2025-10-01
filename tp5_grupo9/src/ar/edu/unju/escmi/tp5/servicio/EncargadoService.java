package ar.edu.unju.escmi.tp5.servicio;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;

public class EncargadoService {

    public static void mostrarVentas() {
        System.out.println("\n=== LISTA DE VENTAS ===");
        CollectionFactura.listarFacturas();
    }

    public static void mostrarTotalVentas() {
        double total = CollectionFactura.calcularTotalVentas();
        System.out.println("\n=== TOTAL DE VENTAS ===");
        System.out.println("Monto total facturado: $" + total);
    }
}