package camara.jose.connection;

import camara.jose.log.Log;
import camara.jose.utils.message.ErrorMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    /**
     * Atributos de clase
     */
    private static Connection conn;
    private static DBConnection _newInstance;

    /**
     * Metodo para realizar la conexion
     */
    private DBConnection() {
        try {
            DataConnection dc = load();
            conn= DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(), dc.getUsername(), dc.getPassword());
        } catch (SQLException e) {
            new ErrorMessage("No se pudo crear la conexion").showMessage();
            Log.warningLogging(e+"");
            conn=null;
        }
    }

    /**
     * Metodo que realiza la instancia de la clase
     * @return Devuelve el objeto con inicializado
     */
    public static Connection getConnect() {
        if(_newInstance==null) {
            _newInstance=new DBConnection();
        }
        return conn;
    }

    /**
     * Obtiene de un archivo xml los datos para necesario para realizar la conexion con la bbdd
     * @return Objeto con los datos para realizar la conexion
     */
    public DataConnection load() {
        DataConnection dc = new DataConnection();
        JAXBContext context;
        try {
            context=JAXBContext.newInstance(DataConnection.class);
            Unmarshaller um = context.createUnmarshaller();
            dc = (DataConnection) um.unmarshal(DBConnection.class.getResource("/connectiondata/connectionData.xml"));
        } catch (JAXBException e) {
            new ErrorMessage("No se pudieron obtener los datos de conexión.").showMessage();
            Log.warningLogging(e+"");
        }
        return dc;
    }

    /**
     * Procedimiento el cual finaliza la conexión
     */
    public static void close() {
        if(conn != null) {
            try {
                conn.close();
            }catch(SQLException ex) {
                Log.warningLogging(ex+"");
            }
        }
    }
}
