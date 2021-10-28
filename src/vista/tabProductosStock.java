package vista;

import controlador.tabComprarProductosControlador;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import modelo.ProductoStock;
import modelo.ProductoStockDAO;
import modelo.ProductoStockDAOImplement;

import java.util.ArrayList;
import java.util.List;

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

        tablaProductosStock.getColumns().addAll(columnaNoProducto, columnaNombreProducto,columnaCantidad,columnaTipo,columnaPrecio);


        contenedorTablaFormulario.getChildren().addAll(tablaProductosStock);
        areaProductosStock.getChildren().addAll(contenedorTablaFormulario);
    }

}
