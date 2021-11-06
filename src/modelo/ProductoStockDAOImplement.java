package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoStockDAOImplement implements ProductoStockDAO{
    @Override
    public void actualizarProductosStock(ProductoStock productoStock) throws Exception {
        if(productoExiste(productoStock)){
            Statement stm = null;
            int cantidad = 0;
            cantidad = cantidadProductoStock(productoStock);
            String sql = "update productostock set cantidad=? where noProductoStock=?";

            ConexionBD cc = new ConexionBD();
            try (Connection con = cc.getConnection();) {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, cantidad+productoStock.getCantidad());
                stmt.setInt(2, productoStock.getNoProductoStock());
                stmt.executeUpdate();
                con.close();
            } catch (SQLException e) {
                throw new Exception("Error en create SQLException " + e.getMessage());
            } catch (Exception ex) {
                throw new Exception("Error en create Exception " + ex.getMessage());
            }
        }else {
            Statement stm = null;
            String sql = "INSERT INTO productostock values ("+ productoStock.getNoProductoStock() + ", '"+ productoStock.getNombreProducto() +"', "+ productoStock.getCantidad() + ", '" + productoStock.getTipo() +"'," + productoStock.getPrecio() +")";
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

    @Override
    public void actualizarProductosStockVenta(ProductoStock productoStock) throws Exception {
        if(productoExiste(productoStock)){
            Statement stm = null;
            int cantidad = 0;
            cantidad = cantidadProductoStock(productoStock);
            String sql = "update productostock set cantidad=? where noProductoStock=?";

            ConexionBD cc = new ConexionBD();
            try (Connection con = cc.getConnection();) {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, productoStock.getCantidad());
                stmt.setInt(2, productoStock.getNoProductoStock());
                stmt.executeUpdate();
                con.close();
            } catch (SQLException e) {
                throw new Exception("Error en create SQLException " + e.getMessage());
            } catch (Exception ex) {
                throw new Exception("Error en create Exception " + ex.getMessage());
            }
        }else {
            Statement stm = null;
            String sql = "INSERT INTO productostock values ("+ productoStock.getNoProductoStock() + ", '"+ productoStock.getNombreProducto() +"', "+ productoStock.getCantidad() + ", '" + productoStock.getTipo() +"'," + productoStock.getPrecio() +")";
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

    @Override
    public boolean productoExiste(ProductoStock productoStock) throws Exception {
        Statement stm;
        ResultSet compra;
        String sql = "select * from productostock where noProductoStock="+ productoStock.getNoProductoStock() ;
        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            compra = stm.executeQuery(sql);
            while(compra.next()){
                int noProductoStock  = compra.getInt("noProductoStock");
                if(noProductoStock == productoStock.getNoProductoStock()){
                    System.out.println("entre en true");
                    return true;
                }else{
                    System.out.println("entre en false");
                    return false;
                }
            }
            System.out.println("entre en el false");
            return false;
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }

    @Override
    public int cantidadProductoStock(ProductoStock productoStock) throws Exception {
        Statement stm;
        ResultSet productos;
        String sql = "select cantidad from productostock where noProductoStock =" + productoStock.getNoProductoStock() ;

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            productos = stm.executeQuery(sql);
            while (productos.next()){
                int cantidad = productos.getInt("cantidad");
                return cantidad;
            }
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }

        return -1;
    }

    public List obtenerProductosStock() throws Exception {
        Statement stm;
        ResultSet productos;
        String sql = "select * from productostock";
        List<ProductoStock> listaProductosStock = new ArrayList<ProductoStock>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            productos = stm.executeQuery(sql);
            while (productos.next()){
                int noProducto = productos.getInt("noProductoStock");
                String nombreProducto = productos.getString("nombreProducto");
                int cantidad = productos.getInt("cantidad");
                String tipo = productos.getString("tipo");
                double precio = productos.getDouble("precio");
                ProductoStock producto = new ProductoStock(noProducto,nombreProducto,cantidad,tipo,precio);
                listaProductosStock.add(producto);
            }
            return listaProductosStock;
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }

    @Override
    public boolean actualizarProductosStockEditado(ProductoStock productoStock) throws Exception {
        Statement stm = null;
        String sql = "update productostock set nombreProducto=?, cantidad=?, tipo=?, precio=?  where noProductoStock=?";

        ConexionBD cc = new ConexionBD();
        try (Connection con = cc.getConnection();) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, productoStock.getNombreProducto());
            stmt.setInt(2, productoStock.getCantidad());
            stmt.setString(3, productoStock.getTipo());
            stmt.setDouble(4, productoStock.getPrecio());
            stmt.setInt(5, productoStock.getNoProductoStock());
            if (stmt.executeUpdate()>0){
                con.close();
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new Exception("Error en create SQLException " + e.getMessage());
        } catch (Exception ex) {
            throw new Exception("Error en create Exception " + ex.getMessage());
        }
    }

    @Override
    public boolean borrarProductoStock(int noProductoStock) throws Exception {
        Statement stm = null;
        String sql = "DELETE FROM productostock where noProductoStock=?";

        ConexionBD cc = new ConexionBD();
        try (Connection con = cc.getConnection();) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, noProductoStock);
            if (stmt.executeUpdate()>0){
                con.close();
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new Exception("Error en create SQLException " + e.getMessage());
        } catch (Exception ex) {
            throw new Exception("Error en create Exception " + ex.getMessage());
        }
    }
}
