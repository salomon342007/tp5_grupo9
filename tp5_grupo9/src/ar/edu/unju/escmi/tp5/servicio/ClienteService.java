package ar.edu.unju.escmi.tp5.servicio;

import java.util.Scanner;
import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.dominio.Factura;

public class ClienteService {

    private static Scanner scanner = new Scanner(System.in);

    public static void buscarFacturaPorNumero() {
        System.out.print("Ingrese número de factura a buscar: ");
        int nro = readInt();

        Factura f = CollectionFactura.buscarFacturaPorNumero(nro);
        if (f == null) {
            System.out.println("No se encontró ninguna factura con ese número.");
        } else {
            System.out.println("\n=== FACTURA ENCONTRADA ===");
            System.out.println(f);
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
