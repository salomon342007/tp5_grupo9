package ar.edu.unju.escmi.tp5.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.tp5.collections.CollectionCliente;
import ar.edu.unju.escmi.tp5.collections.CollectionEmpleado;
import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.dominio.AgenteAdministrativo;
import ar.edu.unju.escmi.tp5.dominio.Cliente;
import ar.edu.unju.escmi.tp5.dominio.ClienteMayorista;
import ar.edu.unju.escmi.tp5.dominio.ClienteMinorista;
import ar.edu.unju.escmi.tp5.dominio.DetalleFactura;
import ar.edu.unju.escmi.tp5.dominio.EncargadoDeVentas;
import ar.edu.unju.escmi.tp5.dominio.Factura;
import ar.edu.unju.escmi.tp5.dominio.Producto;

/**
 * Menu de consola para el TP:
 * - Encargado de ventas: mostrar ventas, total ventas, verificar stock.
 * - Cliente: buscar factura por número.
 * - Agente administrativo: alta de producto, realizar venta.
 *
 * Uso:
 * - Pegar en src/ar/edu/unju/escmi/tp5/principal/ConsoleMenu.java
 * - Compilar todo: javac -d .\bin (Get-ChildItem -Path .\src -Recurse -Filter
 * *.java ...)
 * - Ejecutar: java -cp .\bin ar.edu.unju.escmi.tp5.principal.ConsoleMenu
 *
 * Nota: el programa llama a las precargas de collections para que haya datos de
 * ejemplo.
 */
public class ConsoleMenu {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Precargas (si no las hiciste en otro lado)
        CollectionProducto.precargarProductos();
        CollectionCliente.precargarClientes();
        CollectionFactura.precargarFacturas();
        CollectionEmpleado.precargarEmpleados();

