package ar.edu.unju.escmi.tp5.dominio;

public class ClienteMayorista extends Cliente {
    private String codigoCliente;

    public ClienteMayorista(String nombre, String apellido, String direccion, String codigoCliente) {
        super(nombre, apellido, direccion);
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    // No aplicamos un descuento adicional global aquí porque en VentaService
    // ya se ajusta el precio por bulto (mitad del unitario).
    @Override
    public double calcularDescuento(double total) {
        return total;
    }

    @Override
    public String toString() {
        return "Mayorista: " + super.toString() + " (cod: " + codigoCliente + ")";
    }
}
