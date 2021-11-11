package vista;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.ClienteDAOImplement;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class tabCliente extends Tab {
    AnchorPane areaListaClientes;
    double ancho;
    double alto;

    TableView<Cliente> tablaClientes;
    List<Cliente> listaClientes = new ArrayList<Cliente>();
    ClienteDAO clienteDAO = new ClienteDAOImplement();

    ObservableList<Cliente> clienteSeleccionado;

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

        VBox areaTituloBotones = new VBox();
        areaTituloBotones.setSpacing(50);
        areaTituloBotones.setPadding(new Insets(20,20,20,20));

        HBox contenedorBotonesAdministrarCliente = new HBox();
        contenedorBotonesAdministrarCliente.setSpacing(20);

        Label labelRegistrarCliente = new Label("Administrar clientes");
        labelRegistrarCliente.setLayoutX(400);
        labelRegistrarCliente.setLayoutY(50);
        labelRegistrarCliente.setFont(Font.font("Verdana", 20));
        labelRegistrarCliente.setTextFill(Color.web("#000000"));
        labelRegistrarCliente.setStyle("-fx-font-weight: bold");

        Label labelBuscar = new Label("Buscar:");
        TextField textBuscar = new TextField();
        Button botonBuscar = new Button("Buscar");
        botonBuscar.setDisable(true);
        
        Button botonRegistrarCliente = new Button("Registrar cliente");
        Button botonModificarCliente = new Button("Modificar Cliente");
        Button botonEliminarCliente = new Button("Eliminar Cliente");

        botonModificarCliente.setDisable(true);
        botonEliminarCliente.setDisable(true);

        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                botonEliminarCliente.setDisable(false);
                botonModificarCliente.setDisable(false);
            }else {
                botonEliminarCliente.setDisable(true);
                botonModificarCliente.setDisable(true);
            }
        });


        botonRegistrarCliente.setOnAction(event -> {
            componentesRegistrarCliente();
        });

        botonModificarCliente.setOnAction(event -> {
            componentesModificarCliente();
        });

        botonEliminarCliente.setOnAction(event -> {
            componentesEliminarCliente();
        });


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


        TableView.TableViewSelectionModel<Cliente> modeloSeleccion = tablaClientes.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.SINGLE);

        clienteSeleccionado = modeloSeleccion.getSelectedItems();

        ClienteDAO clienteDAO = new ClienteDAOImplement();
        try {
            listaClientes=clienteDAO.listaClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablaClientes.setItems(FXCollections.observableArrayList(listaClientes));
        tablaClientes.getColumns().addAll(columnaId,columnaNombre,columnaEdad,columnaDireccion);

        contenedorBotonesAdministrarCliente.getChildren().addAll(botonRegistrarCliente, botonModificarCliente, botonEliminarCliente);
        areaTituloBotones.getChildren().addAll(labelRegistrarCliente, contenedorBotonesAdministrarCliente);
        contenedorBusqueda.getChildren().addAll(labelBuscar,textBuscar,botonBuscar);
        contenedorTablaFormulario.getChildren().addAll(contenedorBusqueda,tablaClientes, areaTituloBotones);
        areaListaClientes.getChildren().addAll(contenedorTablaFormulario);
    }

    public void componentesRegistrarCliente(){
        VBox pantallaAgregarCliente = new VBox();

        Stage stagePrincipal1 = new Stage();
        stagePrincipal1.setScene(new Scene(pantallaAgregarCliente, 400, 400));
        stagePrincipal1.centerOnScreen();
        stagePrincipal1.setAlwaysOnTop(false);
        stagePrincipal1.setResizable(false);
        //stagePrincipal1.initOwner();
        stagePrincipal1.initModality(Modality.APPLICATION_MODAL);

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


        Button btnAgregar = new Button("Agregar");
        Button btnCancelar = new Button("Cancelar");

        ArrayList<HBox> ObjetosEditar = new ArrayList<HBox>();

        for (int i = 0; i < 4; i++) {
            HBox Columna = new HBox();
            Columna.setSpacing(20);
            Columna.setAlignment(Pos.CENTER);
            Columna.setPadding(new Insets(20, 20, 20, 20));
            ObjetosEditar.add(i, Columna);
        }

        ObjetosEditar.get(0).getChildren().addAll(labelNombre, textNombre);
        ObjetosEditar.get(1).getChildren().addAll(labelEdad, textEdad);
        ObjetosEditar.get(2).getChildren().addAll(labelDireccion, textDireccion);
        ObjetosEditar.get(3).getChildren().addAll(btnAgregar,btnCancelar);

        btnAgregar.setOnAction(event -> {
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
                textNombre.clear();
                textDireccion.clear();
                textEdad.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cliente agregado");
                alert.setHeaderText(null);
                alert.setContentText("Cliente agregado exitosamente!");
                alert.showAndWait();
                stagePrincipal1.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Datos faltantes");
                alert.setHeaderText(null);
                alert.setContentText("Por favor rellene todos los campos");
                alert.showAndWait();
            }
        });

        btnCancelar.setOnAction(event4 -> {
            stagePrincipal1.close();

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

        pantallaAgregarCliente.getChildren().addAll(ObjetosEditar);
        stagePrincipal1.showAndWait();
    }

    public void componentesModificarCliente(){
        if(clienteSeleccionado.get(0)!=null) {

            VBox pantallaAgregarCliente = new VBox();

            Stage stagePrincipal1 = new Stage();
            stagePrincipal1.setScene(new Scene(pantallaAgregarCliente, 400, 400));
            stagePrincipal1.centerOnScreen();
            stagePrincipal1.setAlwaysOnTop(false);
            stagePrincipal1.setResizable(false);
            //stagePrincipal1.initOwner();
            stagePrincipal1.initModality(Modality.APPLICATION_MODAL);

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
            textNombre.setText(clienteSeleccionado.get(0).getNombreCliente());
            TextField textEdad = new TextField();
            textEdad.setLayoutX(350);
            textEdad.setLayoutY(145);
            textEdad.setPrefWidth(80);
            textEdad.setText(String.valueOf(clienteSeleccionado.get(0).getEdad()));
            TextField textDireccion = new TextField();
            textDireccion.setLayoutX(530);
            textDireccion.setLayoutY(145);
            textDireccion.setPrefWidth(250);
            textDireccion.setText(clienteSeleccionado.get(0).getDireccion());


            Button btnGuardar = new Button("Guardar");
            Button btnCancelar = new Button("Cancelar");

            ArrayList<HBox> ObjetosEditar = new ArrayList<HBox>();

            for (int i = 0; i < 4; i++) {
                HBox Columna = new HBox();
                Columna.setSpacing(20);
                Columna.setAlignment(Pos.CENTER);
                Columna.setPadding(new Insets(20, 20, 20, 20));
                ObjetosEditar.add(i, Columna);
            }

            ObjetosEditar.get(0).getChildren().addAll(labelNombre, textNombre);
            ObjetosEditar.get(1).getChildren().addAll(labelEdad, textEdad);
            ObjetosEditar.get(2).getChildren().addAll(labelDireccion, textDireccion);
            ObjetosEditar.get(3).getChildren().addAll(btnGuardar, btnCancelar);

            btnGuardar.setOnAction(event2 -> {
                if (textNombre.getText().trim().length() != 0 && textEdad.getText().trim().length() != 0 && textDireccion.getText().trim().length() != 0) {
                    clienteSeleccionado.get(0).setNombreCliente(textNombre.getText());
                    clienteSeleccionado.get(0).setEdad(Integer.parseInt(textEdad.getText()));
                    clienteSeleccionado.get(0).setDireccion(textDireccion.getText());
                    try {
                        if (clienteDAO.updateCliente(clienteSeleccionado.get(0))) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Cliente modificado");
                            alert.setHeaderText(null);
                            alert.setContentText("Cliente modificado exitosamente!");
                            alert.showAndWait();
                            stagePrincipal1.close();

                            try {
                                listaClientes = clienteDAO.listaClientes();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            tablaClientes.getItems().clear();
                            tablaClientes.getItems().addAll(FXCollections.observableArrayList(listaClientes));
                            listaClientes.clear();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("No se pudo modificar el Cliente!");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor rellene todos los campos");
                    alert.showAndWait();
                }
            });

            btnCancelar.setOnAction(event4 -> {
                stagePrincipal1.close();

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

            pantallaAgregarCliente.getChildren().addAll(ObjetosEditar);
            stagePrincipal1.showAndWait();
        }else{
            
        }

    }

    public void componentesEliminarCliente() {
        if (clienteSeleccionado.get(0) != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setContentText("¿Desea eliminar el cliente?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    if (clienteDAO.deleteCliente(clienteSeleccionado.get(0).getId())) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Cliente eliminado");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Cliente eliminado exitosamente!");
                        alert1.showAndWait();

                        try {
                            listaClientes = clienteDAO.listaClientes();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tablaClientes.getItems().clear();
                        tablaClientes.getItems().addAll(FXCollections.observableArrayList(listaClientes));
                        listaClientes.clear();
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setContentText("No se pudo eliminar el cliente!");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Avertencia");
                    alert1.setHeaderText(null);
                    alert1.setContentText("No se ha seleccionado ningun cliente!");
                    alert1.showAndWait();
                }
            } else {
                alert.close();
            }
        }
    }
}
