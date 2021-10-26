package modelo;

public class Cliente {
    private int id;
    private String nombreCliente;
    private int edad;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int id, String nombreCliente, int edad, String direccion) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.edad = edad;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "ClienteDAO{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
