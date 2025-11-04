package ar.edu.unju.escmi.tp5.principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ar.edu.unju.escmi.tp5.collections.*;
import ar.edu.unju.escmi.tp5.dominio.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        precargarTodo();
        mostrarMenu();
        sc.close();
    }

    private static void precargarTodo() {
        CollectionEmpleado.precargarEmpleados();
        CollectionCliente.precargarClientes();
        CollectionProducto.precargarProductos();
    }

    private static void mostrarMenu() {
        while (true) {
            System.out.println("\n=== SISTEMA DE VENTAS ===");
            System.out.println("1. Ver ventas");
            System.out.println("2. Ver total ventas");
            System.out.println("3. Verificar stock");
            System.out.println("4. Buscar factura");
            System.out.println("5. Alta producto");
            System.out.println("6. Realizar venta");
            System.out.println("0. Salir");
            
            System.out.print("Opción: ");
            String opt = sc.nextLine().trim();
            
            try {
                switch (opt) {
                    case "1": CollectionFactura.mostrar(); break;
                    case "2": System.out.printf("Total: $%.2f%n", CollectionFactura.totalVentas()); break;
                    case "3": verificarStock(); break;
                    case "4": buscarFactura(); break;
                    case "5": altaProducto(); break;
                    case "6": realizarVenta(); break;
                    case "0": return;
                    default: System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void verificarStock() {
        System.out.print("Código producto: ");
        try {
            int codigo = Integer.parseInt(sc.nextLine().trim());
            int stock = CollectionProducto.verificarStock(codigo);
            if (stock >= 0) {
                System.out.println("Stock disponible: " + stock);
            } else {
                System.out.println("Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un código válido");
        }
    }

    private static void buscarFactura() {
        System.out.print("Número factura: ");
        try {
            int nro = Integer.parseInt(sc.nextLine().trim());
            Factura f = CollectionFactura.buscar(nro);
            if (f != null) {
                f.mostrarDetalle();
            } else {
                System.out.println("Factura no encontrada");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido");
        }
    }

    private static void altaProducto() {
        try {
            System.out.print("Código: ");
            int codigo = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Descripción: ");
            String desc = sc.nextLine().trim();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Descuento (0,25,30): ");
            int descuento = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Stock inicial: ");
            int stock = Integer.parseInt(sc.nextLine().trim());

            Producto p = new Producto(codigo, desc, precio, descuento, stock);
            if (CollectionProducto.agregar(p)) {
                System.out.println("Producto agregado correctamente");
            } else {
                System.out.println("Error: código ya existe");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese valores numéricos válidos");
        }
    }

    private static void realizarVenta() {
        try {
            System.out.print("Código cliente: ");
            int codCliente = Integer.parseInt(sc.nextLine().trim());
            Cliente cliente = CollectionCliente.buscarClientePorCodigo(codCliente);
            if (cliente == null) {
                System.out.println("Cliente no encontrado");
                return;
            }

            List<DetalleFactura> detalles = new ArrayList<>();
            while (true) {
                System.out.print("Código producto (0 para terminar): ");
                int codProd = Integer.parseInt(sc.nextLine().trim());
                if (codProd == 0) break;

                Producto prod = CollectionProducto.buscar(codProd);
                if (prod == null) {
                    System.out.println("Producto no encontrado");
                    continue;
                }

                System.out.print("Cantidad: ");
                int cant = Integer.parseInt(sc.nextLine().trim());
                if (prod.getStock() < cant) {
                    System.out.println("Error: stock insuficiente. Stock actual: " + prod.getStock());
                    continue;
                }
                detalles.add(new DetalleFactura(prod, cant));
            }

            if (detalles.isEmpty()) {
                System.out.println("No se agregaron productos");
                return;
            }

            int nroFactura = CollectionFactura.facturas.size() + 1;
            Factura factura = new Factura(nroFactura, LocalDate.now(), cliente);
            for (DetalleFactura det : detalles) {
                factura.agregarDetalle(det);
            }

            try {
                factura.calcularTotal();
                CollectionFactura.agregar(factura);
                System.out.println("Venta realizada correctamente");
                factura.mostrarDetalle();
            } catch (RuntimeException e) {
                System.out.println("Error al realizar venta: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese valores numéricos válidos");
        }
    }
}