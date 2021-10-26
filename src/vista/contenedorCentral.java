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

    //Tab tabComprarProductos = new Tab("Comprar Productos", areaComprarProductos);
    Tab tabCompras = new Tab("Compras", areaCompras);
    Tab tabVenderProductos = new Tab("Vender Productos", areaVenderProductos);
    Tab tabVentas = new Tab("Ventas", areaVentas);
    //Tab tabListaClientes = new Tab("Lista de clientes", areaListaClientes);
    //Tab tabProductosStock = new Tab("Productos en stock", areaProductosStock);
    Tab tabCorteCaja = new Tab("Corte de caja", areaCorteCaja);

    //TabCompra

    double totalCompra;
    int cantidad;

    //ProductoHelper productoHelper = new ProductoHelper();
    //CompraHelper compraHelper = new CompraHelper();
    //ProductoCompraHelper productoCompraHelper = new ProductoCompraHelper();


    TableView tablaComprarProductos;
    TableView tabladetallesComprarProductos;

    //ProductoEntity productoAgregar = new ProductoEntity();
    //List<ProductocompraEntity> listaProductosComprados = new ArrayList<>();

    //ProductocompraEntity productoCompra = new ProductocompraEntity();
    int indexProductoComprado=0;

    //CompraEntity compraActual = new CompraEntity();
    //List<CompraEntity> listaCompras = new ArrayList<>();

    int contadorCompra=1;
    int contadorId=1;

    Calendar cal = new GregorianCalendar();
    Date fechaActual = new java.sql.Date(cal.getTime().getTime());


    //Tab VerCompras


    //Tab generar ventas


    //Tab VerVentas
    TableView tablaVentas;
    //VentaHelper tabVentaHelper = new VentaHelper();
    //List<VentaEntity> listaTabVentas = new ArrayList<>();

    //Tab clientes


    //Tab productosStock


    //Tab Corte de caja
    TableView tablaCorteCaja;
    //List<CompraEntity> listaCorteCaja = new ArrayList<>();

    contenedorCentral() {
        //this.usuarioLogeado = usuarioLogeado;

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
//        componentesTabComprasRealizadas();
//        componentesTabVentas();
//        componentesTabVentasRealizadas();
//        componentesTabCliente();
//        componentesTabProductos();
//        componentesTabCorteCaja();


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
            if(!contenedorDerecho.getTabs().contains(tabCompras)){
                contenedorDerecho.getTabs().add(tabCompras);

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
            if(!contenedorDerecho.getTabs().contains(tabVentas)){
                contenedorDerecho.getTabs().add(tabVentas);
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
        tabCompras.setStyle("-fx-background-color: #" + "51c4d3");
        tabVenderProductos.setStyle("-fx-background-color: #" + "51c4d3");
        tabVentas.setStyle("-fx-background-color: #" + "51c4d3");
        tabCliente.setStyle("-fx-background-color: #" + "51c4d3");
        tabProductosStock.setStyle("-fx-background-color: #" + "51c4d3");
        tabCorteCaja.setStyle("-fx-background-color: #" + "51c4d3");


    }

    //dfggdfgfgdfgfg



    /*
    public void  componentesTabVentasRealizadas(){
        areaVentas.setPrefSize(contenedorDerecho.getPrefWidth(), contenedorDerecho.getPrefHeight());

        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, contenedorDerecho.getPrefHeight());
        HBox contenedorTablaComboBox = new HBox();
        contenedorTablaComboBox.setSpacing(20);
        VBox contenedorLabelComboBox = new VBox();
        contenedorLabelComboBox.setSpacing(10);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));

        tablaVentas = new TableView();
        tablaVentas.setPrefSize(730, 400);


        Label labelFecha = new Label("Mostras por Fecha:");
        labelFecha.setFont(Font.font("Arial", 16));
        labelFecha.setStyle("-fx-font-weight: bold");

        Label labelTodasVentas = new Label("Mostrar todas las ventas:");
        labelTodasVentas.setFont(Font.font("Arial", 16));
        labelTodasVentas.setStyle("-fx-font-weight: bold");
        Button botonTodasVentas  = new Button("Mostrar");

        DatePicker datePickerFecha = new DatePicker();

        datePickerFecha.setOnAction(event -> {
            String date = datePickerFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.util.Date date2 = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date2 = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date fechaBuscar = new Date(date2.getTime());

            listaTabVentas = tabVentaHelper.listaVentasPorFecha(fechaBuscar);
            tablaVentas.setItems(FXCollections.observableArrayList(listaTabVentas));
        });

        botonTodasVentas.setOnAction(event -> {
            listaTabVentas = tabVentaHelper.listaVentas();
            tablaVentas.setItems(FXCollections.observableArrayList(listaTabVentas));
        });

        TableColumn<VentaEntity, Integer> columnaNoVenta = new TableColumn<>("No.Venta");
        columnaNoVenta.setCellValueFactory(new PropertyValueFactory<>("noVenta"));
        columnaNoVenta.setPrefWidth(200);

        TableColumn<VentaEntity, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaCantidad.setPrefWidth(150);

        TableColumn<VentaEntity, Date> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaFecha.setPrefWidth(150);

        TableColumn<VentaEntity, Double> columnaTotal = new TableColumn<>("Total");
        columnaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        columnaTotal.setPrefWidth(150);


        tablaVentas.getColumns().addAll(columnaNoVenta,columnaCantidad,columnaFecha,columnaTotal);


        contenedorLabelComboBox.getChildren().addAll(labelFecha, datePickerFecha, labelTodasVentas,botonTodasVentas);
        contenedorTablaComboBox.getChildren().addAll(tablaVentas, contenedorLabelComboBox);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox);
        areaVentas.getChildren().addAll(contenedorTablaFormulario);
    }

    */
}
