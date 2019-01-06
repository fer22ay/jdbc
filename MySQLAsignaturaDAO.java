package mysql;

import dao.AsignaturaDAO;
import dao.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Asignatura;

public class MySQLAsignaturaDAO implements AsignaturaDAO{

    final String INSERT = "INSERT INTO asignaturas (id_asignatura, nombre, profesor) VALUES(?,?,?)";
    final String UPDATE = "UPDATE asignaturas SET nombre = ? WHERE id_asignatura = ? AND profesor = ?";
    final String DELETE = "DELETE FROM asignaturas WHERE id_asignatura = ?";
    final String GETALL = "SELECT id_asignatura, nombre, profesor FROM asignaturas";
    final String GETONE = "SELECT id_asignatura, nombre, profesor FROM asignaturas WHERE id_asignatura = ?";
    
    private Connection conn;
    
    public MySQLAsignaturaDAO(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insertar(Asignatura a) throws DAOException{
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(INSERT);
            stmt.setLong(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setLong(3, a.getIdProfesor());
            
            if (stmt.executeUpdate() == 0) {
                throw new DAOException("Datos no se ingresaron correctamente.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL. " + ex);
        }finally{
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL. " + ex);
                }
            }
        }
    }

    @Override
    public void modificar(Asignatura a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Asignatura a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Asignatura> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Asignatura obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
