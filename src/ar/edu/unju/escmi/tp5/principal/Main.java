package ar.edu.unju.escmi.tp5.principal;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.collections.CollectionCliente;
import ar.edu.unju.escmi.tp5.collections.CollectionEmpleado;
import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.dominio.AgenteAdministrativo;
import ar.edu.unju.escmi.tp5.dominio.Cliente;
import ar.edu.unju.escmi.tp5.dominio.ClienteMinorista;
import ar.edu.unju.escmi.tp5.dominio.DetalleFactura;
import ar.edu.unju.escmi.tp5.dominio.EncargadoDeVentas;
import ar.edu.unju.escmi.tp5.dominio.Factura;
import ar.edu.unju.escmi.tp5.dominio.Producto;


 // Main de prueba para precargar collections y simular una venta.
 
public class Main {
    public static void main(String[] args) {
        // Precargar datos
        CollectionProducto.precargarProductos();
        CollectionCliente.precargarClientes();
        CollectionFactura.precargarFacturas();
        CollectionEmpleado.precargarEmpleados();

        System.out.println("Productos iniciales:");
        for (Producto p : CollectionProducto.productos)
            System.out.println(p);

        // Simular venta: elegimos un cliente minorista
        Cliente cliente = CollectionCliente.buscarPorCodigo("12345678"); // dni de ejemplo
        if (cliente == null) {
            // si no existe, usamos un cliente nuevo y lo agregamos
            cliente = new ClienteMinorista("Prueba", "Cliente", "Calle", "99999999", true);
            CollectionCliente.agregarCliente(cliente);
        }

        // Armar items de venta
        List<DetalleFactura> items = new ArrayList<>();
        Producto p1 = CollectionProducto.buscarPorCodigo(1001); // Fideo
        Producto p2 = CollectionProducto.buscarPorCodigo(1002); // Arroz

        if (p1 != null)
            items.add(new DetalleFactura(p1, 5)); // 5 unidades
        if (p2 != null)
            items.add(new DetalleFactura(p2, 2)); // 2 unidades

        // Realizar venta mediante AgenteAdministrativo
        Factura factura = AgenteAdministrativo.realizarVenta(cliente, items);
        System.out.println("Factura generada: " + factura);

        System.out.println("Productos luego de venta (stock actualizado):");
        for (Producto p : CollectionProducto.productos)
            System.out.println(p);

        System.out.println("Total ventas acumulado: " + EncargadoDeVentas.mostrarTotalVentas());
    }
}
