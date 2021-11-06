package modelo;

import java.util.List;

public interface UsuarioDAO {
    public boolean create(Usuario usuario) throws Exception;

    public List<Usuario> obtenerUsuarios() throws Exception;

    public Usuario read(int id) throws Exception;

    public boolean update(Usuario usuario) throws Exception;

    public boolean delete(Usuario usuario) throws Exception;

    boolean verificarDatos(String usuario, String contrasena) throws Exception;

    public boolean agregarUsuario(Usuario usuario) throws Exception;

}
