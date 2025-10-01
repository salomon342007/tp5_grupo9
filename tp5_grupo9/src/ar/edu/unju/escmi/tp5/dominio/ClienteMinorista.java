package ar.edu.unju.escmi.tp5.dominio;

public class ClienteMinorista extends Cliente {
    private String dni;
    private boolean obraSocialPami;

    public ClienteMinorista(String nombre, String apellido, String direccion, String dni, boolean obraSocialPami) {
        super(nombre, apellido, direccion);
        this.dni = dni;
        this.obraSocialPami = obraSocialPami;
    }

    public String getDni() {
        return dni;
    }

    public boolean isObraSocialPami() {
        return obraSocialPami;
    }

    // Si tiene PAMI: 10% de descuento sobre el total de la factura
    @Override
    public double calcularDescuento(double total) {
        if (obraSocialPami) {
            return total * 0.90; // 10% off
        }
        return total;
    }

    @Override
    public String toString() {
        return "Minorista: " + super.toString() + " (DNI: " + dni + ", PAMI: " + (obraSocialPami ? "sí" : "no") + ")";
    }
}