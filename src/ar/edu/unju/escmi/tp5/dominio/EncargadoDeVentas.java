package ar.edu.unju.escmi.tp5.dominio;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;

public class EncargadoDeVentas extends Empleado {
    public EncargadoDeVentas(int legajo, String nombre, String contrasenia) {
        super(legajo, nombre, contrasenia);
    }

    public void mostrarVentas() {
        CollectionFactura.mostrar();
    }

    public double mostrarTotalVentas() {
        return CollectionFactura.totalVentas();
    }

    public int verificarStock(int codigoProducto) {
        return CollectionProducto.verificarStock(codigoProducto);
    }
}
