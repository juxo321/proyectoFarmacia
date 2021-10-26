package modelo;

public class Producto {
    private int noProducto;
    private String nombreProducto;
    private int cantidad;
    private String tipo;
    private double precio;
    private Provedor provedor;

    public Producto(int noProducto, String nombreProducto, int cantidad, String tipo, double precio) {
        this.noProducto = noProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.precio = precio;
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

    public Provedor getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedor provedor) {
        this.provedor = provedor;
    }

    @Override
    public String toString() {
        return "ProductoDAO{" +
                "noProducto=" + noProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
