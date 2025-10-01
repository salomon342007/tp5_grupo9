package ar.edu.unju.escmi.tp5.principal;

import java.util.Scanner;
import ar.edu.unju.escmi.tp5.dominio.Producto;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.collections.CollectionStock;
import ar.edu.unju.escmi.tp5.servicio.ClienteService;
import ar.edu.unju.escmi.tp5.servicio.EncargadoService;
import ar.edu.unju.escmi.tp5.servicio.ProductoService;
import ar.edu.unju.escmi.tp5.servicio.StockService;
import ar.edu.unju.escmi.tp5.servicio.VentaService;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        precargaEjemplo();
        int opcion;
        do {
            System.out.println("===================================");
            System.out.println("   SISTEMA DE GESTIÓN DE VENTAS");
            System.out.println("===================================");
            System.out.println("Seleccione el rol:");
            System.out.println("1 - Encargado de Ventas");
            System.out.println("2 - Agente Administrativo");
            System.out.println("3 - Cliente");
            System.out.println("0 - Salir");
            System.out.print("Opción: ");

            opcion = readInt();
            switch (opcion) {
                case 1 -> menuEncargado();
                case 2 -> menuAgente();
                case 3 -> menuCliente();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void menuEncargado() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ENCARGADO DE VENTAS ---");
            System.out.println("1 - Mostrar todas las ventas");
            System.out.println("2 - Mostrar total de ventas");
            System.out.println("3 - Verificar stock de un producto");
            System.out.println("0 - Volver");
            System.out.print("Opción: ");
            opcion = readInt();

            switch (opcion) {
                case 1 -> EncargadoService.mostrarVentas();
                case 2 -> EncargadoService.mostrarTotalVentas();
                case 3 -> StockService.verificarStock();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void menuAgente() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ AGENTE ADMINISTRATIVO ---");
            System.out.println("1 - Alta de producto");
            System.out.println("2 - Realizar venta");
            System.out.println("0 - Volver");
            System.out.print("Opción: ");
            opcion = readInt();

            switch (opcion) {
                case 1 -> ProductoService.altaProducto();
                case 2 -> VentaService.realizarVenta();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void menuCliente() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1 - Buscar factura por número");
            System.out.println("0 - Volver");
            System.out.print("Opción: ");
            opcion = readInt();

            switch (opcion) {
                case 1 -> ClienteService.buscarFacturaPorNumero();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void precargaEjemplo() {
        Producto p1 = new Producto(1001, "Fideo Knorr Spaghetti x 500 gr", 1200.00, 0, 5000);
        Producto p2 = new Producto(1002, "Arroz Largo Añejo 1kg", 950.00, 25, 2000);
        CollectionProducto.agregarProducto(p1);
        CollectionStock.setStock(p1.getCodigo(), p1.getStock());
        CollectionProducto.agregarProducto(p2);
        CollectionStock.setStock(p2.getCodigo(), p2.getStock());
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
