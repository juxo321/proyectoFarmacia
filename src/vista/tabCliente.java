package vista;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.ClienteDAOImplement;

import java.util.ArrayList;
import java.util.List;

public class tabCliente extends Tab {
    AnchorPane areaListaClientes;
    double ancho;
    double alto;

    TableView<Cliente> tablaClientes;
    List<Cliente> listaClientes = new ArrayList<Cliente>();
    ClienteDAO clienteDAO = new ClienteDAOImplement();

    public tabCliente() {
    }

    public tabCliente(String text, AnchorPane areaListaClientes, double ancho, double alto) {
        super(text, areaListaClientes);
        this.areaListaClientes = areaListaClientes;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void componentesTabCliente() {
        areaListaClientes.setPrefSize(ancho, alto);

        VBox contenedorTablaFormulario = new VBox();
        HBox contenedorBusqueda = new HBox();
        contenedorBusqueda.setPadding(new Insets(20, 20, 20, 20));
        contenedorBusqueda.setSpacing(20);
        contenedorTablaFormulario.setPrefSize(960, alto);
        contenedorTablaFormulario.setPadding(new Insets(20, 20, 20, 20));

        tablaClientes = new TableView();
        tablaClientes.setPrefSize(800, 400);

        AnchorPane areaRegistrarCliente = new AnchorPane();

        Label labelRegistrarCliente = new Label("Registrar cliente");
        labelRegistrarCliente.setLayoutX(400);
        labelRegistrarCliente.setLayoutY(50);
        labelRegistrarCliente.setFont(Font.font("Verdana", 20));
        labelRegistrarCliente.setTextFill(Color.web("#000000"));
        labelRegistrarCliente.setStyle("-fx-font-weight: bold");

        Label labelBuscar = new Label("Buscar:");
        TextField textBuscar = new TextField();
        Button botonBuscar = new Button("Buscar");
        botonBuscar.setDisable(true);

        Label labelNombre = new Label("Nombre:");
        labelNombre.setLayoutX(20);
        labelNombre.setLayoutY(150);
        Label labelEdad = new Label("Edad:");
        labelEdad.setLayoutX(300);
        labelEdad.setLayoutY(150);
        Label labelDireccion = new Label("Direccion:");
        labelDireccion.setLayoutX(450);
        labelDireccion.setLayoutY(150);

        TextField textNombre = new TextField();
        textNombre.setLayoutX(90);
        textNombre.setLayoutY(145);
        TextField textEdad = new TextField();
        textEdad.setLayoutX(350);
        textEdad.setLayoutY(145);
        textEdad.setPrefWidth(80);
        TextField textDireccion = new TextField();
        textDireccion.setLayoutX(530);
        textDireccion.setLayoutY(145);
        textDireccion.setPrefWidth(250);

        Button botonRegistrarCliente = new Button("Registrar");
        botonRegistrarCliente.setLayoutX(820);
        botonRegistrarCliente.setLayoutY(145);

        textBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
               if(textBuscar.getText().isEmpty()){
                   botonBuscar.setDisable(true);
               }else{
                   botonBuscar.setDisable(false);
               }
            }
        });

        botonRegistrarCliente.setOnAction(event -> {
            if (textNombre.getText().trim().length() > 0 && textEdad.getText().trim().length() > 0 && textDireccion.getText().trim().length() > 0) {
                Cliente cliente = new Cliente();
                cliente.setNombreCliente(textNombre.getText());
                cliente.setEdad(Integer.parseInt(textEdad.getText()));
                cliente.setDireccion(textDireccion.getText());
                try {
                    clienteDAO.create(cliente);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    listaClientes=clienteDAO.listaClientes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tablaClientes.setItems(FXCollections.observableArrayList(listaClientes));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Datos faltantes");
                alert.setHeaderText(null);
                alert.setContentText("Por favor rellene todos los campos");
                alert.showAndWait();
            }
        });

        textEdad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textEdad.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        textBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textBuscar.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        textBuscar.setOnKeyTyped(event -> {
            if(textBuscar.getText().trim().length()==0){
                try {
                    listaClientes=clienteDAO.listaClientes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tablaClientes.setItems(FXCollections.observableArrayList(listaClientes));
            }
        });

        botonBuscar.setOnAction(event -> {
            if(textBuscar.getText().trim().length()!=0){
                try {
                    listaClientes=clienteDAO.listaClientesPorId(Integer.parseInt(textBuscar.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tablaClientes.setItems(FXCollections.observableArrayList(listaClientes));
            }
        });

        TableColumn<Cliente, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaId.setPrefWidth(200);

        TableColumn<Cliente, Integer> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        columnaNombre.setPrefWidth(250);

        TableColumn<Cliente, Integer> columnaEdad = new TableColumn<>("Edad");
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columnaEdad.setPrefWidth(200);

        TableColumn<Cliente, String>  columnaDireccion = new TableColumn<>("Direccion");
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnaDireccion.setPrefWidth(270);

       // listaClientes=tabClienteHelper.listaClientes();
        tablaClientes.setItems(FXCollections.observableArrayList(listaClientes));
        tablaClientes.getColumns().addAll(columnaId,columnaNombre,columnaEdad,columnaDireccion);

        areaRegistrarCliente.getChildren().addAll(labelRegistrarCliente,labelNombre,textNombre,labelEdad,textEdad,labelDireccion,textDireccion,botonRegistrarCliente);
        contenedorBusqueda.getChildren().addAll(labelBuscar,textBuscar,botonBuscar);
        contenedorTablaFormulario.getChildren().addAll(contenedorBusqueda,tablaClientes, areaRegistrarCliente);
        areaListaClientes.getChildren().addAll(contenedorTablaFormulario);
    }
}
