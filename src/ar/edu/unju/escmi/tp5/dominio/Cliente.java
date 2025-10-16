package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;

/**
 * Clase abstracta Cliente. Define comportamiento polimórfico
 * calcularTotalCompra.
 * NOTA: método abstracto es de instancia (no puede ser static).
 */
public abstract class Cliente {
    protected String apellido;
    protected String nombre;
    protected String direccion;

    public Cliente(String apellido, String nombre, String direccion) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Método polimórfico: cada tipo de cliente calcula el total según su lógica
    public abstract double calcularTotalCompra(List<DetalleFactura> items);

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Permite consultar una factura por número (usa CollectionFactura)
    public Factura consultarFactura(int numero) {
        return ar.edu.unju.escmi.tp5.collections.CollectionFactura.buscarPorNumero(numero);
    }
}
