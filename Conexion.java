package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    
    private Connection conexion = null;
    
    public Conexion() throws SQLException{
        try {
            conectar();
//            consulta();
            transaccion();
        } finally{
            cerrar();
        }
    }
    
    //Método para conexión a la base de datos.
    public void conectar() throws SQLException{
        String jdbc = "jdbc:mysql://localhost:3306/curso";
        conexion = DriverManager.getConnection(jdbc, "fernando", "1234");
        conexion.setAutoCommit(false);
        
    }
    
    //Consulta a la base de datos curso SELECT de todos los alumnos.
//    private void consulta() throws SQLException{
//        Statement stmt = conexion.createStatement();
//        ResultSet res = stmt.executeQuery("select * from alumnos");
//        while (res.next()) {            
//            int idAlumno = res.getInt("id_alumno");
//            String nombre = res.getString("nombre");
//            String apellidos = res.getString("apellidos");
//            System.out.println("Alumno: " + idAlumno + " Nombre: " + nombre + " " + apellidos);
//        }
//        res.close();
//    }
    
    //Método para insertar en la base de datos.
    private void transaccion() throws SQLException{
        final String PROFESOR = "INSERT INTO profesores(id_profesor, nombre, apellidos) VALUES(?, ?, ?)";
        final String ASIGNATURA = "INSERT INTO asignaturas(id_asignatura, nombre, profesor) VALUES(?, ?, ?)";
        PreparedStatement profesor = null, asignatura = null;
        
        try {
            //Se utiliza prepareStatement para evitar inyección SQL. Ya que es más seguro. 
            profesor = conexion.prepareStatement(PROFESOR);
            profesor.setInt(1, 50);
            profesor.setString(2, "Pepito");
            profesor.setString(3, "Pérez");
            profesor.executeUpdate();
            
            asignatura = conexion.prepareStatement(ASIGNATURA);
            asignatura.setInt(1, 100);
            asignatura.setString(2, "Fundamentos de Bases de Datos");
            asignatura.setInt(3, 50);
            asignatura.executeUpdate();
            
            conexion.commit();
            System.out.println("Datos ingresados...!!");
        } catch (SQLException e){    
            conexion.rollback();
            e.printStackTrace();
        }finally{
            if (profesor !=null){
                profesor.close();
            }
            if (asignatura != null) {
                asignatura.close();
            }
        }
        
    }
    
    
    //Método para cerrar la conexión.
    public void cerrar() throws SQLException{
        if (conexion != null) {
            conexion.close();
        }
    }
    
//    private static final Logger LOG = LoggerFactory.getLogger(Conexion.class);
    
    public static void main(String [] args){
        try {
            new Conexion();
        } catch (SQLException e) {
//            LOG.error("Error durante el uso de JDBC", e);
        }
    }
    
}
