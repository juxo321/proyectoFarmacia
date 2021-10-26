package modelo;

import java.sql.Date;
import java.util.List;

public class Venta {

    private int noVenta;
    private int cantidad;
    private Date fecha;
    private double total;
    private Usuario usuario;
    private List<ProductoVenta> productosVenta;

    public Venta(int noVenta, int cantidad, Date fecha, double total) {
        this.noVenta = noVenta;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
    }

    public Venta() {

    }

    public int getNoVenta() {
        return noVenta;
    }

    public void setNoVenta(int noVenta) {
        this.noVenta = noVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ProductoVenta> getProductosVenta() {
        return productosVenta;
    }

    public void setProductosVenta(List<ProductoVenta> productosVenta) {
        this.productosVenta = productosVenta;
    }

    @Override
    public String toString() {
        return "VentaDAO{" +
                "noVenta=" + noVenta +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
