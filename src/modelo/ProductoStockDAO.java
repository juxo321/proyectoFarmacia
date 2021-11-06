package modelo;

import java.util.List;

public interface ProductoStockDAO {

    public void actualizarProductosStock(ProductoStock productoStock) throws Exception;

    public void actualizarProductosStockVenta(ProductoStock productoStock) throws Exception;

    public boolean productoExiste(ProductoStock productoStock) throws Exception;

    public int cantidadProductoStock(ProductoStock productoStock) throws Exception;

    public List obtenerProductosStock() throws Exception;

    public boolean actualizarProductosStockEditado(ProductoStock productoStock) throws Exception;

    public boolean borrarProductoStock(int noProductoStock) throws  Exception;
}


