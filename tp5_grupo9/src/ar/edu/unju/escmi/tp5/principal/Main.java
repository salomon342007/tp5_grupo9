package ar.edu.unju.escmi.tp5.principal;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    menuEncargado();
                    break;
                case 2:
                    menuAgente();
                    break;
                case 3:
                    menuCliente();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Submenú Encargado de Ventas
    private static void menuEncargado() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ENCARGADO DE VENTAS ---");
            System.out.println("1 - Mostrar todas las ventas");
            System.out.println("2 - Mostrar total de ventas");
            System.out.println("3 - Verificar stock de un producto");
            System.out.println("0 - Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(">> Mostrar ventas (pendiente implementación)");
                    break;
                case 2:
                    System.out.println(">> Mostrar total de ventas (pendiente implementación)");
                    break;
                case 3:
                    System.out.println(">> Verificar stock (pendiente implementación)");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Submenú Agente Administrativo
    private static void menuAgente() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ AGENTE ADMINISTRATIVO ---");
            System.out.println("1 - Alta de producto");
            System.out.println("2 - Realizar venta");
            System.out.println("0 - Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(">> Alta de producto (pendiente implementación)");
                    break;
                case 2:
                    System.out.println(">> Realizar venta (pendiente implementación)");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Submenú Cliente
    private static void menuCliente() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1 - Buscar factura por número");
            System.out.println("0 - Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(">> Buscar factura (pendiente implementación)");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
