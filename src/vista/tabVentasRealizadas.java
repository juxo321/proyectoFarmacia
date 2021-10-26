package vista;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelo.Venta;
import modelo.VentaDAO;
import modelo.VentaDAOImplement;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class tabVentasRealizadas extends Tab {

    AnchorPane areaVentas;
    double ancho;
    double alto;

    TableView tablaVentas;
    VentaDAO ventaDAO = new VentaDAOImplement();
    List<Venta> listaTabVentas = new ArrayList<>();

    public tabVentasRealizadas(String text, AnchorPane areaVentas, double ancho, double alto) {
        super(text, areaVentas);
        this.areaVentas = areaVentas;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void  componentesTabVentasRealizadas(){
        areaVentas.setPrefSize(ancho, alto);

        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, ancho);
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

            try {
                listaTabVentas = ventaDAO.listaVentasPorFecha(fechaBuscar);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaVentas.setItems(FXCollections.observableArrayList(listaTabVentas));
        });

        botonTodasVentas.setOnAction(event -> {
            try {
                listaTabVentas = ventaDAO.listaVentas();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaVentas.setItems(FXCollections.observableArrayList(listaTabVentas));
        });

        TableColumn<Venta, Integer> columnaNoVenta = new TableColumn<>("No.Venta");
        columnaNoVenta.setCellValueFactory(new PropertyValueFactory<>("noVenta"));
        columnaNoVenta.setPrefWidth(200);

        TableColumn<Venta, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaCantidad.setPrefWidth(150);

        TableColumn<Venta, Date> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaFecha.setPrefWidth(150);

        TableColumn<Venta, Double> columnaTotal = new TableColumn<>("Total");
        columnaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        columnaTotal.setPrefWidth(150);


        tablaVentas.getColumns().addAll(columnaNoVenta,columnaCantidad,columnaFecha,columnaTotal);


        contenedorLabelComboBox.getChildren().addAll(labelFecha, datePickerFecha, labelTodasVentas,botonTodasVentas);
        contenedorTablaComboBox.getChildren().addAll(tablaVentas, contenedorLabelComboBox);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox);
        areaVentas.getChildren().addAll(contenedorTablaFormulario);
    }
}
