package vista;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Compra;
import modelo.CompraDAO;
import modelo.CompraDAOImplement;

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

public class tabComprasRealizadas extends Tab {

    AnchorPane areaCompras;
    double ancho;
    double alto;

    TableView tablaCompras;
    CompraDAO compraDAO = new CompraDAOImplement();
    List<Compra> listaTabCompras = new ArrayList<>();

    Calendar cal = new GregorianCalendar();
    Date fechaActual = new java.sql.Date(cal.getTime().getTime());

    public tabComprasRealizadas(String text, AnchorPane areaCompras, double ancho, double alto) {
        super(text, areaCompras);
        this.areaCompras = areaCompras;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void  componentesTabComprasRealizadas(){
        areaCompras.setPrefSize(ancho, alto);

        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, ancho);
        HBox contenedorTablaComboBox = new HBox();
        contenedorTablaComboBox.setSpacing(20);
        VBox contenedorLabelComboBox = new VBox();
        contenedorLabelComboBox.setSpacing(20);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));

        Label labelFecha = new Label("Mostrar compras por Fecha:");
        labelFecha.setFont(Font.font("Arial", 16));
        labelFecha.setStyle("-fx-font-weight: bold");

        Label labelTodasCompras = new Label("Mostrar todas las compras:");
        labelTodasCompras.setFont(Font.font("Arial", 16));
        labelTodasCompras.setStyle("-fx-font-weight: bold");

        Button botonTodasCompras  = new Button("Mostrar ");

        Label labelReporte = new Label("Generar reporte de compras:");
        labelReporte.setFont(Font.font("Arial", 16));
        labelReporte.setStyle("-fx-font-weight: bold");

        Button botonGenerarReporte  = new Button("Reporte ");

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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de conexión");
                alert.setContentText("Ooops, algo salió mal al conectarse con la base de datos!");
                alert.showAndWait();
            }
            tablaCompras.setItems(FXCollections.observableArrayList(listaTabCompras));
            datePickerFecha.getEditor().setDisable(false);
        });


        botonTodasCompras.setOnAction(event -> {
            try {
                listaTabCompras = compraDAO.listaCompras();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de conexión");
                alert.setContentText("Ooops, algo salió mal al conectarse con la base de datos!");
                alert.showAndWait();
            }
            tablaCompras.setItems(FXCollections.observableArrayList(listaTabCompras));
            datePickerFecha.getEditor().setDisable(true);
        });

        botonGenerarReporte.setOnAction(event -> {
            if(!listaTabCompras.isEmpty()){
                BufferedWriter bw = null;
                FileWriter fw = null;
                double total = 0;
                try {
                    StringBuilder corte= new StringBuilder();
                    for(int i=0;i<listaTabCompras.size();i++){
                        corte.append(listaTabCompras.get(i).toString()).append("\n");
                        total=total+listaTabCompras.get(i).getTotal();
                    }
                    corte.append("Fecha del reporte: ").append(fechaActual).append("\n");
                    corte.append("Total:"+total).append("\n");
                    corte.append("**********************************************************************************************************************************************************************************************************************************+").append("\n\n");
                    File file = new File("src/CortesCaja/ReporteCompras.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    bw.write(String.valueOf(corte));

                    if(file.exists() && tablaCompras != null){
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
                alert.setContentText("No hay compras seleccionadas");
                alert.showAndWait();

        }
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


        contenedorLabelComboBox.getChildren().addAll(labelFecha, datePickerFecha, labelTodasCompras,botonTodasCompras, labelReporte,botonGenerarReporte);
        contenedorTablaComboBox.getChildren().addAll(tablaCompras, contenedorLabelComboBox);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox);
        areaCompras.getChildren().addAll(contenedorTablaFormulario);
    }
}
