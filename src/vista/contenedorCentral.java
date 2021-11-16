package vista;
//import helpers.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import models.*;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class contenedorCentral extends SplitPane {

    //UsuarioEntity usuarioLogeado;

    AnchorPane contenedorIzquierdo = new AnchorPane();
    TabPane contenedorDerecho = new TabPane();
    ToolBar barraHerramientas = new ToolBar();

    //Botones toolbar
    Label labelCompras;
    Button botonComprarProductos;
    Button botonCompras;

    Label labelVentas;
    Button botonVenderProductos;
    Button botonVentas;

    Label labelClientes;
    Button botonListaClientes;

    Label labelAdministracion;
    Button botonProductosStock;
    Button botonCorteCaja;

    //Tab Pane
    AnchorPane areaComprarProductos = new AnchorPane();
    AnchorPane areaCompras = new AnchorPane();
    AnchorPane areaVenderProductos = new AnchorPane();
    AnchorPane areaVentas = new AnchorPane();
    AnchorPane areaListaClientes = new AnchorPane();
    AnchorPane areaProductosStock = new AnchorPane();
    AnchorPane areaCorteCaja = new AnchorPane();

    String tipo;

    contenedorCentral(String tipo) {
        this.tipo = tipo;
        componentesContenedorIzquierdo();
        componentesContenedorDerecho();

        this.setPrefSize(1200, 750);
        this.getItems().addAll(contenedorIzquierdo, contenedorDerecho);
        this.setDividerPositions(0.0);
    }

    public void componentesContenedorIzquierdo(){
        labelCompras = new Label("COMPRAS");
        labelCompras.setFont(Font.font("Verdana", 15));
        labelCompras.setTextFill(Color.web("#FFFFFF"));
        labelCompras.setStyle("-fx-font-weight: bold");

        File archivo1 = new File("src/icons/compra.png");
        Image imagen1 = new Image(archivo1.toURI().toString(), 40, 40, true, true);
        ImageView compra= new ImageView(imagen1);
        botonComprarProductos = new Button("Comprar productos", compra);
        botonComprarProductos.setStyle("-fx-background-color: #" + "aad8d3");
        botonComprarProductos.setOnMouseEntered(event -> {
            botonComprarProductos.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonComprarProductos.setOnMouseExited(event -> {
            botonComprarProductos.setStyle("-fx-background-color: #" + "aad8d3");
        });

        File archivo2 = new File("src/icons/factura.png");
        Image imagen2 = new Image(archivo2.toURI().toString(), 40, 40, true, true);
        ImageView reporteCompras= new ImageView(imagen2);
        botonCompras = new Button("Compras realizadas", reporteCompras);
        botonCompras.setStyle("-fx-background-color: #" + "aad8d3");
        botonCompras.setOnMouseEntered(event -> {
            botonCompras.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonCompras.setOnMouseExited(event -> {
            botonCompras.setStyle("-fx-background-color: #" + "aad8d3");
        });

        labelVentas  = new Label("VENTAS");
        labelVentas.setFont(Font.font("Verdana", 15));
        labelVentas.setTextFill(Color.web("#FFFFFF"));
        labelVentas.setStyle("-fx-font-weight: bold");
        File archivo3 = new File("src/icons/venta.png");
        Image imagen3 = new Image(archivo3.toURI().toString(), 40, 40, true, true);
        ImageView venta= new ImageView(imagen3);
        botonVenderProductos  = new Button("Vender productos", venta);
        botonVenderProductos.setStyle("-fx-background-color: #" + "aad8d3");
        botonVenderProductos.setOnMouseEntered(event -> {
            botonVenderProductos.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonVenderProductos.setOnMouseExited(event -> {
            botonVenderProductos.setStyle("-fx-background-color: #" + "aad8d3");
        });

        File archivo4 = new File("src/icons/factura.png");
        Image imagen4 = new Image(archivo4.toURI().toString(), 40, 40, true, true);
        ImageView reporteVenta= new ImageView(imagen4);
        botonVentas  = new Button("Ventas realizadas", reporteVenta);
        botonVentas.setStyle("-fx-background-color: #" + "aad8d3");
        botonVentas.setOnMouseEntered(event -> {
            botonVentas.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonVentas.setOnMouseExited(event -> {
            botonVentas.setStyle("-fx-background-color: #" + "aad8d3");
        });

        labelClientes = new Label("CLIENTES");
        labelClientes.setFont(Font.font("Verdana", 15));
        labelClientes.setTextFill(Color.web("#FFFFFF"));
        labelClientes.setStyle("-fx-font-weight: bold");
        File archivo5 = new File("src/icons/cliente.png");
        Image imagen5 = new Image(archivo5.toURI().toString(), 40, 40, true, true);
        ImageView cliente= new ImageView(imagen5);
        botonListaClientes  = new Button("Clientes", cliente);
        botonListaClientes.setStyle("-fx-background-color: #" + "aad8d3");
        botonListaClientes.setOnMouseEntered(event -> {
            botonListaClientes.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonListaClientes.setOnMouseExited(event -> {
            botonListaClientes.setStyle("-fx-background-color: #" + "aad8d3");
        });

        labelAdministracion = new Label("ADMINISTRACIÓN");
        labelAdministracion.setFont(Font.font("Verdana", 15));
        labelAdministracion.setTextFill(Color.web("#FFFFFF"));
        labelAdministracion.setStyle("-fx-font-weight: bold");
        File archivo6 = new File("src/icons/producto.png");
        Image imagen6 = new Image(archivo6.toURI().toString(), 40, 40, true, true);
        ImageView producto= new ImageView(imagen6);
        botonProductosStock  = new Button("Productos en stock", producto);
        botonProductosStock.setStyle("-fx-background-color: #" + "aad8d3");
        botonProductosStock.setOnMouseEntered(event -> {
            botonProductosStock.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonProductosStock.setOnMouseExited(event -> {
            botonProductosStock.setStyle("-fx-background-color: #" + "aad8d3");
        });

        File archivo7 = new File("src/icons/caja.png");
        Image imagen7 = new Image(archivo7.toURI().toString(), 40, 40, true, true);
        ImageView caja= new ImageView(imagen7);
        botonCorteCaja  = new Button("Corte de caja", caja);
        botonCorteCaja.setStyle("-fx-background-color: #" + "aad8d3");
        botonCorteCaja.setOnMouseEntered(event -> {
            botonCorteCaja.setStyle("-fx-background-color: #" + "deedf0");
        });
        botonCorteCaja.setOnMouseExited(event -> {
            botonCorteCaja.setStyle("-fx-background-color: #" + "aad8d3");
        });

        if(tipo.equals("empleado")){
            botonComprarProductos.setDisable(true);
            botonCompras.setDisable(true);
        }else {
            botonVenderProductos.setDisable(true);
            botonCorteCaja.setDisable(true);

        }

        barraHerramientas.setOrientation(Orientation.VERTICAL);
        barraHerramientas.setStyle("-fx-background-color: #" + "aad8d3");
        barraHerramientas.setPrefSize(200, 800);
        barraHerramientas.getItems().addAll(labelCompras, botonComprarProductos, botonCompras, labelVentas,
                botonVenderProductos, botonVentas, labelClientes, botonListaClientes,
                labelAdministracion, botonProductosStock, botonCorteCaja);


        contenedorIzquierdo.setPrefSize(200,800);
        contenedorIzquierdo.setStyle("-fx-background-color: #" + "aad8d3");
        contenedorIzquierdo.getChildren().addAll(barraHerramientas);
    }

    public void componentesContenedorDerecho(){

          tabComprarProductos tabComprarProductos = new tabComprarProductos("Comprar Productos", areaComprarProductos, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabComprarProductos.componentesTabComprarProductos();

          tabProductosStock tabProductosStock = new tabProductosStock("Productos", areaProductosStock, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabProductosStock.componentesTabProductos();

          tabCliente tabCliente = new tabCliente("Clientes", areaListaClientes, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabCliente.componentesTabCliente();

          tabVenderProductos tabVenderProductos = new tabVenderProductos("Vender Productos", areaVenderProductos, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabVenderProductos.componentesTabVentas();

          tabCorteCaja tabCorteCaja = new tabCorteCaja("Corte caja", areaCorteCaja, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabCorteCaja.componentesTabCorteCaja(botonCorteCaja);

          tabComprasRealizadas tabComprasRealizadas = new tabComprasRealizadas("Compras realizadas", areaCompras, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabComprasRealizadas.componentesTabComprasRealizadas();

          tabVentasRealizadas tabVentasRealizadas = new tabVentasRealizadas("Ventas realizadas", areaVentas, contenedorDerecho.getPrefWidth(), contenedorDerecho.getTabMaxHeight());
          tabVentasRealizadas.componentesTabVentasRealizadas();

        botonComprarProductos.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabComprarProductos)){
                contenedorDerecho.getTabs().add(tabComprarProductos);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });
        botonCompras.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabComprasRealizadas)){
                contenedorDerecho.getTabs().add(tabComprasRealizadas);

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });
        botonVenderProductos.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabVenderProductos)){
                contenedorDerecho.getTabs().add(tabVenderProductos);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });
        botonVentas.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabVentasRealizadas)){
                contenedorDerecho.getTabs().add(tabVentasRealizadas);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });
        botonListaClientes.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabCliente)){
                contenedorDerecho.getTabs().add(tabCliente);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });
        botonProductosStock.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabProductosStock)){
                contenedorDerecho.getTabs().add(tabProductosStock);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });
        botonCorteCaja.setOnAction(event -> {
            if(!contenedorDerecho.getTabs().contains(tabCorteCaja)){
                contenedorDerecho.getTabs().add(tabCorteCaja);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pestaña");
                alert.setHeaderText(null);
                alert.setContentText("La pestaña ya esta añadida");
                alert.showAndWait();
            }
        });

        contenedorDerecho.setPrefSize(800,800);
        contenedorDerecho.setStyle("-fx-background-color: #" + "deedf0");

        tabComprarProductos.setStyle("-fx-background-color: #" + "51c4d3");
        tabComprasRealizadas.setStyle("-fx-background-color: #" + "51c4d3");
        tabVenderProductos.setStyle("-fx-background-color: #" + "51c4d3");
        tabVentasRealizadas.setStyle("-fx-background-color: #" + "51c4d3");
        tabCliente.setStyle("-fx-background-color: #" + "51c4d3");
        tabProductosStock.setStyle("-fx-background-color: #" + "51c4d3");
        tabCorteCaja.setStyle("-fx-background-color: #" + "51c4d3");
    }
}
