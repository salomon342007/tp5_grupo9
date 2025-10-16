package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Cliente;
import ar.edu.unju.escmi.tp5.dominio.ClienteMayorista;
import ar.edu.unju.escmi.tp5.dominio.ClienteMinorista;

/**
 * Collection de clientes (public static). Incluye precarga y búsquedas simples.
 */
public class CollectionCliente {
    public static List<Cliente> clientes = new ArrayList<>();

    public static void precargarClientes() {
        clientes.clear();
        clientes.add(new ClienteMayorista("Perez", "Juan", "Calle Falsa 123", "MAY001"));
        clientes.add(new ClienteMinorista("Gomez", "Ana", "Av. Siempreviva 456", "12345678", true));
        clientes.add(new ClienteMinorista("Lopez", "Carlos", "Gral. Paz 789", "87654321", false));
    }

    public static void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    // Buscar por "codigo" (si es mayorista se busca codigoCliente, si minorista se
    // busca dni)
    public static Cliente buscarPorCodigo(String codigo) {
        for (Cliente c : clientes) {
            if (c instanceof ClienteMayorista) {
                ClienteMayorista m = (ClienteMayorista) c;
                if (m.getCodigoCliente().equalsIgnoreCase(codigo))
                    return c;
            } else if (c instanceof ClienteMinorista) {
                ClienteMinorista mi = (ClienteMinorista) c;
                if (mi.getDni().equalsIgnoreCase(codigo))
                    return c;
            }
        }
        return null;
    }
}
