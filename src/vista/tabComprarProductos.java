package vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelo.*;

import java.sql.Date;
import java.util.*;

public class tabComprarProductos extends Tab {

    AnchorPane areaComprarProductos;
    double ancho;
    double alto;

    TableView<Producto> tablaComprarProductos;
    TableView<ProductoCompra> tabladetallesComprarProductos;

    Button botonagregar;
    Button botoneliminar;
    Button botonConfirmarCompra;

    Calendar cal = new GregorianCalendar();
    Date fechaActual = new java.sql.Date(cal.getTime().getTime());

    List<ProductoCompra> listaProductosComprados = new ArrayList<>();
    List<Producto> listaProductos = new ArrayList<>();
    List<ProductoStock> listaProductosStock = new ArrayList<>();

    ProductoCompra productoCompra = new ProductoCompra();
    ProductoStock productoStock = new ProductoStock();
    int indexProductoComprado;

    double totalCompra;
    int cantidad;

    ProductoDAO productoDAO = new ProductoDAOImplement();
    public tabComprarProductos(String text, AnchorPane areaComprarProductos, double ancho, double alto) {
        super(text, areaComprarProductos);
        this.areaComprarProductos = areaComprarProductos;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void componentesTabComprarProductos(){
        areaComprarProductos.setPrefSize(ancho, alto);
        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, alto);
        HBox contenedorTablaComboBox = new HBox();
        contenedorTablaComboBox.setSpacing(20);
        contenedorTablaComboBox.setFillHeight(false);
        VBox contenedorLabelComboBox = new VBox();
        contenedorLabelComboBox.setSpacing(10);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));
        HBox contenedorBotonesAgregarCancelar = new HBox();

        HBox contenedorDetallesCompra = new HBox();
        contenedorDetallesCompra.setSpacing(20);
        AnchorPane areaDetallesCompra = new AnchorPane();

        tablaComprarProductos = new TableView<>();
        tablaComprarProductos.setPrefSize(800, 300);

        botonagregar = new Button("Agregar");
        botonConfirmarCompra = new Button("Confirmar compra");
        botoneliminar = new Button("Eliminar");

        botonagregar.setDisable(true);
        botoneliminar.setDisable(true);



        //compraActual=construirCompra(compraActual);


        Label labelfecha = new Label("Fecha:");
        labelfecha.setLayoutX(30);
        labelfecha.setLayoutY(100);
        TextField textFecha = new TextField();
        textFecha.setLayoutX(100);
        textFecha.setLayoutY(100);
        textFecha.setEditable(false);
        textFecha.setText(fechaActual.toString());
        Label labelCantidad = new Label("Cantidad:");
        labelCantidad.setLayoutX(30);
        labelCantidad.setLayoutY(150);
        TextField textCantidad = new TextField();
        textCantidad.setLayoutX(100);
        textCantidad.setLayoutY(150);
        Label labelTotal = new Label("Total:");
        labelTotal.setLayoutX(30);
        labelTotal.setLayoutY(200);
        TextField textTotal = new TextField();
        textTotal.setLayoutX(100);
        textTotal.setLayoutY(200);
        botonConfirmarCompra.setLayoutX(100);
        botonConfirmarCompra.setLayoutY(300);
        ComboBox<String> comboBoxProvedor = new ComboBox<>();
        comboBoxProvedor.getItems().addAll("1","2","3");
        Label labelProvedor = new Label("Provedor:");
        labelProvedor.setFont(Font.font("Arial", 16));
        labelProvedor.setStyle("-fx-font-weight: bold");



        TableColumn<Producto, Integer> columnaNoProducto = new TableColumn<>("No.Producto");
        columnaNoProducto.setCellValueFactory(new PropertyValueFactory<>("noProducto"));
        columnaNoProducto.setPrefWidth(200);

        TableColumn<Producto, String> columnaNombreProducto = new TableColumn<>("Nombre Producto");
        columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaNombreProducto.setPrefWidth(200);

        TableColumn<Producto, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaCantidad.setPrefWidth(150);

        TableColumn<Producto, String> columnaTipo = new TableColumn<>("Tipo");
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnaTipo.setPrefWidth(150);

        TableColumn<Producto, Double> columnaPrecio = new TableColumn<>("Precio");
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaPrecio.setPrefWidth(150);


        TableColumn<Producto, Integer> columnaNoProvedor = new TableColumn<>("No.Provedor");
        columnaNoProvedor.setCellValueFactory(new PropertyValueFactory<>("noProvedor"));
        columnaNoProvedor.setPrefWidth(110);

        //SEGUNDA TABLA
        contenedorBotonesAgregarCancelar.setAlignment(Pos.CENTER_RIGHT);
        contenedorBotonesAgregarCancelar.setPadding(new Insets(10,10,10,10));
        contenedorBotonesAgregarCancelar.setSpacing(10);

        tabladetallesComprarProductos = new TableView<>();
        tabladetallesComprarProductos.setPrefSize(600, 410);
        tabladetallesComprarProductos.setPrefSize(600, 410);


        TableColumn<ProductoCompra, Integer> columnaCantidad2 = new TableColumn<>("No.Producto");
        columnaCantidad2.setCellValueFactory(new PropertyValueFactory<>("noProducto"));
        columnaCantidad2.setPrefWidth(200);

        TableColumn<ProductoCompra, String> columnaProducto2 = new TableColumn<>("Nombre Producto");
        columnaProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaProducto2.setPrefWidth(200);

        TableColumn<ProductoCompra, Integer> columnaPrecioUnitario2 = new TableColumn<>("Cantidad");
        columnaPrecioUnitario2.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<ProductoCompra, String> columnaTotal2 = new TableColumn<>("Total");
        columnaTotal2.setCellValueFactory(new PropertyValueFactory<>("Total"));
        columnaTotal2.setPrefWidth(150);

        tabladetallesComprarProductos.getColumns().addAll(columnaCantidad2, columnaProducto2, columnaPrecioUnitario2, columnaTotal2);


        //Modelo de selecci??n para la primera tabla
        TableView.TableViewSelectionModel<Producto> modeloSeleccion = tablaComprarProductos.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Producto> productoSeleccionado = modeloSeleccion.getSelectedItems();


        //Modelo de selecci??n para la segunda tabla

        TableView.TableViewSelectionModel<ProductoCompra> modeloSeleccion2 = tabladetallesComprarProductos.getSelectionModel();
        modeloSeleccion2.setSelectionMode(SelectionMode.SINGLE);

        ObservableList<ProductoCompra> productoSeleccionado2 = modeloSeleccion2.getSelectedItems();

        tablaComprarProductos.getColumns().addAll(columnaNoProducto, columnaNombreProducto, columnaCantidad, columnaTipo,columnaPrecio);


        if(tabladetallesComprarProductos.getItems().isEmpty()){
            botonConfirmarCompra.setDisable(true);
        }

        comboBoxProvedor.setOnAction(event -> {
            try {
                listaProductos = productoDAO.obtenerProductos(Integer.parseInt(comboBoxProvedor.getSelectionModel().getSelectedItem()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de conexi??n");
                alert.setContentText("Ooops, algo sali?? mal al conectarse con la base de datos!");
                alert.showAndWait();
            }
            tablaComprarProductos.setItems(FXCollections.observableArrayList(listaProductos));
        });

        botonagregar.setOnAction(event  -> {
            productoCompra = construirProductoCompra(productoSeleccionado.get(0));
            productoStock = construirProductoStock(productoSeleccionado.get(0));
            indexProductoComprado= productoEnLista(productoCompra);
            if(indexProductoComprado!=-1){
                if(productoSeleccionado.get(0).getCantidad()>0){
                    listaProductosStock.get(indexProductoComprado).setCantidad(listaProductosStock.get(indexProductoComprado).getCantidad()+1);
                    listaProductosComprados.get(indexProductoComprado).setCantidad(listaProductosComprados.get(indexProductoComprado).getCantidad()+1);
                    productoSeleccionado.get(0).setCantidad(productoSeleccionado.get(0).getCantidad()-1);
                    listaProductosComprados.get(indexProductoComprado).setTotal(productoSeleccionado.get(0).getPrecio()*listaProductosComprados.get(indexProductoComprado).getCantidad());
                    tablaComprarProductos.refresh();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cantidad insuficiente");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficientes productos para agregar");
                    alert.showAndWait();
                }
            }else{
                productoCompra.setTotal(productoSeleccionado.get(0).getPrecio());
                listaProductosStock.add(productoStock);
                listaProductosComprados.add(productoCompra);
                productoSeleccionado.get(0).setCantidad(productoSeleccionado.get(0).getCantidad()-1);
                tablaComprarProductos.refresh();
            }
            totalCompra=0;
            cantidad =0;

            for (ProductoCompra producto : listaProductosComprados){
                totalCompra+=producto.getTotal();
                cantidad+=producto.getCantidad();
                if(!tabladetallesComprarProductos.getItems().contains(producto)){
                    tabladetallesComprarProductos.getItems().addAll(producto);
                    tabladetallesComprarProductos.refresh();
                }
                textTotal.setText(String.valueOf(totalCompra));
                textCantidad.setText(String.valueOf(cantidad));
                tabladetallesComprarProductos.refresh();
            }
            if(!tabladetallesComprarProductos.getItems().isEmpty()){
                botonConfirmarCompra.setDisable(false);
            }else {
                botonConfirmarCompra.setDisable(true);
            }
        });

        botoneliminar.setOnAction(event -> {
            if(!tabladetallesComprarProductos.getItems().isEmpty()){
                int indexProductoComprado2 = productoEnLista(productoSeleccionado2.get(0));
                if(productoSeleccionado2.get(0).getCantidad()>0){
                    listaProductosStock.get(indexProductoComprado2).setCantidad(listaProductosStock.get(indexProductoComprado2).getCantidad()-1);
                    listaProductosComprados.get(indexProductoComprado2).setCantidad(listaProductosComprados.get(indexProductoComprado2).getCantidad()-1);
                    for(Producto producto : listaProductos){
                        if(productoSeleccionado2.get(0).getNoProducto() == producto.getNoProducto()){
                            producto.setCantidad(producto.getCantidad()+1);
                        }
                    }
                    double precioProductoEliminar = precioProductoEliminar(productoSeleccionado2.get(0).getNoProducto());
                    listaProductosComprados.get(indexProductoComprado2).setTotal(listaProductosComprados.get(indexProductoComprado2).getTotal()-precioProductoEliminar);
                    if(productoSeleccionado2.get(0).getCantidad() == 0){
                        listaProductosComprados.remove(indexProductoComprado2);
                        tabladetallesComprarProductos.setItems(FXCollections.observableArrayList(listaProductosComprados));
                        textTotal.setText("0");
                        textCantidad.setText("0");
                    }
                    tablaComprarProductos.refresh();
                    tabladetallesComprarProductos.refresh();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cantidad insuficiente");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficientes productos para eliminar");
                    alert.showAndWait();
                }

                totalCompra=0;
                cantidad =0;


                for (ProductoCompra producto : listaProductosComprados){
                    totalCompra+=producto.getTotal();
                    cantidad+=producto.getCantidad();
                    textTotal.setText(String.valueOf(totalCompra));
                    textCantidad.setText(String.valueOf(cantidad));
                    tabladetallesComprarProductos.refresh();
                }
                if(!tabladetallesComprarProductos.getItems().isEmpty()){
                    botonConfirmarCompra.setDisable(false);
                }else {
                    botonConfirmarCompra.setDisable(true);
                    botoneliminar.setDisable(true);
                }
            }
        });

        tablaComprarProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                botonagregar.setDisable(false);
                botoneliminar.setDisable(true);
            }
        });

        tabladetallesComprarProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                botoneliminar.setDisable(false);
            }else{
                botoneliminar.setDisable(true);
            }
        });



        botonConfirmarCompra.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar compra");
            alert.setContentText("??Desea realizar la compra?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Compra compra = new Compra();
                try {
                    compra = construirCompra(compra);
                } catch (Exception e) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error de conexi??n");
                    alert1.setContentText("Ooops, algo sali?? mal al conectarse con la base de datos!");
                    alert1.showAndWait();
                    return ;
                }
                CompraDAO compraDAO = new CompraDAOImplement();
                try {
                    compraDAO.create(compra);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                compra.setProductosCompra(listaProductosComprados);
                for (ProductoCompra productoComprado: listaProductosComprados){
                    productoComprado.setCompra(compra);
                }

                for (ProductoCompra productoComprado: listaProductosComprados){
                    System.out.println(productoComprado.toString());
                }

                for (Producto listaProducto : listaProductos) {
                    ProductoDAO productoDAO= new ProductoDAOImplement();
                    try {
                        productoDAO.actualizarProductos(listaProducto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for(ProductoCompra producto : listaProductosComprados){
                    ProductoCompraDAO productoCompraDAO = new ProductoCompraDAOImplement();
                    try {
                        productoCompraDAO.actualizarProductosComprados(producto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //usuarioLogeado.setCompras(listaCompras);


                for(ProductoStock productoStock : listaProductosStock){
                    ProductoStockDAO productoStockDAO = new ProductoStockDAOImplement();
                    try {
                        System.out.println(productoStock.toString());
                        productoStockDAO.actualizarProductosStock(productoStock);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("******************************************************");
                listaProductosComprados.clear();
                listaProductosStock.clear();
                tabladetallesComprarProductos.setItems(FXCollections.observableArrayList(listaProductosComprados));
                textCantidad.setText("0");
                textTotal.setText("0");
            } else {

            }

        });

        areaDetallesCompra.getChildren().addAll(labelfecha, textFecha, labelCantidad, textCantidad, labelTotal, textTotal, botonConfirmarCompra);
        contenedorDetallesCompra.getChildren().addAll(tabladetallesComprarProductos, areaDetallesCompra);
        contenedorBotonesAgregarCancelar.getChildren().addAll(botoneliminar, botonagregar);
        contenedorLabelComboBox.getChildren().addAll(labelProvedor, comboBoxProvedor);
        contenedorTablaComboBox.getChildren().addAll(tablaComprarProductos, contenedorLabelComboBox);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox, contenedorBotonesAgregarCancelar, contenedorDetallesCompra);
        areaComprarProductos.getChildren().addAll(contenedorTablaFormulario);


    }

    private double precioProductoEliminar(int noProducto) {
        for(Producto producto : listaProductos){
            if(noProducto ==  producto.getNoProducto()){
                return producto.getPrecio();
            }
        }
        return -1;
    }

    public ProductoCompra construirProductoCompra(Producto producto){
        ProductoCompra productoCompra = new ProductoCompra();
        //productoCompra.setCompra(compraActual);
        productoCompra.setNoProducto(producto.getNoProducto());
        productoCompra.setNombreProducto(producto.getNombreProducto());
        productoCompra.setCantidad(1);
        return productoCompra;
    }

    public ProductoStock construirProductoStock(Producto producto){
        productoStock = new ProductoStock();
        productoStock.setNoProductoStock(producto.getNoProducto());
        productoStock.setNombreProducto(producto.getNombreProducto());
        productoStock.setCantidad(1);
        productoStock.setTipo(producto.getTipo());
        productoStock.setPrecio(producto.getPrecio()+20);
        return  productoStock;
    }

    public Compra construirCompra(Compra compra) throws Exception{
        CompraDAO compraDAO = new CompraDAOImplement();
        compra.setNoCompra(compraDAO.recuperarUltimaCompra());
        //compra.setUsuario(usuarioLogeado);
        compra.setCantidad(cantidad);
        compra.setFecha(fechaActual);
        compra.setTotal(totalCompra);
        return compra;
    }

    public int productoEnLista(ProductoCompra productoCompra){
        for(int i=0;i<listaProductosComprados.size();i++){
            if(listaProductosComprados.get(i).getNoProducto()==productoCompra.getNoProducto()){
                return i;
            }
        }
        return -1;
    }

}

