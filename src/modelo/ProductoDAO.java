package modelo;

import java.util.List;

public interface ProductoDAO {

    public void actualizarProductos(Producto producto) throws Exception;

    public List obtenerProductos(int noProvedor) throws Exception;
}
