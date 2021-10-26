package modelo;

import java.util.List;

public interface VentaDAO {
    public boolean create(Venta venta) throws Exception;

    public List<Venta> listaVentas() throws Exception;

    public Compra read(int clave) throws Exception;

    public boolean update(Venta venta) throws Exception;

    public boolean delete(Venta venta) throws Exception;


    public int recuperarUltimaVenta() throws Exception;
}
