package ar.edu.unju.escmi.tp5.dominio;

import java.util.Scanner;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;

public abstract class Cliente {
    
    private static Scanner scanner = new Scanner(System.in);
    
    protected String nombre;
    protected String apellido;
    protected String direccion;

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public double calcularDescuento(double total) {
        return total;
    }

     

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

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + direccion;
    }
}