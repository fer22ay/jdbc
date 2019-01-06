package mysql;

import dao.AlumnoDAO;
import dao.DAOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Alumno;

public class MySQLAlumnoDAO implements AlumnoDAO{

    final String INSERT = "INSERT INTO alumnos (id_alumno, nombre, apellidos, fecha_nac) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nac = ? WHERE id_alumno = ?";
    final String DELETE = "DELETE FROM alumnos WHERE id_alumno = ?";
    final String GETALL = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos";
    final String GETONE = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos WHERE id_alumno = ?";
    
    private Connection conn;
    
    public MySQLAlumnoDAO(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insertar(Alumno a) throws DAOException{
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(INSERT);
            stmt.setLong(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setString(3, a.getApellidos());
            stmt.setDate(4,new Date(a.getFechaNacimiento().getTime()));
            
            if (stmt.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
        }catch (SQLException ex){
            throw new DAOException("Error en SQL" + ex);
        }finally{
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL" + ex);
                }
            }
        }
    }

    @Override
    public void modificar(Alumno a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Alumno a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Alumno> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Alumno obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
