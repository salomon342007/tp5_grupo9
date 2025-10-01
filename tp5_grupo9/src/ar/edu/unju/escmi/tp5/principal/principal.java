package ar.edu.unju.escmi.tp5.principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.tp5.collections.*;
import ar.edu.unju.escmi.tp5.dominio.*;

public class principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Encargado de Ventas");
            System.out.println("2. Cliente");
            System.out.println("3. Agente Administrativo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuEncargado(scanner);
                    break;
                case 2:
                    menuCliente(scanner);
                    break;
                case 3:
                    menuAdministrativo(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void menuEncargado(Scanner scanner) {
        System.out.println("\n--- Encargado de Ventas ---");
        System.out.println("1. Mostrar todas las ventas");
        System.out.println("2. Mostrar total de ventas");
        System.out.println("3. Verificar stock por código");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                for (Factura f : CollectionFactura.listarFacturas()) {
                    System.out.println(f);
                }
                break;
            case 2:
                System.out.println("Total de ventas: $" + CollectionFactura.calcularTotalVentas());
                break;
            case 3:
                System.out.print("Ingrese código de producto: ");
                int codigo = scanner.nextInt();
                Producto p = CollectionProducto.buscarPorCodigo(codigo);
                if (p != null) {
                    System.out.println("Stock disponible: " + p.getStock());
                } else {
                    System.out.println("Producto no encontrado.");
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    public static void menuCliente(Scanner scanner) {
        System.out.println("\n--- Cliente ---");
        System.out.print("Ingrese número de factura: ");
        int numero = scanner.nextInt();

        Factura factura = CollectionFactura.buscarPorNumero(numero);
        if (factura != null) {
            System.out.println("Factura encontrada:");
            System.out.println(factura);
        } else {
            System.out.println("No se encontró ninguna factura con ese número.");
        }
    }

    public static void menuAdministrativo(Scanner scanner) {
        System.out.println("\n--- Agente Administrativo ---");
        System.out.println("1. Alta de producto");
        System.out.println("2. Realizar venta");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                scanner.nextLine(); // limpiar buffer
                System.out.print("Descripción del producto: ");
                String descripcion = scanner.nextLine();
                System.out.print("Código: ");
                int codigo = scanner.nextInt();
                System.out.print("Precio unitario: ");
                double precioUnitario = scanner.nextDouble();
                System.out.print("Stock inicial: ");
                int stock = scanner.nextInt();
                System.out.print("Descuento: ");
                double descuento = scanner.nextDouble();
                Producto nuevo = new Producto(codigo, descripcion, precioUnitario, descuento, stock);
                CollectionProducto.agregarProducto(nuevo);
                System.out.println("Producto agregado correctamente.");
                break;

            case 2:
                scanner.nextLine(); // limpiar buffer
                System.out.print("Ingrese nombre del cliente: ");
                String nombreCliente = scanner.nextLine();
                Cliente cliente = CollectionCliente.buscarPorNombre(nombreCliente);

                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                    break;
                }

                List<DetalleFactura> detalles = new ArrayList<>();
                double totalFactura = 0;

                String continuar = "s";

                do {
                    System.out.print("Ingrese código de producto: ");
                    int codigoProducto = scanner.nextInt();
                    Producto producto = CollectionProducto.buscarPorCodigo(codigoProducto);

                    if (producto == null) {
                        System.out.println("Producto no encontrado.");
                        continue;
                    }

                    System.out.print("Cantidad a comprar: ");
                    int cantidad = scanner.nextInt();

                    precioUnitario = producto.getPrecioUnitario(); 

                    if (cliente instanceof ClienteMayorista && cantidad >= 10) {
                        precioUnitario /= 2;
                    } else if (cliente instanceof ClienteMinorista) {
                        ClienteMinorista cm = (ClienteMinorista) cliente;
                        if (cm.isObraSocialPami()) {
                            precioUnitario *= 0.9;
                        }
                    }

                    double importe = precioUnitario * cantidad;
                    totalFactura += importe;

                    DetalleFactura detalle = new DetalleFactura(producto, cantidad);

                    detalles.add(detalle);

                    CollectionProducto.actualizarStock(codigoProducto, cantidad);

                    scanner.nextLine(); // limpiar buffer
                    System.out.print("¿Agregar otro producto? (s/n): ");
                    continuar = scanner.nextLine();

                } while (continuar.equalsIgnoreCase("s"));

                int numeroFactura = CollectionFactura.listarFacturas().size() + 1;
                String fecha = LocalDate.now().toString();
                Factura factura = new Factura(numeroFactura, cliente, fecha, totalFactura, detalles);
                CollectionFactura.agregarFactura(factura);

                System.out.println("Factura generada exitosamente:");
                System.out.println(factura);
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }
}
