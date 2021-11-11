package vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.*;
//import models.UsuarioEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class barraSuperior extends Pane {
    HBox contenedorPrincipal = new HBox();
    HBox contenedorImagenTitulo = new HBox();
    Pane contenedorBotones = new Pane();

    Button botonCambiarUsuario;
    Button botonSalir;
    Button botonAdministrarUsuarios;

    Button botonAgregarUsuario;
    Button botonModificarUsuario;
    Button botonEliminarUsuario;

    List<Usuario> listaUsuarios = new ArrayList<>();

    barraSuperior() {
        this.setPrefSize(1200, 150);
        this.setStyle("-fx-background-color: #" + "00adb5");

        File archivo1 = new File("src/icons/logo.png");
        Image imagen1 = new Image(archivo1.toURI().toString(), 110, 110, true, true);
        ImageView logo = new ImageView(imagen1);

        Pane tituloArea = new Pane();
        Text titulo = new Text();
        titulo.setText("FARMACIAS TOÑITO");
        titulo.setFont(Font.font("Courier New", 50));
        titulo.setFill(Color.web("#EEEEEE"));
        titulo.setStyle("-fx-font-weight: bold");
        titulo.setLayoutX(30);
        titulo.setLayoutY(75);
        tituloArea.getChildren().add(titulo);

        contenedorImagenTitulo.getChildren().addAll(logo, tituloArea);
        contenedorImagenTitulo.setPadding(new Insets(20, 20, 20, 20));

        File archivo2 = new File("src/icons/cambiarUsuario.png");
        Image imagen2 = new Image(archivo2.toURI().toString(), 40, 40, true, true);
        ImageView cambiarUsuario = new ImageView(imagen2);
        botonCambiarUsuario = new Button("", cambiarUsuario);
        botonCambiarUsuario.setLayoutX(390);
        botonCambiarUsuario.setLayoutY(55);
        botonCambiarUsuario.setStyle("-fx-background-color: #" + "00adb5");
        botonCambiarUsuario.setTooltip(new Tooltip("Cambiar de usuario"));
        botonCambiarUsuario.setOnMouseEntered(event -> {
            botonCambiarUsuario.setStyle("-fx-background-color: #" + "aad8d3");
        });
        botonCambiarUsuario.setOnMouseExited(event -> {
            botonCambiarUsuario.setStyle("-fx-background-color: #" + "00adb5");
        });

        File archivo3 = new File("src/icons/salir.png");
        Image imagen3 = new Image(archivo3.toURI().toString(), 40, 40, true, true);
        ImageView salir = new ImageView(imagen3);
        botonSalir = new Button("", salir);
        botonSalir.setLayoutX(458);
        botonSalir.setLayoutY(55);
        botonSalir.setStyle("-fx-background-color: #" + "00adb5");
        botonSalir.setTooltip(new Tooltip("Salir"));
        botonSalir.setOnMouseEntered(event -> {
            botonSalir.setStyle("-fx-background-color: #" + "aad8d3");
        });
        botonSalir.setOnMouseExited(event -> {
            botonSalir.setStyle("-fx-background-color: #" + "00adb5");
        });

        File archivo4 = new File("src/icons/agregarUsuario.png");
        Image imagen4 = new Image(archivo4.toURI().toString(), 40, 40, true, true);
        ImageView agregarUsuario = new ImageView(imagen4);
        botonAdministrarUsuarios = new Button("", agregarUsuario);
        botonAdministrarUsuarios.setLayoutX(322);
        botonAdministrarUsuarios.setLayoutY(55);
        botonAdministrarUsuarios.setStyle("-fx-background-color: #" + "00adb5");
        botonAdministrarUsuarios.setTooltip(new Tooltip("Administar usuarios"));
        botonAdministrarUsuarios.setOnMouseEntered(event -> {
            botonAdministrarUsuarios.setStyle("-fx-background-color: #" + "aad8d3");
        });
        botonAdministrarUsuarios.setOnMouseExited(event -> {
            botonAdministrarUsuarios.setStyle("-fx-background-color: #" + "00adb5");
        });

        botonAgregarUsuario = new Button("Agregar");
        botonModificarUsuario = new Button("Modificar");
        botonEliminarUsuario = new Button("Eliminar");

        botonModificarUsuario.setDisable(true);
        botonEliminarUsuario.setDisable(true);


        botonSalir.setOnAction(event -> {
            Stage stage = (Stage) botonSalir.getScene().getWindow();
            stage.close();
        });

        botonCambiarUsuario.setOnAction(event -> {
            Login pantallaInicio = new Login();
            Stage stage = (Stage) botonSalir.getScene().getWindow();
            stage.close();
            Stage stage1 = new Stage();
            Login pantallaLogin = new Login();
            Scene scene = new Scene(pantallaLogin, 300, 275);
            stage1.setScene(scene);
            stage1.show();
        });

        botonAdministrarUsuarios.setOnAction(event -> {
            VBox pantallaAdministrarUsurios = new VBox();
            pantallaAdministrarUsurios.setSpacing(20);
            pantallaAdministrarUsurios.setPadding(new Insets(20, 20, 20, 20));
            HBox contenedorBotones1 = new HBox();
            contenedorBotones1.setSpacing(20);
            contenedorBotones1.setPadding(new Insets(20, 20, 20, 20));

            Stage stagePrincipal = new Stage();
            stagePrincipal.setScene(new Scene(pantallaAdministrarUsurios, 700, 500));
            stagePrincipal.centerOnScreen();
            stagePrincipal.setAlwaysOnTop(false);
            stagePrincipal.setResizable(false);
            stagePrincipal.initOwner(this.getScene().getWindow());
            stagePrincipal.initModality(Modality.APPLICATION_MODAL);



            TableView<Usuario> tablaUsuarios = new TableView<>();
            tablaUsuarios.setPrefSize(600, 300);

            TableColumn<Usuario, Integer> columnaNoUsuario = new TableColumn<>("No.Usuario");
            columnaNoUsuario.setCellValueFactory(new PropertyValueFactory<>("noUsuario"));
            columnaNoUsuario.setPrefWidth(100);

            TableColumn<Usuario, String> columnaNombre = new TableColumn<>("Nombre");
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnaNombre.setPrefWidth(200);

            TableColumn<Usuario, String> columnaContrasena = new TableColumn<>("Contraseña");
            columnaContrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
            columnaContrasena.setPrefWidth(200);

            TableColumn<Usuario, String> columnaTipo = new TableColumn<>("Tipo");
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaTipo.setPrefWidth(150);

            tablaUsuarios.getColumns().addAll(columnaNoUsuario, columnaNombre, columnaContrasena, columnaTipo);
            contenedorBotones1.getChildren().addAll(botonAgregarUsuario, botonModificarUsuario, botonEliminarUsuario);

            UsuarioDAO usuarioDAO = new UsuarioDAOImplement();

            try {
                listaUsuarios = usuarioDAO.obtenerUsuarios();
            } catch (Exception e) {
                e.printStackTrace();
            }

            tablaUsuarios.setItems(FXCollections.observableArrayList(listaUsuarios));

            TableView.TableViewSelectionModel<Usuario> modeloSeleccion = tablaUsuarios.getSelectionModel();
            modeloSeleccion.setSelectionMode(SelectionMode.SINGLE);

            ObservableList<Usuario> usuarioSeleccionado = modeloSeleccion.getSelectedItems();

            pantallaAdministrarUsurios.getChildren().addAll(tablaUsuarios, contenedorBotones1);

            tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    botonEliminarUsuario.setDisable(false);
                    botonModificarUsuario.setDisable(false);
                }else {
                    botonEliminarUsuario.setDisable(true);
                    botonModificarUsuario.setDisable(true);
                }
            });

            botonAgregarUsuario.setOnAction(event1 -> {
                VBox pantallaAgregarUsuario = new VBox();

                Stage stagePrincipal1 = new Stage();
                stagePrincipal1.setScene(new Scene(pantallaAgregarUsuario, 400, 400));
                stagePrincipal1.centerOnScreen();
                stagePrincipal1.setAlwaysOnTop(false);
                stagePrincipal1.setResizable(false);
                stagePrincipal1.initOwner(stagePrincipal);
                stagePrincipal1.initModality(Modality.APPLICATION_MODAL);

                TextField txtNombre = new TextField("");
                Label lbNombre = new Label("Nombre: ");

                TextField txtContrasena = new TextField("");
                Label lbContrasena = new Label("Contraseña: ");


                Label lbTipo = new Label("Tipo: ");
                RadioButton opcionAdministrador = new RadioButton("administrador");
                opcionAdministrador.setSelected(true);
                RadioButton opcionEmpleado = new RadioButton("empleado");


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

                ObjetosEditar.get(0).getChildren().addAll(lbNombre, txtNombre);
                ObjetosEditar.get(1).getChildren().addAll(lbContrasena, txtContrasena);
                ObjetosEditar.get(2).getChildren().addAll(lbTipo, opcionAdministrador, opcionEmpleado);
                ObjetosEditar.get(3).getChildren().addAll(btnAgregar,btnCancelar);

                opcionAdministrador.setOnAction(event2 -> {
                    if(opcionAdministrador.isSelected()){
                        opcionEmpleado.setSelected(false);
                    }
                });

                opcionEmpleado.setOnAction(event2 -> {
                    if(opcionEmpleado.isSelected()){
                        opcionAdministrador.setSelected(false);
                    }
                });


                btnAgregar.setOnAction(event2 -> {
                    String opcionSeleccionada;
                    if (opcionAdministrador.isSelected()) {
                        opcionSeleccionada = opcionAdministrador.getText();
                    } else {
                        opcionSeleccionada = opcionEmpleado.getText();
                    }

                    if(txtNombre.getText().trim().length() != 0 && txtContrasena.getText().trim().length() != 0 && opcionSeleccionada.trim().length() !=0) {
                        Usuario usuario = new Usuario(0, txtNombre.getText(), txtContrasena.getText(), opcionSeleccionada);
                        try {
                            if (usuarioDAO.agregarUsuario(usuario)) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Usuario agregado");
                                alert.setHeaderText(null);
                                alert.setContentText("Usuario agregado exitosamente!");
                                alert.showAndWait();
                                stagePrincipal1.close();

                                try {
                                    listaUsuarios=usuarioDAO.obtenerUsuarios();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                tablaUsuarios.getItems().clear();
                                tablaUsuarios.getItems().addAll(FXCollections.observableArrayList(listaUsuarios));
                                listaUsuarios.clear();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setContentText("No se pudo agregar el usuario!");

                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
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

                pantallaAgregarUsuario.getChildren().addAll(ObjetosEditar);
                stagePrincipal1.showAndWait();

            });

            botonModificarUsuario.setOnAction(event1 -> {
                if(usuarioSeleccionado.get(0) != null){

                    VBox pantallaModificarUsuario = new VBox();

                    Stage stagePrincipal1 = new Stage();
                    stagePrincipal1.setScene(new Scene(pantallaModificarUsuario, 400, 400));
                    stagePrincipal1.centerOnScreen();
                    stagePrincipal1.setAlwaysOnTop(false);
                    stagePrincipal1.setResizable(false);
                    stagePrincipal1.initOwner(stagePrincipal);
                    stagePrincipal1.initModality(Modality.APPLICATION_MODAL);

                    TextField txtNombre = new TextField("");
                    txtNombre.setText(usuarioSeleccionado.get(0).getNombre());
                    Label lbNombre = new Label("Nombre: ");

                    TextField txtContrasena = new TextField("");
                    txtContrasena.setText(usuarioSeleccionado.get(0).getContrasena());
                    Label lbContrasena = new Label("Contraseña: ");


                    Label lbTipo = new Label("Tipo: ");
                    RadioButton opcionAdministrador = new RadioButton("administrador");
                    opcionAdministrador.setSelected(true);
                    RadioButton opcionEmpleado = new RadioButton("empleado");


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

                    ObjetosEditar.get(0).getChildren().addAll(lbNombre, txtNombre);
                    ObjetosEditar.get(1).getChildren().addAll(lbContrasena, txtContrasena);
                    ObjetosEditar.get(2).getChildren().addAll(lbTipo, opcionAdministrador, opcionEmpleado);
                    ObjetosEditar.get(3).getChildren().addAll(btnGuardar,btnCancelar);

                    opcionAdministrador.setOnAction(event2 -> {
                        if(opcionAdministrador.isSelected()){
                            opcionEmpleado.setSelected(false);
                        }
                    });

                    opcionEmpleado.setOnAction(event2 -> {
                        if(opcionEmpleado.isSelected()){
                            opcionAdministrador.setSelected(false);
                        }
                    });


                    btnGuardar.setOnAction(event2 -> {
                        String opcionSeleccionada;
                        if (opcionAdministrador.isSelected()) {
                            opcionSeleccionada = opcionAdministrador.getText();
                        } else {
                            opcionSeleccionada = opcionEmpleado.getText();
                        }

                        if(txtNombre.getText().trim().length() != 0 && txtContrasena.getText().trim().length() != 0 && opcionSeleccionada.trim().length() !=0) {
                            usuarioSeleccionado.get(0).setNombre(txtNombre.getText());
                            usuarioSeleccionado.get(0).setContrasena(txtContrasena.getText());
                            usuarioSeleccionado.get(0).setTipo(opcionSeleccionada);
                            try {
                                if(usuarioDAO.update(usuarioSeleccionado.get(0))){
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Usuario modificado");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Usuario modificado exitosamente!");
                                    alert.showAndWait();
                                    stagePrincipal1.close();

                                    try {
                                        listaUsuarios=usuarioDAO.obtenerUsuarios();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    tablaUsuarios.getItems().clear();
                                    tablaUsuarios.getItems().addAll(FXCollections.observableArrayList(listaUsuarios));
                                    listaUsuarios.clear();
                                }else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setContentText("No se pudo modificar el usuario!");
                                    alert.showAndWait();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
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

                    pantallaModificarUsuario.getChildren().addAll(ObjetosEditar);
                    stagePrincipal1.showAndWait();
                }

            });

            botonEliminarUsuario.setOnAction(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Coonfirmar");
                alert.setContentText("¿Desea eliminar el usuario?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                        if(usuarioDAO.delete(usuarioSeleccionado.get(0).getNoUsuario())){
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Usuario eliminado");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Usuario eliminado exitosamente!");
                            alert1.showAndWait();

                            try {
                                listaUsuarios=usuarioDAO.obtenerUsuarios();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            tablaUsuarios.getItems().clear();
                            tablaUsuarios.getItems().addAll(FXCollections.observableArrayList(listaUsuarios));
                            listaUsuarios.clear();
                        }else {
                            Alert alert2= new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("Error");
                            alert2.setContentText("No se pudo eliminar el usuario!");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Avertencia");
                        alert1.setHeaderText(null);
                        alert1.setContentText("No se ha seleccionado ningun usuario!");
                        alert1.showAndWait();
                    }
                } else {
                    alert.close();
                }
            });

            stagePrincipal.showAndWait();

        });



        contenedorBotones.getChildren().addAll(botonAdministrarUsuarios, botonCambiarUsuario, botonSalir);
        contenedorPrincipal.getChildren().addAll(contenedorImagenTitulo, contenedorBotones);
        this.getChildren().addAll(contenedorPrincipal);


    }
}
