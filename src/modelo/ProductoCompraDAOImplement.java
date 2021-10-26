package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoCompraDAOImplement implements ProductoCompraDAO{
    @Override
    public void actualizarProductosComprados(ProductoCompra productoCompra) throws Exception {
        boolean created = false;
        Statement stm = null;
        String sql = "INSERT INTO productocompra values (0 ," + productoCompra.getCompra().getNoCompra() + ", " + productoCompra.getNoProducto() + ", '"+ productoCompra.getNombreProducto() +"', "+ productoCompra.getCantidad() + ", " + productoCompra.getTotal() +")";
        ConexionBD cc = new ConexionBD();
        try (Connection con = cc.getConnection();) {
            stm = con.createStatement();
            stm.execute(sql);
            stm.close();
        } catch (SQLException e) {
            throw new Exception("Error en create SQLException " + e.getMessage());
        } catch (Exception ex) {
            throw new Exception("Error en create Exception " + ex.getMessage());
        }
    }
}
