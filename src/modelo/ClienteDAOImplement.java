package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImplement implements ClienteDAO {
    @Override
    public boolean create(Cliente cliente) throws Exception {
        boolean created = false;
        Statement stm = null;
        String sql = "INSERT INTO cliente values (0, " + "'" + cliente.getNombreCliente() + "',"+ cliente.getEdad() +", '"+ cliente.getDireccion() + "')";
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

    public List listaClientes() throws Exception {
        Statement stm;
        ResultSet clientes;
        String sql = "select * from cliente";
        List<Cliente> listaClientes = new ArrayList<Cliente>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            clientes = stm.executeQuery(sql);
            while (clientes.next()){
                int id = clientes.getInt("id");
                String nombreCliente = clientes.getString("nombreCliente");
                int edad = clientes.getInt("edad");
                String direccion = clientes.getString("direccion");
                Cliente cliente = new Cliente(id,nombreCliente,edad,direccion);
                listaClientes.add(cliente);
            }
            return listaClientes;
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }

    @Override
    public List listaClientesPorId(int idBuscar) throws Exception {
        Statement stm;
        ResultSet clientes;
        String sql = "select * from cliente where id=" + idBuscar;
        List<Cliente> listaClientes = new ArrayList<Cliente>();

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            clientes = stm.executeQuery(sql);
            while (clientes.next()){
                int id = clientes.getInt("id");
                String nombreCliente = clientes.getString("nombreCliente");
                int edad = clientes.getInt("edad");
                String direccion = clientes.getString("direccion");
                Cliente cliente = new Cliente(id,nombreCliente,edad,direccion);
                listaClientes.add(cliente);
            }
            return listaClientes;
        }
        catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }
}
