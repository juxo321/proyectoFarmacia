package vista;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import modelo.Venta;
import modelo.VentaDAO;
import modelo.VentaDAOImplement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

public class tabCorteCaja extends Tab{

    AnchorPane areaCorteCaja;
    double ancho;
    double alto;

    Calendar cal = new GregorianCalendar();
    Date fechaActual = new java.sql.Date(cal.getTime().getTime());

    TableView tablaCorteCaja;
    List<Venta> listaCorteCaja = new ArrayList<>();

    public tabCorteCaja(String text, AnchorPane areaCorteCaja, double ancho, double alto) {
        super(text, areaCorteCaja);
        this.areaCorteCaja = areaCorteCaja;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void componentesTabCorteCaja(Button botonCorteCaja){
        areaCorteCaja.setPrefSize(ancho, alto);
        VBox contenedorTablaFormulario = new VBox();
        contenedorTablaFormulario.setPrefSize(960, ancho);
        contenedorTablaFormulario.setSpacing(30);
        HBox contenedorTablaComboBox = new HBox();
        contenedorTablaComboBox.setSpacing(20);
        HBox contendedorBotonCorteCaja = new HBox();
        contendedorBotonCorteCaja.setAlignment(Pos.CENTER);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));

        tablaCorteCaja = new TableView();
        tablaCorteCaja.setPrefSize(950, 300);

        ComboBox<String> comboBoxProvedor = new ComboBox<>();
        comboBoxProvedor.getItems().addAll("1","2","3");
        Label labelProvedor = new Label("Usuario:");
        labelProvedor.setFont(Font.font("Arial", 16));
        labelProvedor.setStyle("-fx-font-weight: bold");

        Button corteCaja= new Button("Corte de caja");
        corteCaja.setAlignment(Pos.CENTER);

        VentaDAO ventaDAO = new VentaDAOImplement();

        areaCorteCaja.setOnMouseEntered(event -> {
            try {
                listaCorteCaja = ventaDAO.listaVentas();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setContentText("Ooops, error al obetenr los datos!");
                alert.showAndWait();
            }
            tablaCorteCaja.setItems(FXCollections.observableArrayList(listaCorteCaja));
        });


        corteCaja.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion");
            alert.setHeaderText("Corte de caja");
            alert.setContentText("Esta seguro de realizar el corte de caja, al aceptar se cerrara la sesión");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                if(!listaCorteCaja.isEmpty()){
                    BufferedWriter bw = null;
                    FileWriter fw = null;
                    double total = 0;
                    try {
                        StringBuilder corte= new StringBuilder();
                        for(int i=0;i<listaCorteCaja.size();i++){
                            corte.append(listaCorteCaja.get(i).toString()).append("\n");
                            total=total+listaCorteCaja.get(i).getTotal();
                        }
                        corte.append("Fecha del corte: ").append(fechaActual);
                        corte.append("Total:"+total).append("\n");
                        corte.append("**********************************************************************************************************************************************************************************************************************************+").append("\n\n");
                        File file = new File("src/CortesCaja/cortesDeCaja.txt");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        fw = new FileWriter(file.getAbsoluteFile(), true);
                        bw = new BufferedWriter(fw);
                        bw.write(String.valueOf(corte));
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
                    Login pantallaInicio = new Login();
                    Stage stage = (Stage) botonCorteCaja.getScene().getWindow();
                    stage.close();
                    Stage stage1 = new Stage();
                    Login pantallaLogin = new Login();
                    Scene scene = new Scene(pantallaLogin, 300, 275);
                    stage1.setScene(scene);
                    stage1.show();
                }else{
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Datos");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay ventas realizadas");
                    alert.showAndWait();
                }
            } else {
            }


        });

        TableColumn<Venta, Integer> columnaNoCompra = new TableColumn<>("Numero de venta");
        columnaNoCompra.setCellValueFactory(new PropertyValueFactory<>("noVenta"));
        columnaNoCompra.setPrefWidth(200);

        TableColumn<Venta, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaCantidad.setPrefWidth(250);

        TableColumn<Venta, Date> columnaFecha = new TableColumn<>("Fecha");
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaFecha.setPrefWidth(250);

        TableColumn<Venta, Double> columnaTotal = new TableColumn<>("Total");
        columnaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        columnaTotal.setPrefWidth(210);


        tablaCorteCaja.getColumns().addAll(columnaNoCompra,columnaCantidad,columnaFecha,columnaTotal);

        contendedorBotonCorteCaja.getChildren().setAll(corteCaja);
        contenedorTablaComboBox.getChildren().addAll(tablaCorteCaja);
        contenedorTablaFormulario.getChildren().addAll(contenedorTablaComboBox, contendedorBotonCorteCaja);
        areaCorteCaja.getChildren().addAll(contenedorTablaFormulario);

    }
}
