package vista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.ProductoStock;
import modelo.ProductoStockDAO;
import modelo.ProductoStockDAOImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class tabProductosStock extends Tab {

    AnchorPane areaProductosStock;
    double ancho;
    double alto;

    TableView<ProductoStock> tablaProductosStock;
    List<ProductoStock> listaProductosStock = new ArrayList<>();
    ProductoStock productoStock = new ProductoStock();


    public tabProductosStock(String text, AnchorPane areaProductosStock, double ancho, double alto) {
        super(text, areaProductosStock);
        this.areaProductosStock = areaProductosStock;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void componentesTabProductos(){
        areaProductosStock.setPrefSize(ancho, alto);

        VBox contenedorTablaFormulario = new VBox();
        HBox contenedorBotones = new HBox();
        contenedorBotones.setPadding(new Insets(20,20,20,20));
        contenedorBotones.setSpacing(20);
        contenedorTablaFormulario.setPrefSize(960, alto);
        contenedorTablaFormulario.setPadding(new Insets(20,20,20,20));

        tablaProductosStock= new TableView<>();
        tablaProductosStock.setPrefSize(800, 400);

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
        columnaPrecio.setPrefWidth(200);

        Button btnModificar= new Button("Modificar");
        Button btnEliminar= new Button("Eliminar");

        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);

        TableView.TableViewSelectionModel<ProductoStock> modeloSeleccion = tablaProductosStock.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.SINGLE);

        ObservableList<ProductoStock> productoSeleccionado = modeloSeleccion.getSelectedItems();

        ProductoStockDAO productoStockDAO = new ProductoStockDAOImplement();



        areaProductosStock.setOnMouseEntered(event -> {
            try {
                listaProductosStock=productoStockDAO.obtenerProductosStock();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tablaProductosStock.getItems().clear();
            tablaProductosStock.getItems().addAll(FXCollections.observableArrayList(listaProductosStock));
            listaProductosStock.clear();

        });

        btnModificar.setOnAction(event -> {

            if(productoSeleccionado.get(0)!=null){

                VBox pantallaEditar = new VBox();

                Stage stagePrincipal = new Stage();
                stagePrincipal.setScene(new Scene(pantallaEditar, 400, 500));
                stagePrincipal.centerOnScreen();
                stagePrincipal.setAlwaysOnTop(false);
                stagePrincipal.setResizable(false);
                stagePrincipal.initOwner(areaProductosStock.getScene().getWindow());
                stagePrincipal.initModality(Modality.APPLICATION_MODAL);



                TextField txtNoProducto = new TextField("");
                txtNoProducto.setText(String.valueOf(productoSeleccionado.get(0).getNoProductoStock()));

                txtNoProducto.setEditable(false);
                Label lbNo = new Label("No. Producto: ");

                TextField txtNombre = new TextField("");
                txtNombre.setText(productoSeleccionado.get(0).getNombreProducto());
                Label lbNombre = new Label("Nombre: ");

                TextField txtCantidad = new TextField("");
                txtCantidad.setText(String.valueOf(productoSeleccionado.get(0).getCantidad()));
                Label lbCantidad = new Label("Cantidad: ");

                TextField txtTipo = new TextField("");
                txtTipo.setText(productoSeleccionado.get(0).getTipo());
                Label lbTipo = new Label("Tipo: ");

                TextField txtPrecio = new TextField("");
                txtPrecio.setText(String.valueOf(productoSeleccionado.get(0).getPrecio()));
                Label lbPrecio = new Label("Precio: ");

                Button btnGuardar = new Button("Guardar");
                Button btnCancelar = new Button("Cancelar");


                ArrayList<HBox> ObjetosEditar = new ArrayList<HBox>();

                for(int i = 0; i<6; i++) {
                    HBox Columna =  new  HBox();
                    Columna.setSpacing(20);
                    Columna.setAlignment(Pos.CENTER);
                    Columna.setPadding(new Insets(20,20,20,20));
                    ObjetosEditar.add(i,Columna);
                }

                ObjetosEditar.get(0).getChildren().addAll(lbNo,txtNoProducto);
                ObjetosEditar.get(1).getChildren().addAll(lbNombre, txtNombre);
                ObjetosEditar.get(2).getChildren().addAll(lbCantidad, txtCantidad);
                ObjetosEditar.get(3).getChildren().addAll(lbTipo, txtTipo);
                ObjetosEditar.get(4).getChildren().addAll(lbPrecio, txtPrecio);
                ObjetosEditar.get(5).getChildren().addAll(btnCancelar, btnGuardar);

                btnGuardar.setOnAction(event1 -> {
                    productoSeleccionado.get(0).setNombreProducto(txtNombre.getText());
                    productoSeleccionado.get(0).setCantidad(Integer.parseInt(txtCantidad.getText()));
                    productoSeleccionado.get(0).setTipo(txtTipo.getText());
                    productoSeleccionado.get(0).setPrecio(Double.parseDouble(txtPrecio.getText()));
                    try {
                        if(productoStockDAO.actualizarProductosStockEditado(productoSeleccionado.get(0))){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Producto modificado");
                            alert.setHeaderText(null);
                            alert.setContentText("Producto modificado exitosamente!");
                            alert.showAndWait();
                            stagePrincipal.close();
                        }else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("No se pudo modificar el producto!");

                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                btnCancelar.setOnAction(event1 -> {
                    stagePrincipal.close();
                });

                pantallaEditar.getChildren().addAll(ObjetosEditar);

                stagePrincipal.showAndWait();
            }
        });


        btnEliminar.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Coonfirmar");
            alert.setContentText("¿Desea eliminar el producto?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    if(productoStockDAO.borrarProductoStock(productoSeleccionado.get(0).getNoProductoStock())){
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Producto eliminado");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Producto eliminado exitosamente!");
                        alert1.showAndWait();
                    }else {
                        Alert alert2= new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setContentText("No se pudo eliminar el producto!");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Avertencia");
                    alert1.setHeaderText(null);
                    alert1.setContentText("No se ha seleccionado ningun producto!");
                    alert1.showAndWait();
                }
            } else {
                alert.close();
            }
        });

        tablaProductosStock.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEliminar.setDisable(false);
                btnModificar.setDisable(false);
            }else {
                btnEliminar.setDisable(true);
                btnModificar.setDisable(true);
            }
        });

        tablaProductosStock.getColumns().addAll(columnaNoProducto, columnaNombreProducto,columnaCantidad,columnaTipo,columnaPrecio);
        contenedorBotones.getChildren().addAll(btnModificar, btnEliminar);


        contenedorTablaFormulario.getChildren().addAll(tablaProductosStock, contenedorBotones);
        areaProductosStock.getChildren().addAll(contenedorTablaFormulario);
    }

}
