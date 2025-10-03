package ar.edu.unju.escmi.tp5.dominio;

import java.util.Scanner;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.collections.CollectionStock;

public class EncargadodeVentas {

    public static void mostrarVentas() {
        System.out.println("\n=== LISTA DE VENTAS ===");
        CollectionFactura.listarFacturas();
    }

    public static void mostrarTotalVentas() {
        double total = CollectionFactura.calcularTotalVentas();
        System.out.println("\n=== TOTAL DE VENTAS ===");
        System.out.println("Monto total facturado: $" + total);
    }
    private static Scanner scanner = new Scanner(System.in);

    public static void verificarStock() {
        System.out.print("Ingrese código de producto a verificar: ");
        int codigo = readInt();
        Integer cantidad = CollectionStock.getStock(codigo);
        if (cantidad == null) {
            System.out.println("Producto no encontrado.");
        } else {
            Producto p = CollectionProducto.buscarPorCodigo(codigo);
            String desc = (p != null) ? p.getDescripcion() : "(sin descripción)";
            System.out.println("Producto: " + desc + " | Código: " + codigo + " | Stock: " + cantidad);
        }
    }

    private static int readInt() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }
    }
}