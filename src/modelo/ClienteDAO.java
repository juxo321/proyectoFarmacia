package modelo;

import java.util.List;

public interface ClienteDAO {
    public boolean create(Cliente cliente) throws Exception;

    public List listaClientes() throws Exception;

    public List listaClientesPorId(int idBuscar) throws Exception;
}
