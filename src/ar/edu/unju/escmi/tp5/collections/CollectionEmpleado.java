package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Empleado;
import ar.edu.unju.escmi.tp5.dominio.EncargadoDeVentas;
import ar.edu.unju.escmi.tp5.dominio.AgenteAdministrativo;

public class CollectionEmpleado {
    public static List<Empleado> empleados = new ArrayList<>();

    // Registro simple: evita legajo duplicado
    public static boolean registroEmpleado(int legajo, String contrasenia, String nombre, boolean esEncargado) {
        for (Empleado e : empleados) if (e.getLegajo() == legajo) return false;
        if (esEncargado) {
            empleados.add(new EncargadoDeVentas(legajo, nombre, contrasenia));
        } else {
            empleados.add(new AgenteAdministrativo(legajo, nombre, contrasenia));
        }
        return true;
    }

    // Devuelve el tipo de empleado si credenciales válidas
    public static String tipoEmpleado(int legajo, String contrasenia) {
        for (Empleado e : empleados) {
            if (e.getLegajo() == legajo && e.getContrasenia().equals(contrasenia)) {
                return e.getClass().getSimpleName();
            }
        }
        return null;
    }

    public static void precargarEmpleados() {
        empleados.clear();
        empleados.add(new EncargadoDeVentas(1001, "Carlos Ruiz", "enc123"));
        empleados.add(new AgenteAdministrativo(2001, "María López", "adm123"));
    }
}
