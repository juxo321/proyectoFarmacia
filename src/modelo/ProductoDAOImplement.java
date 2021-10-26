package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImplement implements ProductoDAO{
    @Override
    public void actualizarProductos(Producto producto) throws Exception {
        Statement stm = null;
        String sql = "update producto set cantidad=? where noProducto=?";
        ConexionBD cc = new ConexionBD();
        try (Connection con = cc.getConnection();) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, producto.getCantidad());
            stmt.setInt(2, producto.getNoProducto());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error en create SQLException " + e.getMessage());
        } catch (Exception ex) {
            throw new Exception("Error en create Exception " + ex.getMessage());
        }
    }

    @Override
    public List obtenerProductos(int noProvedor) throws Exception {
        Statement stm;
        ResultSet productos;
        String sql = "select * from producto where noProvedor =" + noProvedor ;
        List<Producto> listaProductos = new ArrayList<Producto>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            productos = stm.executeQuery(sql);
            while (productos.next()){
                    int noProducto = productos.getInt("noProducto");
                    String nombreProducto = productos.getString("nombreProducto");
                    int cantidad = productos.getInt("cantidad");
                    String tipo = productos.getString("tipo");
                    double precio = productos.getDouble("precio");
                    Producto producto = new Producto(noProducto,nombreProducto,cantidad,tipo,precio);
                    listaProductos.add(producto);
            }
            return listaProductos;
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }
}
