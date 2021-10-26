/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author fredycastaneda
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    // Librería de MySQL
    public String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "farmacia";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    // public String url = "jdbc:mariadb://localhost:3306/ejemplo_crud?user=root&password=";
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "";
    /**
     * // Librería de MySQL
     public String driver = "com.mysql.jdbc.Driver";

     // Nombre de la base de datos
     public String database = "ejemplo_crud";

     // Host
     public String hostname = "localhost";

     // Puerto
     public String port = "3306";

     // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
     public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

     // Nombre de usuario
     public String username = "root";

     // Clave de usuario
     public String password = "root";

     */

    public ConexionBD() {
        this.hostname = "localhost";
        this.port = "3306";
        this.database = "farmacia";
        this.username = "root";
        this.password = "";

        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    }

    public Connection getConnection() throws Exception{

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            throw new Exception("Error en ConexionDB. La causa es: " + ex.getCause().toString());
        }


    }

    /**
     * public class ConexionDB {

     // Librería de MySQL
     public String driver = "com.mysql.jdbc.Driver";

     // Nombre de la base de datos
     public String database = "ejemplo_crud";

     // Host
     public String hostname = "localhost";

     // Puerto
     public String port = "3306";

     // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
     public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

     // Nombre de usuario
     public String username = "root";

     // Clave de usuario
     public String password = "root";

     public Connection conectarMySQL() {
     Connection conn = null;

     try {
     Class.forName(driver);
     conn = DriverManager.getConnection(url, username, password);

     } catch (ClassNotFoundException | SQLException e) {
     System.out.println("Error en la conexion de la base de datos");
     e.printStackTrace();
     } //catch

     return conn;
     }

     }
     * Cierra la conexión con la base de datos y asigna null al objeto Connection.
     */

}
