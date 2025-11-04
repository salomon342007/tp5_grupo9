package ar.edu.unju.escmi.tp5.dominio;

import java.util.List;


public class ClienteMinorista extends Cliente {
    private String dni;
    private boolean tienePami;

    public ClienteMinorista(String nombre, String apellido, String direccion, String dni, boolean tienePami) {
        super(nombre, apellido, direccion);
        this.dni = dni;
        this.tienePami = tienePami;
    }

    public String getDni() { return dni; }
    public boolean tienePami() { return tienePami; }
}
