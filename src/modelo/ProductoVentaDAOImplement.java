package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoVentaDAOImplement implements ProductoVentaDAO{
    @Override
    public void actualizarProductosVendidos(ProductoVenta productoVenta) throws Exception {
        boolean created = false;
        Statement stm = null;
        String sql = "INSERT INTO productoventa values (0 ," + productoVenta.getVenta().getNoVenta() + ", " + productoVenta.getNoProducto() + ", '"+ productoVenta.getNombreProducto() +"', "+ productoVenta.getCantidad() + ", " + productoVenta.getTotal() +")";
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
