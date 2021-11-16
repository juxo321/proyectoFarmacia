package vista;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
//import models.UsuarioEntity;

public class pantallaPrincipal extends VBox {
    pantallaPrincipal(String tipo){
        barraSuperior barraSuperior = new barraSuperior(tipo);
        contenedorCentral contenedorCentral = new contenedorCentral(tipo);
        this.setPadding(new Insets(10,10,10,10));
        this.getChildren().addAll(barraSuperior,contenedorCentral);
    }
}
