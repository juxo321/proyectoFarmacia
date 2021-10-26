package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImplement implements VentaDAO{
    @Override
    public boolean create(Venta venta) throws Exception {
        boolean created = false;
        Statement stm = null;
        String sql = "INSERT INTO venta values (0, 1, " + venta.getCantidad() + ", '"+ venta.getFecha() +"', "+ venta.getTotal() + ")";
        ConexionBD cc = new ConexionBD();
        try (Connection con = cc.getConnection();) {
            stm = con.createStatement();
            stm.execute(sql);
            created = true;
            stm.close();
        } catch (SQLException e) {
            throw new Exception("Error en create SQLException " + e.getMessage());
        } catch (Exception ex) {
            throw new Exception("Error en create Exception " + ex.getMessage());
        }
        return created;
    }

    @Override
    public List<Venta> listaVentas() throws Exception {
        Statement stm;
        ResultSet ventas;
        String sql = "select * from venta";
        List<Venta> listaVentas = new ArrayList<Venta>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            ventas = stm.executeQuery(sql);
            while (ventas.next()){
                int noVenta = ventas.getInt("noVenta");
                int noUsuario = ventas.getInt("noUsuario");
                int cantidad = ventas.getInt("cantidad");
                Date fecha = ventas.getDate("fecha");
                double total = ventas.getDouble("total");
                Venta venta = new Venta(noVenta,cantidad,fecha,total);
                listaVentas.add(venta);
            }
            return listaVentas;
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }

    @Override
    public Compra read(int clave) throws Exception {
        return null;
    }

    @Override
    public boolean update(Venta venta) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Venta venta) throws Exception {
        return false;
    }

    @Override
    public int recuperarUltimaVenta() throws Exception {
        Statement stm;
        ResultSet venta;
        String sql = "select noVenta from venta;";

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            venta = stm.executeQuery(sql);
            int noUltimaVenta = 0;
            while(venta.next()){
                noUltimaVenta = venta.getInt("noVenta");
                noUltimaVenta= venta.getInt("noVenta");
            }
            return  noUltimaVenta+1;

        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }
}
