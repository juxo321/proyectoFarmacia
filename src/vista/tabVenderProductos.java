package vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class tabVenderProductos extends Tab{

    AnchorPane areaVenderProductos;
    double ancho;
    double alto;

    double totalVenta;
    double totalDescuento;
    int cantidad;

    Calendar cal = new GregorianCalendar();
    Date fechaActual = new java.sql.Date(cal.getTime().getTime());

    ProductoVenta productoVenta = new ProductoVenta();

    List<ProductoStock> listaProductosStock = new ArrayList<>();

    TableView tablaVenderProductos;
    TableView tabladetallesVenderProductos;

    List<ProductoVenta> listaProductosVendidos = new ArrayList<>();

    ProductoStockDAO productoStockDAO = new ProductoStockDAOImplement();

    int indexproductoVenta=0;

    int contadorVenta=1;
    int contadorId2=1;

    List<String> listaIdClientes = new ArrayList<>();

    TextField textTotal;
    TextField textTotalDescuento;

    public tabVenderProductos(String text, AnchorPane areaVenderProductos, double ancho, double alto) {
        super(text, areaVenderProductos);
        this.areaVenderProductos = areaVenderProductos;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void componentesTabVentas(){
        areaVenderProductos.setPrefSize(ancho, alto);
        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, ancho);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));
        HBox contenedorBotonesAgregarCancelar = new HBox();
        HBox contenedorDetallesVenta = new HBox();
        contenedorDetallesVenta.setSpacing(20);
        AnchorPane areaDetallesVenta = new AnchorPane();

        tablaVenderProductos = new TableView();
        tablaVenderProductos.setPrefSize(800, 300);

        Button botonagregar = new Button("Agregar");
        Button botonConfirmarVenta = new Button("Confirmar Venta");
        Button botoneliminar = new Button("Eliminar");

        botonagregar.setDisable(true);
        botoneliminar.setDisable(true);

        Label labelfecha = new Label("Fecha:");
        labelfecha.setLayoutX(30);
        labelfecha.setLayoutY(50);
        TextField textFecha = new TextField();
        textFecha.setLayoutX(100);
        textFecha.setLayoutY(50);
        textFecha.setEditable(false);
        textFecha.setText(fechaActual.toString());
        Label labelCantidad = new Label("Cantidad:");
        labelCantidad.setLayoutX(30);
        labelCantidad.setLayoutY(100);
        TextField textCantidad = new TextField();
        textCantidad.setLayoutX(100);
        textCantidad.setLayoutY(100);
        textCantidad.setEditable(false);
        Label labelTotal = new Label("Total:");
        labelTotal.setLayoutX(30);
        labelTotal.setLayoutY(150);
        textTotal = new TextField();
        textTotal.setLayoutX(100);
        textTotal.setLayoutY(150);
        textTotal.setEditable(false);
        Label labelIdCliente = new Label("ID cliente: ");
        labelIdCliente.setLayoutX(30);
        labelIdCliente.setLayoutY(200);
        ComboBox<String> textIdCliente = new ComboBox<>();
        textIdCliente.setTooltip(new Tooltip("Seleccione un ID de cliente para aplicar un descuento sobre el total"));
        textIdCliente.setPrefWidth(70);
        textIdCliente.setLayoutX(110);
        textIdCliente.setLayoutY(200);
        textIdCliente.setDisable(true);
        ClienteDAO clienteDAO = new ClienteDAOImplement();
        textIdCliente.setOnMouseClicked(event -> {
            try {
                listaIdClientes = clienteDAO.IdDeClientes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            textIdCliente.getItems().clear();
            textIdCliente.getItems().addAll(listaIdClientes);
        });

        textTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()){
               if(newValue.equals("0")){
                   textIdCliente.setDisable(true);
               }else {
                   textIdCliente.setDisable(false);
                   if(!textTotalDescuento.isDisable()){
                       totalDescuento = Double.parseDouble(textTotal.getText())* .90;
                       textTotalDescuento.setText(String.valueOf(totalDescuento));
                   }
               }
            }else {
                textIdCliente.setDisable(false);
            }
        });

        Label labelTotalDescuento = new Label("Total con descuento:");
        labelTotalDescuento.setLayoutX(30);
        labelTotalDescuento.setLayoutY(250);
        textTotalDescuento = new TextField();
        textTotalDescuento.setLayoutX(180);
        textTotalDescuento.setLayoutY(250);
        textTotalDescuento.setPrefWidth(150);
        textTotalDescuento.setEditable(false);
        textTotalDescuento.setDisable(true);

        textIdCliente.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue != null){
                textTotalDescuento.setDisable(false);
                totalDescuento = Double.parseDouble(textTotal.getText())* .90;
                textTotalDescuento.setText(String.valueOf(totalDescuento));
                textTotal.setDisable(true);
            }else {
                textTotalDescuento.setDisable(true);
                textTotal.setDisable(false);
            }
        });


        botonConfirmarVenta.setLayoutX(100);
        botonConfirmarVenta.setLayoutY(330);




        TableColumn<ProductoStock, Integer> columnaNoProducto = new TableColumn<>("No.Producto");
        columnaNoProducto.setCellValueFactory(new PropertyValueFactory<>("noProductoStock"));
        columnaNoProducto.setPrefWidth(200);

        TableColumn<ProductoStock, String> columnaNombreProducto = new TableColumn<>("Nombre Producto");
        columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaNombreProducto.setPrefWidth(200);

        TableColumn<ProductoStock, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaCantidad.setPrefWidth(150);

        TableColumn<ProductoStock, String> columnaTipo = new TableColumn<>("Tipo");
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnaTipo.setPrefWidth(150);

        TableColumn<ProductoStock, Double> columnaPrecio = new TableColumn<>("Precio");
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaPrecio.setPrefWidth(150);

        TableColumn<ProductoStock, Integer> columnaNoProvedor = new TableColumn<>("No.Provedor");
        columnaNoProvedor.setCellValueFactory(new PropertyValueFactory<>("noProvedor"));
        columnaNoProvedor.setPrefWidth(110);



        try {
            listaProductosStock = productoStockDAO.obtenerProductosStock();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablaVenderProductos.setItems(FXCollections.observableArrayList(listaProductosStock));


        tablaVenderProductos.getColumns().addAll(columnaNoProducto, columnaNombreProducto, columnaCantidad, columnaTipo,columnaPrecio);


        contenedorBotonesAgregarCancelar.setAlignment(Pos.CENTER_RIGHT);
        contenedorBotonesAgregarCancelar.setPadding(new Insets(10,10,10,10));
        contenedorBotonesAgregarCancelar.setSpacing(10);

        tabladetallesVenderProductos = new TableView();
        tabladetallesVenderProductos.setPrefSize(600, 410);


        TableColumn<ProductoVenta, Integer> columnaCantidad2 = new TableColumn<>("No.Producto");
        columnaCantidad2.setCellValueFactory(new PropertyValueFactory<>("noProducto"));
        columnaCantidad2.setPrefWidth(200);

        TableColumn<ProductoVenta, String> columnaProducto2 = new TableColumn<>("Nombre Producto");
        columnaProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaProducto2.setPrefWidth(200);

        TableColumn<ProductoVenta, Integer> columnaPrecioUnitario2 = new TableColumn<>("Cantidad");
        columnaPrecioUnitario2.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<ProductoVenta, String> columnaTotal2 = new TableColumn<>("Total");
        columnaTotal2.setCellValueFactory(new PropertyValueFactory<>("Total"));
        columnaTotal2.setPrefWidth(150);

        tabladetallesVenderProductos.getColumns().addAll(columnaCantidad2,columnaProducto2,columnaPrecioUnitario2, columnaTotal2);

        TableView.TableViewSelectionModel<ProductoStock> modeloSeleccion = tablaVenderProductos.getSelectionModel();
        ObservableList<ProductoStock> productoSeleccionado = modeloSeleccion.getSelectedItems();
        modeloSeleccion.setSelectionMode(SelectionMode.SINGLE);

        //Modelo de selección para la segunda tabla

        TableView.TableViewSelectionModel<ProductoVenta> modeloSeleccion2 = tabladetallesVenderProductos.getSelectionModel();
        modeloSeleccion2.setSelectionMode(SelectionMode.SINGLE);

        ObservableList<ProductoVenta> productoSeleccionado2 = modeloSeleccion2.getSelectedItems();

        botonagregar.setOnAction(event -> {
            productoVenta = construirProductoVenta(productoSeleccionado.get(0));
            indexproductoVenta= productoEnLista(productoVenta);
            if(indexproductoVenta!=-1){
                if(productoSeleccionado.get(0).getCantidad()>0){
                    listaProductosVendidos.get(indexproductoVenta).setCantidad(listaProductosVendidos.get(indexproductoVenta).getCantidad()+1);
                    productoSeleccionado.get(0).setCantidad(productoSeleccionado.get(0).getCantidad()-1);
                    tablaVenderProductos.refresh();
                    listaProductosVendidos.get(indexproductoVenta).setTotal(productoSeleccionado.get(0).getPrecio()*listaProductosVendidos.get(indexproductoVenta).getCantidad());
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cantidad insuficiente");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficientes productos para agregar");
                    alert.showAndWait();
                }
            }else{
                productoVenta.setTotal(productoSeleccionado.get(0).getPrecio());
                listaProductosVendidos.add(productoVenta);
                productoSeleccionado.get(0).setCantidad(productoSeleccionado.get(0).getCantidad()-1);
                tablaVenderProductos.refresh();
            }
            totalVenta=0;
            cantidad =0;

            for (ProductoVenta producto : listaProductosVendidos){
                totalVenta+=producto.getTotal();
                cantidad+=producto.getCantidad();
                if(!tabladetallesVenderProductos.getItems().contains(producto)){
                    tabladetallesVenderProductos.getItems().addAll(producto);
                    tabladetallesVenderProductos.refresh();
                }
                textTotal.setText(String.valueOf(totalVenta));
                textCantidad.setText(String.valueOf(cantidad));
                tabladetallesVenderProductos.refresh();
            }
        });

        botoneliminar.setOnAction(event -> {
            if(!tabladetallesVenderProductos.getItems().isEmpty()){
                int indexProductoComprado2 = productoEnLista(productoSeleccionado2.get(0));
                if(productoSeleccionado2.get(0).getCantidad()>0){
                    listaProductosVendidos.get(indexProductoComprado2).setCantidad(listaProductosVendidos.get(indexProductoComprado2).getCantidad()-1);
                    for(ProductoStock producto : listaProductosStock){
                        if(productoSeleccionado2.get(0).getNoProducto() == producto.getNoProductoStock()){
                            producto.setCantidad(producto.getCantidad()+1);
                        }
                    }
                    double precioProductoEliminar = precioProductoEliminar(productoSeleccionado2.get(0).getNoProducto());
                    listaProductosVendidos.get(indexProductoComprado2).setTotal(listaProductosVendidos.get(indexProductoComprado2).getTotal()-precioProductoEliminar);
                    if(productoSeleccionado2.get(0).getCantidad() == 0){
                        listaProductosVendidos.remove(indexProductoComprado2);
                        tabladetallesVenderProductos.setItems(FXCollections.observableArrayList(listaProductosVendidos));
                        textTotal.setText("0");
                        textCantidad.setText("0");
                    }
                    tablaVenderProductos.refresh();
                    tabladetallesVenderProductos.refresh();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cantidad insuficiente");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficientes productos para eliminar");
                    alert.showAndWait();
                }

                totalVenta=0;
                cantidad =0;


                for (ProductoVenta producto : listaProductosVendidos){
                    totalVenta+=producto.getTotal();
                    cantidad+=producto.getCantidad();
                    textTotal.setText(String.valueOf(totalVenta));
                    textCantidad.setText(String.valueOf(cantidad));
                    tabladetallesVenderProductos.refresh();
                }
                if(!tabladetallesVenderProductos.getItems().isEmpty()){
                    botonConfirmarVenta.setDisable(false);
                }else {
                    botonConfirmarVenta.setDisable(true);
                    botoneliminar.setDisable(true);
                }
            }
        });

        tablaVenderProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                botonagregar.setDisable(false);
                botoneliminar.setDisable(true);
            }
        });

        tabladetallesVenderProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                botoneliminar.setDisable(false);
            }else{
                botoneliminar.setDisable(true);
            }
        });

        botonConfirmarVenta.setOnAction(event -> {
            Venta venta = new Venta();
            venta = construirVenta(venta);
            VentaDAO ventaDAO = new VentaDAOImplement();
            try {
                ventaDAO.create(venta);
            } catch (Exception e) {
                e.printStackTrace();
            }

            venta.setProductosVenta(listaProductosVendidos);

            for (ProductoVenta productoVendido: listaProductosVendidos){
                productoVendido.setVenta(venta);
            }

            for (ProductoStock producto : listaProductosStock) {
                ProductoStockDAO productoStockDAO = new ProductoStockDAOImplement();
                try {
                    productoStockDAO.actualizarProductosStockVenta(producto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for(ProductoVenta producto : listaProductosVendidos){
                ProductoVentaDAO productoVentaDAO = new ProductoVentaDAOImplement();
                try {
                    productoVentaDAO.actualizarProductosVendidos(producto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            listaProductosVendidos.clear();
            tabladetallesVenderProductos.setItems(FXCollections.observableArrayList(listaProductosVendidos));
            textCantidad.setText("0");
            textTotal.setText("0");
            textTotalDescuento.setText("0");
        });

        areaVenderProductos.setOnMouseEntered(event -> {
            try {
                listaProductosStock = productoStockDAO.obtenerProductosStock();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaVenderProductos.getItems().clear();
            tablaVenderProductos.setItems(FXCollections.observableArrayList(listaProductosStock));
            listaProductosStock.clear();
        });

        areaDetallesVenta.getChildren().addAll(labelfecha, textFecha, labelCantidad, textCantidad, labelTotal, textTotal, labelIdCliente, textIdCliente, labelTotalDescuento,textTotalDescuento,  botonConfirmarVenta);
        contenedorDetallesVenta.getChildren().addAll(tabladetallesVenderProductos, areaDetallesVenta);
        contenedorBotonesAgregarCancelar.getChildren().addAll( botoneliminar, botonagregar);
        contenedorTablaFormulario.getChildren().addAll(tablaVenderProductos, contenedorBotonesAgregarCancelar, contenedorDetallesVenta);
        areaVenderProductos.getChildren().addAll(contenedorTablaFormulario);

    }

    public ProductoVenta construirProductoVenta(ProductoStock producto){
        ProductoVenta productoVenta = new ProductoVenta();
        //productoVenta.setVenta(ventaActual);
        productoVenta.setNoProducto(producto.getNoProductoStock());
        productoVenta.setNombreProducto(producto.getNombreProducto());
        productoVenta.setCantidad(1);
        return productoVenta;
    }

    private double precioProductoEliminar(int noProducto) {
        for(ProductoStock producto : listaProductosStock){
            if(noProducto ==  producto.getNoProductoStock()){
                return producto.getPrecio();
            }
        }
        return -1;
    }

    public Venta construirVenta(Venta venta) {
        VentaDAO ventaDAO = new VentaDAOImplement();
        try {
            venta.setNoVenta(ventaDAO.recuperarUltimaVenta());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //venta.setUsuario(usuarioLogeado);
        venta.setCantidad(cantidad);
        venta.setFecha(fechaActual);
        if(textTotalDescuento.isDisable()){
            venta.setTotal(totalVenta);
        }else {
            venta.setTotal(totalDescuento);
        }
        return venta;
    }

    public int productoEnLista(ProductoVenta productoVenta){
        for(int i=0;i<listaProductosVendidos.size();i++){
            if(listaProductosVendidos.get(i).getNoProducto()==productoVenta.getNoProducto()){
                return i;
            }
        }
        return -1;
    }
}
