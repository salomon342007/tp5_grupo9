package ar.edu.unju.escmi.tp5.dominio;

public class ClienteMinorista extends Cliente {
    private String dni;
    private boolean obraSocialPami;

    public ClienteMinorista(String nombre, String apellido, String direccion, String dni, boolean obraSocialPami) {
        super(nombre, apellido, direccion);
        this.dni = dni;
        this.obraSocialPami = obraSocialPami;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public boolean isObraSocialPami() { return obraSocialPami; }
    public void setObraSocialPami(boolean obraSocialPami) { this.obraSocialPami = obraSocialPami; }
}
