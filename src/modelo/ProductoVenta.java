package modelo;

public class ProductoVenta {
    private int id;
    private int noProducto;
    private String nombreProducto;
    private int cantidad;
    private double total;
    private Venta venta;

    public ProductoVenta(int id, int noProducto, String nombreProducto, int cantidad, double total) {
        this.id = id;
        this.noProducto = noProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public ProductoVenta() {

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

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "ProductoVentaDAO{" +
                "id=" + id +
                ", noProducto=" + noProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }
}
