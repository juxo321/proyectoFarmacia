package modelo;

import java.sql.Date;
import java.util.List;

public interface CompraDAO {
    public boolean create(Compra compra) throws Exception;

    public List<Compra> listaCompras() throws Exception;

    public Compra read(int clave) throws Exception;

    public boolean update(Compra compra) throws Exception;

    public boolean delete(Compra compra) throws Exception;

    public int recuperarUltimaCompra() throws Exception;

    public List<Compra> listaComprasPorFecha(Date fechaBuscar) throws Exception;
}