        System.out.println("=== TP POO - Menu de prueba ===");
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n-- MENU PRINCIPAL --");
                System.out.println("1) Encargado de ventas");
                System.out.println("2) Cliente");
                System.out.println("3) Agente administrativo");
                System.out.println("4) Mostrar todos los productos (consulta rápida)");
                System.out.println("0) Salir");
                System.out.print("Opción: ");
                String opt = sc.nextLine().trim();
                switch (opt) {
                    case "1":
                        menuEncargado();
                        break;
                    case "2":
                        menuCliente();
                        break;
                    case "3":
                        menuAdministrativo();
                        break;
                    case "4":
                        mostrarTodosProductos();
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                // limpiar buffer si hace falta y continuar
            }
        }
        sc.close();
    }

    /* ---------------- Encargado de ventas ---------------- */
    private static void menuEncargado() {
        System.out.println("\n--- ENCARGADO DE VENTAS ---");
        System.out.println("1) Mostrar ventas");
        System.out.println("2) Mostrar total de todas las ventas");
        System.out.println("3) Verificar stock por código");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String o = sc.nextLine().trim();
        switch (o) {
            case "1":
                List<Factura> ventas = EncargadoDeVentas.mostrarVentas();
                if (ventas == null || ventas.isEmpty()) {
                    System.out.println("No hay ventas registradas.");
                } else {
                    for (Factura f : ventas)
                        System.out.println(f);
                }
                break;
            case "2":
                System.out.println("Total ventas acumulado: " + EncargadoDeVentas.mostrarTotalVentas());
                break;
            case "3":
                System.out.print("Código del producto: ");
                try {
                    int codigo = Integer.parseInt(sc.nextLine().trim());
                    int stock = EncargadoDeVentas.verificarStock(codigo);
                    if (stock < 0)
                        System.out.println("Producto no encontrado.");
                    else
                        System.out.println("Stock del producto " + codigo + ": " + stock);
                } catch (NumberFormatException e) {
                    System.out.println("Código inválido.");
                }
                break;
            case "0":
                return;
            default:
                System.out.println("Opción inválida.");
        }
    }

    /* ---------------- Cliente ---------------- */
    private static void menuCliente() {
        System.out.println("\n--- CLIENTE ---");
        System.out.print("Ingrese número de factura a buscar: ");
        String s = sc.nextLine().trim();
        try {
            int nro = Integer.parseInt(s);
            Factura f = CollectionFactura.buscarPorNumero(nro);
            if (f != null)
                System.out.println("Factura encontrada:\n" + f);
            else
                System.out.println("Factura no encontrada.");
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
        }
    }

    /* ---------------- Agente administrativo ---------------- */
    private static void menuAdministrativo() {
        System.out.println("\n--- AGENTE ADMINISTRATIVO ---");
        System.out.println("1) Alta de producto");
        System.out.println("2) Realizar venta");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String o = sc.nextLine().trim();
        switch (o) {
            case "1":
                altaProductoFlujo();
                break;
            case "2":
                realizarVentaFlujo();
                break;
            case "0":
                return;
            default:
                System.out.println("Opción inválida.");
        }
    }

    // flujo para dar de alta un producto nuevo
    private static void altaProductoFlujo() {
        try {
            System.out.print("Código (número): ");
            int codigo = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Descripción: ");
            String desc = sc.nextLine().trim();
            System.out.print("Precio unitario (ej: 1200.0): ");
            double precio = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Descuento porcentual (ej: 0, 25, 30): ");
            int descP = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Cantidad inicial (stock): ");
            int cantidad = Integer.parseInt(sc.nextLine().trim());

            Producto p = new Producto(codigo, desc, precio, descP, cantidad);
            boolean ok = AgenteAdministrativo.altaProducto(p);
            if (ok)
                System.out.println("Producto agregado correctamente.");
            else
                System.out.println("No se pudo agregar: ya existe un producto con ese código.");
        } catch (NumberFormatException e) {
            System.out.println("Valores numéricos inválidos. Alta cancelada.");
        }
    }

    // flujo para realizar una venta completa (pedir cliente y items)
    private static void realizarVentaFlujo() {
        try {
            System.out.print("Cliente (dni para minorista / codigo para mayorista): ");
            String clave = sc.nextLine().trim();
            Cliente cliente = CollectionCliente.buscarPorCodigo(clave);
            if (cliente == null) {
                System.out.println("Cliente no encontrado. ¿Desea crear un cliente minorista temporal? (s/n)");
                String r = sc.nextLine().trim().toLowerCase();
                if (!r.equals("s")) {
                    System.out.println("Venta cancelada.");
                    return;
                }
                System.out.print("Apellido: ");
                String ape = sc.nextLine().trim();
                System.out.print("Nombre: ");
                String nom = sc.nextLine().trim();
                System.out.print("Dirección: ");
                String dir = sc.nextLine().trim();
                cliente = new ClienteMinorista(ape, nom, dir, clave, false);
                CollectionCliente.agregarCliente(cliente);
                System.out.println("Cliente minorista temporal creado.");
            }

            List<DetalleFactura> items = new ArrayList<>();
            while (true) {
                System.out.print("Código producto (0 para terminar): ");
                String scod = sc.nextLine().trim();
                int cod;
                try {
                    cod = Integer.parseInt(scod);
                } catch (NumberFormatException e) {
                    System.out.println("Código inválido.");
                    continue;
                }
                if (cod == 0)
                    break;
                Producto p = CollectionProducto.buscarPorCodigo(cod);
                if (p == null) {
                    System.out.println("Producto no encontrado.");
                    continue;
                }
                System.out.print("Cantidad: ");
                int cant;
                try {
                    cant = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida.");
                    continue;
                }
                if (cant <= 0) {
                    System.out.println("Cantidad debe ser mayor que 0.");
                    continue;
                }
                // chequeo básico de stock (opcional)
                if (p.getCantidadTotal() < cant) {
                    System.out.println("Advertencia: stock insuficiente. Stock actual: " + p.getCantidadTotal());
                    System.out.print("Desea continuar con la cantidad solicitada? (s/n): ");
                    String r = sc.nextLine().trim().toLowerCase();
                    if (!r.equals("s"))
                        continue;
                }
                items.add(new DetalleFactura(p, cant));
            }

            if (items.isEmpty()) {
                System.out.println("Venta cancelada (sin items).");
                return;
            }

            Factura factura = AgenteAdministrativo.realizarVenta(cliente, items);
            System.out.println("Factura generada:\n" + factura);
        } catch (Exception e) {
            System.out.println("Error en el proceso de venta: " + e.getMessage());
        }
    }

    /* ---------------- Utilidades ---------------- */
    private static void mostrarTodosProductos() {
        System.out.println("\n-- LISTA DE PRODUCTOS --");
        if (CollectionProducto.productos == null || CollectionProducto.productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }
        for (Producto p : CollectionProducto.productos) {
            System.out.println(p);
        }
    }
}
