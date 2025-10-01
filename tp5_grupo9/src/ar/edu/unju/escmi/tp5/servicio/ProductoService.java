package ar.edu.unju.escmi.tp5.servicio;

import java.util.Scanner;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.collections.CollectionStock;
import ar.edu.unju.escmi.tp5.dominio.Producto;

public class ProductoService {

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
