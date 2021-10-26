package modelo;

import java.util.List;

public class Provedor {
    private int noProvedor;
    private String nombreProvedor;
    private String ciudad;
    private List<Producto> producto;

    public Provedor(int noProvedor, String nombreProvedor, String ciudad) {
        this.noProvedor = noProvedor;
        this.nombreProvedor = nombreProvedor;
        this.ciudad = ciudad;
    }

    public int getNoProvedor() {
        return noProvedor;
    }

    public void setNoProvedor(int noProvedor) {
        this.noProvedor = noProvedor;
    }

    public String getNombreProvedor() {
        return nombreProvedor;
    }

    public void setNombreProvedor(String nombreProvedor) {
        this.nombreProvedor = nombreProvedor;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "ProvedorDAO{" +
                "noProvedor=" + noProvedor +
                ", nombreProvedor='" + nombreProvedor + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
