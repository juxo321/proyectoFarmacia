package modelo;

import java.sql.Date;
import java.util.List;

public class Compra {

    private int noCompra;
    private int cantidad;
    private Date fecha;
    private double total;
    private List<ProductoCompra> productosCompra;

    public Compra(int noCompra, int cantidad, Date fecha, double total) {
        this.noCompra = noCompra;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
    }

    public Compra() {
    }

    public int getNoCompra() {
        return noCompra;
    }

    public void setNoCompra(int noCompra) {
        this.noCompra = noCompra;
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

    public List<ProductoCompra> getProductosCompra() {
        return productosCompra;
    }

    public void setProductosCompra(List<ProductoCompra> productosCompra) {
        this.productosCompra = productosCompra;
    }

    @Override
    public String toString() {
        return "CompraDAO{" +
                "noCompra=" + noCompra +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
