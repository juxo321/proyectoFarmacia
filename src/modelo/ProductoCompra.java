package modelo;

public class ProductoCompra {
    private int id;
    private int noProducto;
    private String nombreProducto;
    private int cantidad;
    private double total;
    private Compra compra;

    public ProductoCompra(int id, int noProducto, String nombreProducto, int cantidad, double total) {
        this.id = id;
        this.noProducto = noProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public ProductoCompra() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoProducto() {
        return noProducto;
    }

    public void setNoProducto(int noProducto) {
        this.noProducto = noProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "ProductoCompra{" +
                "id=" + id +
                ", noProducto=" + noProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", compra=" + compra +
                '}';
    }
}
