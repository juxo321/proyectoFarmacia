package controlador;

import javafx.scene.control.Alert;
import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.UsuarioDAOImplement;
import vista.Login;

public class LoginControlador {

    public void verificarDatos(Login login) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAOImplement();
        if(login.getUsuario().trim().length() != 0 || login.getContrasena().trim().length() !=0) {
            if (usuarioDAO.verificarDatos(login.getUsuario(), login.getContrasena()) == true) {
                //Usuario usuarioLogueado = new Usuario();
                login.crearStagePrincipal();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Datos incorrectos");
                alert.setHeaderText(null);
                alert.setContentText("Usuario o contraseñas ");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese datos validos");
            alert.showAndWait();
        }
    }
}
