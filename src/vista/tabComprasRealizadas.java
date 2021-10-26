package vista;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelo.Compra;
import modelo.CompraDAO;
import modelo.CompraDAOImplement;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class tabComprasRealizadas extends Tab {

    AnchorPane areaCompras;
    double ancho;
    double alto;

    TableView tablaCompras;
    CompraDAO compraDAO = new CompraDAOImplement();
    List<Compra> listaTabCompras = new ArrayList<>();

    public void  componentesTabComprasRealizadas(){
        areaCompras.setPrefSize(ancho, alto);

        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, ancho);
        HBox contenedorTablaComboBox = new HBox();
        contenedorTablaComboBox.setSpacing(20);
        VBox contenedorLabelComboBox = new VBox();
        contenedorLabelComboBox.setSpacing(10);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));

        tablaCompras = new TableView();
        tablaCompras.setPrefSize(730, 400);


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


            try {
                listaTabCompras = compraDAO.listaComprasPorFecha(fechaBuscar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaCompras.setItems(FXCollections.observableArrayList(listaTabCompras));
        });

        Label labelFecha = new Label("Mostras por Fecha:");
        labelFecha.setFont(Font.font("Arial", 16));
        labelFecha.setStyle("-fx-font-weight: bold");

        Label labelTodasCompras = new Label("Mostrar todas las compras:");
        labelTodasCompras.setFont(Font.font("Arial", 16));
        labelTodasCompras.setStyle("-fx-font-weight: bold");

        Button botonTodasCompras  = new Button("Mostrar ");

        botonTodasCompras.setOnAction(event -> {
            try {
                listaTabCompras = compraDAO.listaCompras();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaCompras.setItems(FXCollections.observableArrayList(listaTabCompras));
        });


        TableColumn<Compra, Integer> columnaNoCompra = new TableColumn<>("No.CompraProducto");
        columnaNoCompra.setCellValueFactory(new PropertyValueFactory<>("noCompra"));
        columnaNoCompra.setPrefWidth(200);

        TableColumn<Compra, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaCantidad.setPrefWidth(150);

        TableColumn<Compra, Date> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaFecha.setPrefWidth(150);

        TableColumn<Compra, Double> columnaTotal = new TableColumn<>("Total");
        columnaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        columnaTotal.setPrefWidth(150);


        tablaCompras.getColumns().addAll(columnaNoCompra,columnaCantidad,columnaFecha,columnaTotal);


        contenedorLabelComboBox.getChildren().addAll(labelFecha, datePickerFecha, labelTodasCompras,botonTodasCompras);
        contenedorTablaComboBox.getChildren().addAll(tablaCompras, contenedorLabelComboBox);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox);
        areaCompras.getChildren().addAll(contenedorTablaFormulario);
    }
}
