package vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import models.UsuarioEntity;

import java.io.File;

public class barraSuperior extends Pane {
    HBox contenedorPrincipal = new HBox();
    HBox contenedorImagenTitulo = new HBox();
    Pane contenedorBotones = new Pane();

    Button botonCambiarUsuario;
    Button botonSalir;

    barraSuperior() {
        this.setPrefSize(1200,150);
        this.setStyle("-fx-background-color: #" + "00adb5");

        File archivo1 = new File("src/icons/logo.png");
        Image imagen1 = new Image(archivo1.toURI().toString(), 110, 110, true, true);
        ImageView logo= new ImageView(imagen1);

        Pane tituloArea = new Pane();
        Text titulo = new Text();
        titulo.setText("FARMACIAS TOÑITO");
        titulo.setFont(Font.font("Courier New", 50));
        titulo.setFill(Color.web("#EEEEEE"));
        titulo.setStyle("-fx-font-weight: bold");
        titulo.setLayoutX(30);
        titulo.setLayoutY(75);
        tituloArea.getChildren().add(titulo);

        contenedorImagenTitulo.getChildren().addAll(logo,tituloArea);
        contenedorImagenTitulo.setPadding(new Insets(20,20,20,20));

        File archivo2 = new File("src/icons/cambiarUsuario.png");
        Image imagen2 = new Image(archivo2.toURI().toString(), 40, 40, true, true);
        ImageView cambiarUsuario= new ImageView(imagen2);
        botonCambiarUsuario= new Button("",cambiarUsuario);
        botonCambiarUsuario.setLayoutX(390);
        botonCambiarUsuario.setLayoutY(55);
        botonCambiarUsuario.setStyle("-fx-background-color: #" + "00adb5");
        botonCambiarUsuario.setTooltip(new Tooltip("Cambiar de usuario"));
        botonCambiarUsuario.setOnMouseEntered(event -> {
            botonCambiarUsuario.setStyle("-fx-background-color: #" + "aad8d3");
        });
        botonCambiarUsuario.setOnMouseExited(event -> {
            botonCambiarUsuario.setStyle("-fx-background-color: #" + "00adb5");
        });

        File archivo3 = new File("src/icons/salir.png");
        Image imagen3 = new Image(archivo3.toURI().toString(), 40, 40, true, true);
        ImageView salir= new ImageView(imagen3);
        botonSalir = new Button("",salir);
        botonSalir.setLayoutX(458);
        botonSalir.setLayoutY(55);
        botonSalir.setStyle("-fx-background-color: #" + "00adb5");
        botonSalir.setTooltip(new Tooltip("Salir"));
        botonSalir.setOnMouseEntered(event -> {
            botonSalir.setStyle("-fx-background-color: #" + "aad8d3");
        });
        botonSalir.setOnMouseExited(event -> {
            botonSalir.setStyle("-fx-background-color: #" + "00adb5");
        });

        botonSalir.setOnAction(event -> {
            Stage stage = (Stage) botonSalir.getScene().getWindow();
            stage.close();
        });

        botonCambiarUsuario.setOnAction(event -> {
            Login pantallaInicio = new Login();
            Stage stage = (Stage) botonSalir.getScene().getWindow();
            stage.close();
            Stage stage1 = new Stage();
            Login pantallaLogin = new Login();
            Scene scene = new Scene(pantallaLogin, 300, 275);
            stage1.setScene(scene);
            stage1.show();
        });


        contenedorBotones.getChildren().addAll(botonCambiarUsuario,botonSalir);

        contenedorPrincipal.getChildren().addAll(contenedorImagenTitulo, contenedorBotones);
        this.getChildren().addAll(contenedorPrincipal);
    }
}
