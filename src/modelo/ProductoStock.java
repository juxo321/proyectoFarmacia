package modelo;

public class ProductoStock {
    private int noProductoStock;
    private String nombreProducto;
    private int cantidad;
    private String tipo;
    private double precio;

    public ProductoStock(int noProductoStock, String nombreProducto, int cantidad, String tipo, double precio) {
        this.noProductoStock = noProductoStock;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
    }

    public ProductoStock() {

    }

    public int getNoProductoStock() {
        return noProductoStock;
    }

    public void setNoProductoStock(int noProductoStock) {
        this.noProductoStock = noProductoStock;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ProductoStock{" +
                "noProductoStock=" + noProductoStock +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
