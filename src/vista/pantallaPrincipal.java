package vista;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
//import models.UsuarioEntity;

public class pantallaPrincipal extends VBox {
    pantallaPrincipal(){
        barraSuperior barraSuperior = new barraSuperior();
        contenedorCentral contenedorCentral = new contenedorCentral();
        this.setPadding(new Insets(10,10,10,10));
        this.getChildren().addAll(barraSuperior,contenedorCentral);
    }
}
