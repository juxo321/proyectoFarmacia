package vista;
//import helpers.UsuarioHelper;
import controlador.LoginControlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import models.UsuarioEntity;

import java.util.List;

public class Login  extends GridPane {

    TextField usuariotxt= new TextField();
    PasswordField contrasenatxt = new PasswordField();
    //UsuarioHelper usuarioHelper = new UsuarioHelper();
    //List<UsuarioEntity> listaUsuarios = usuarioHelper.listaUsuarios();
    //UsuarioEntity usuarioLogeado = new UsuarioEntity();

    public Login(){
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(300,300);
        this.setHgap(10);
        this.setVgap(10);
        this.setStyle("-fx-background-color: #" + "C0FEFC");
        this.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Bienvenidos");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(scenetitle, 0, 0, 2, 1);

        Label usuariolbl = new Label("Usuario:");
        this.add(usuariolbl, 0, 1);

        usuariotxt.setText("Justin Hernandez");
        this.add(usuariotxt, 1, 1);

        Label contrasena = new Label("Contraseña:");
        this.add(contrasena, 0, 2);

         contrasenatxt.setText("123456");
        this.add(contrasenatxt, 1, 2);

        Button loginbtn = new Button("Iniciar sesión");
        HBox hboxLogin = new HBox(10);
        hboxLogin.setAlignment(Pos.BOTTOM_RIGHT);
        hboxLogin.getChildren().add(loginbtn);
        this.add(loginbtn, 1, 4);

        final Text actiontarget = new Text();
        this.add(actiontarget, 1, 6);



        loginbtn.setOnAction(e -> {
            LoginControlador loginControlador = new LoginControlador();
            try {
                loginControlador.verificarDatos(Login.this);
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error al conectarse");
                alert.setHeaderText(null);
                alert.setContentText("Error al conectarse con la base de datos");
                alert.showAndWait();
            }
        });
    }

    public void crearStagePrincipal(String tipo){

        pantallaPrincipal pantallaPrincipal = new pantallaPrincipal(tipo);

        Stage stagePrincipal = new Stage();
        stagePrincipal.setScene(new Scene(pantallaPrincipal, 1200, 950));
        stagePrincipal.centerOnScreen();
        stagePrincipal.setAlwaysOnTop(false);
        stagePrincipal.setResizable(false);
        stagePrincipal.show();

        Stage stage = (Stage) Login.this.getScene().getWindow();
        stage.close();
    }

    public String getUsuario(){
        return usuariotxt.getText();
    }

    public String getContrasena(){
        return contrasenatxt.getText();
    }

    /*public int validarUsuario(String usuariotxt, String contrasenatxt){
        for(UsuarioEntity usuario : listaUsuarios){
            if(usuariotxt.equals(usuario.getNombre())){
                if(contrasenatxt.equals(usuario.getContrasena())){
                    usuarioLogeado = usuario;
                    return 1;
                }else{
                    return 2;
                }
            }else{
                return 3;
            }
        }
        return 4;
    }*/

}
