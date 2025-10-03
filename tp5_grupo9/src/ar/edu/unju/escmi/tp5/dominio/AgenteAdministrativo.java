package ar.edu.unju.escmi.tp5.dominio;

import java.util.Scanner;

import ar.edu.unju.escmi.tp5.collections.CollectionCliente;
import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.collections.CollectionStock;

public class AgenteAdministrativo {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void altaProducto() {
        System.out.println("\n--- ALTA DE PRODUCTO ---");
        System.out.print("Código (numérico): ");
        int codigo = readInt();

        if (CollectionProducto.buscarPorCodigo(codigo) != null) {
            System.out.println("ERROR: Ya existe un producto con ese código.");
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = readLine();

        System.out.print("Precio unitario (ej: 1200.50): ");
        double precio = readDouble();

        System.out.print("Descuento (0, 25 o 30): ");
        int descuento = readInt();
        if (descuento != 0 && descuento != 25 && descuento != 30) {
            System.out.println("Descuento inválido. Se asigna 0.");
            descuento = 0;
        }

        System.out.print("Stock inicial (cantidad total): ");
        int stock = readInt();

        Producto p = new Producto(codigo, descripcion, precio, descuento, stock);
        boolean ok = CollectionProducto.agregarProducto(p);
        if (ok) {
            CollectionStock.setStock(codigo, stock);
            System.out.println("Producto dado de alta correctamente:");
            System.out.println(p);
        } else {
            System.out.println("No se pudo dar de alta el producto (código duplicado).");
        }
    }

    public static void listarProductos() {
        CollectionProducto.listarProductos();
    }
    
    public static void realizarVenta() {
        System.out.println("\n=== NUEVA VENTA ===");

        // 1) Selección de cliente
        Cliente cliente = crearCliente();
        if (cliente == null) {
            System.out.println("Venta cancelada: no se creó cliente.");
            return;
        }

        Factura factura = new Factura(cliente);

        // 2) Selección de productos
        boolean seguir = true;
        while (seguir) {
            System.out.print("Ingrese código de producto: ");
            int codigo = readInt();

            Producto producto = CollectionProducto.buscarPorCodigo(codigo);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
            } else {
                System.out.print("Cantidad: ");
                int cantidad = readInt();

                Integer stockDisponible = CollectionStock.getStock(codigo);
                if (stockDisponible == null || stockDisponible < cantidad) {
                    System.out.println("Stock insuficiente. Disponible: " + stockDisponible);
                } else {
                    // precio base
                    double precioUnitario = producto.obtenerPrecioConDescuento();

                    // si el cliente es mayorista → compra por bulto (10 unidades) → precio mitad
                    if (cliente instanceof ClienteMayorista) {
                        if (cantidad % 10 != 0) {
                            System.out.println("Mayorista debe comprar múltiplos de 10 unidades.");
                            continue;
                        }
                        precioUnitario = precioUnitario / 2; // mitad
                    }

                    DetalleFactura detalle = new DetalleFactura(producto, cantidad, precioUnitario);
                    factura.agregarDetalle(detalle);

                    // actualizar stock
                    CollectionStock.disminuirStock(codigo, cantidad);

                    System.out.println("Producto agregado a la factura.");
                }
            }

            System.out.print("¿Agregar otro producto? (s/n): ");
            String r = scanner.nextLine().trim().toLowerCase();
            seguir = r.equals("s");
        }

        // 3) Aplicar descuentos especiales por cliente
        double total = factura.getTotal();
        double totalFinal = cliente.calcularDescuento(total);

        System.out.println("\n=== FACTURA GENERADA ===");
        System.out.println(factura);
        System.out.println("TOTAL FINAL (con descuentos de cliente): $" + totalFinal);

        // 4) Guardar en colecciones
        CollectionFactura.agregarFactura(factura);
        CollectionCliente.agregarCliente(cliente);
    }

    private static Cliente crearCliente() {
        System.out.print("¿Cliente Mayorista (M) o Minorista (N)? ");
        String tipo = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        if (tipo.equals("M")) {
        System.out.print("Código de cliente mayorista: ");
        String codigo = scanner.nextLine();
        return new ClienteMayorista(nombre, apellido, direccion, codigo);
    } else if (tipo.equals("N")) { // si querés usar "N" para minorista
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("¿Posee obra social PAMI? (s/n): ");
        boolean pami = scanner.nextLine().trim().equalsIgnoreCase("s");
        return new ClienteMinorista(nombre, apellido, direccion, dni, pami);
    } else {
        System.out.println("Opción inválida. Se asume cliente minorista.");
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("¿Posee obra social PAMI? (s/n): ");
        boolean pami = scanner.nextLine().trim().equalsIgnoreCase("s");
        return new ClienteMinorista(nombre, apellido, direccion, dni, pami);
    }
 }   

 /* helpers locales */
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

    private static double readDouble() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número decimal (ej: 1250.50): ");
            }
        }
    }

    private static String readLine() {
        return scanner.nextLine().trim();
    }
}

