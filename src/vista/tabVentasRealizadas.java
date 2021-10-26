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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class tabVentasRealizadas extends Tab {

    AnchorPane areaVentas;
    double ancho;
    double alto;

    TableView tablaVentas;
    VentaDAO ventaDAO = new VentaDAOImplement();
    List<Venta> listaTabVentas = new ArrayList<>();

    Calendar cal = new GregorianCalendar();
    Date fechaActual = new java.sql.Date(cal.getTime().getTime());

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


        Label labelFecha = new Label("Mostrar ventas por Fecha:");
        labelFecha.setFont(Font.font("Arial", 16));
        labelFecha.setStyle("-fx-font-weight: bold");

        Label labelTodasVentas = new Label("Mostrar todas las ventas:");
        labelTodasVentas.setFont(Font.font("Arial", 16));
        labelTodasVentas.setStyle("-fx-font-weight: bold");
        Button botonTodasVentas  = new Button("Mostrar");

        Label labelReporte = new Label("Generar reporte de ventas:");
        labelReporte.setFont(Font.font("Arial", 16));
        labelReporte.setStyle("-fx-font-weight: bold");

        Button botonGenerarReporte  = new Button("Reporte ");

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
            datePickerFecha.getEditor().setDisable(false);
        });


        botonTodasVentas.setOnAction(event -> {
            try {
                listaTabVentas = ventaDAO.listaVentas();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaVentas.setItems(FXCollections.observableArrayList(listaTabVentas));
            datePickerFecha.getEditor().setDisable(true);
        });

        botonGenerarReporte.setOnAction(event -> {
            if(!listaTabVentas.isEmpty()){
                BufferedWriter bw = null;
                FileWriter fw = null;
                double total = 0;
                try {
                    StringBuilder corte= new StringBuilder();
                    for(int i=0;i<listaTabVentas.size();i++){
                        corte.append(listaTabVentas.get(i).toString()).append("\n");
                        total=total+listaTabVentas.get(i).getTotal();
                    }
                    corte.append("Fecha del reporte: ").append(fechaActual).append("\n");
                    corte.append("Total:"+total).append("\n");
                    corte.append("**********************************************************************************************************************************************************************************************************************************+").append("\n\n");
                    File file = new File("src/CortesCaja/ReporteVentas.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    bw.write(String.valueOf(corte));

                    if(file.exists() && tablaVentas != null){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Reporte");
                        alert.setHeaderText(null);
                        alert.setContentText("Reporte generado exitosamente.");
                        alert.showAndWait();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bw != null)
                            bw.close();
                        if (fw != null)
                            fw.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("No hay ventas seleccionadas");
                alert.showAndWait();

            }
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


        contenedorLabelComboBox.getChildren().addAll(labelFecha, datePickerFecha, labelTodasVentas,botonTodasVentas, labelReporte, botonGenerarReporte);
        contenedorTablaComboBox.getChildren().addAll(tablaVentas, contenedorLabelComboBox);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox);
        areaVentas.getChildren().addAll(contenedorTablaFormulario);
    }
}
