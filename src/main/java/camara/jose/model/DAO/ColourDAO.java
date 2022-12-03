package camara.jose.model.DAO;

import camara.jose.connection.DBConnection;
import camara.jose.log.Log;
import camara.jose.model.dataObject.Colour;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColourDAO {

    /**
     * Querys SQL
     */
    private static final String INSERT = "INSERT INTO colours (name,red,green,blue) VALUES (?,?,?,?)";
    private static final String GETCOLOUR = "SELECT name,red,green,blue FROM colours WHERE name=?";
    private  static final String GETALLCOLOURS = "SELECT name,red,green,blue FROM colours";

    /**
     * Atributos de clase
     */
    private Connection conn;

    /**
     * Constructor por defecto
     */
    public ColourDAO() {
        this.conn = DBConnection.getConnect();
    }

    /**
     * Guarda en la base de datos un objeto color
     * @param colour Color a almacenar
     * @return True o false si se realizo o no
     */
    public boolean insert(Colour colour){
        boolean result=false;
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, colour.getName());
            ps.setInt(2, colour.getRed());
            ps.setInt(3, colour.getGreen());
            ps.setInt(4, colour.getBlue());
            ps.executeUpdate();
            result=true;
            ps.close();
        } catch (SQLException e) {
            Log.warningLogging(e+"");
        }
        return result;
    }

    /**
     * Obtiene de la base de datos un color dado por su nombre
     * @param name Nombre del color a buscar
     * @return Color obtenido o null si no existe
     */
    public Colour get(String name) {
        Colour colour = null;
        try {
            PreparedStatement ps = conn.prepareStatement(GETCOLOUR);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            colour = new Colour();
            if(rs!=null){
                rs.next();
                colour.setName(rs.getString(1));
                colour.setRed(rs.getInt(2));
                colour.setGreen(rs.getInt(3));
                colour.setBlue(rs.getInt(4));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Log.warningLogging(e+"");
            colour=null;
        }
        return colour;
    }

    /**
     * Obtiene todos los colores existentes en la base de datos
     * @return List con colores o null si no existe ninguno
     */
    public List<Colour> getAll() {
        List<Colour> colours = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GETALLCOLOURS);
            while(rs.next()) {
                Colour aux = new Colour();
                aux.setName(rs.getString(1));
                aux.setRed(rs.getInt(2));
                aux.setGreen(rs.getInt(3));
                aux.setBlue(rs.getInt(4));
                colours.add(aux);
            }
            rs.close();
        } catch (SQLException e) {
            Log.warningLogging(e+"");
        }
        return colours;
    }
}
