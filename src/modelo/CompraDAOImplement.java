package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImplement implements CompraDAO{
    @Override
    public boolean create(Compra compra) throws Exception {
        boolean created = false;
        Statement stm = null;
        String sql = "INSERT INTO compra values (0, 1, " + compra.getCantidad() + ", '"+ compra.getFecha() +"', "+ compra.getTotal() + ")";
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
    public List<Compra> listaCompras() throws Exception {
        Statement stm;
        ResultSet compras;
        String sql = "select * from compra";
        List<Compra> listaCompras = new ArrayList<Compra>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            compras = stm.executeQuery(sql);
            while (compras.next()){
                int noProducto = compras.getInt("noCompra");
                int noUsuario = compras.getInt("noUsuario");
                int cantidad = compras.getInt("cantidad");
                Date fecha = compras.getDate("fecha");
                double total = compras.getDouble("total");
                Compra compra = new Compra(noProducto, cantidad,fecha ,total);
                listaCompras.add(compra);
            }
            return listaCompras;
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
    public boolean update(Compra compra) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Compra compra) throws Exception {
        return false;
    }

    @Override
    public int recuperarUltimaCompra() throws Exception {
        Statement stm;
        ResultSet compra;
        String sql = "select noCompra from compra;";

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            compra = stm.executeQuery(sql);
            int noUltimaCompra = 0;
            while(compra.next()){
                noUltimaCompra = compra.getInt("noCompra");
                noUltimaCompra= compra.getInt("noCompra");
            }
            return  noUltimaCompra+1;

        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }

    @Override
    public List<Compra> listaComprasPorFecha(Date fechaBuscar) throws Exception {
        Statement stm;
        ResultSet compras;
        String sql = "select * from compra where fecha ='" + fechaBuscar+ "'";
        List<Compra> listaCompras = new ArrayList<Compra>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            compras = stm.executeQuery(sql);
            while (compras.next()){
                int noCompra = compras.getInt("noCompra");
                int noUsuario = compras.getInt("noUsuario");
                int cantidad = compras.getInt("cantidad");
                Date fecha = compras.getDate("fecha");
                double total = compras.getDouble("total");
                Compra compra = new Compra(noCompra, cantidad,fecha ,total);
                listaCompras.add(compra);
            }
            return listaCompras;
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }
}
