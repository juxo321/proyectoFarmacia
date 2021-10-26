package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsuarioDAOImplement implements UsuarioDAO{
    @Override
    public boolean create(Usuario usuario) throws Exception {
        return false;
    }

    @Override
    public List<Usuario> readAll() throws Exception {
        return null;
    }

    @Override
    public Usuario read(int id) throws Exception {
        return null;
    }

    @Override
    public boolean update(Usuario usuario) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Usuario usuario) throws Exception {
        return false;
    }

    @Override
    public boolean verificarDatos(String usuario, String contrasena) throws Exception {
        Statement stm;
        ResultSet usuarios;
        String sql = "select * from usuario;";

        ConexionBD conn = new ConexionBD();
        try (Connection conexion = conn.getConnection();){
            stm = conexion.createStatement();
            usuarios = stm.executeQuery(sql);
            while (usuarios.next()){
                if(usuarios.getString("nombre").equals(usuario) && usuarios.getInt("contrasena") == Integer.parseInt(contrasena)){
                    return true;
                }
            }
            return  false;
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
    }
}
