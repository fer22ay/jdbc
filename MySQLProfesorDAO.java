package mysql;

import dao.DAOException;
import dao.ProfesorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Profesor;

public class MySQLProfesorDAO implements ProfesorDAO{

    final String INSERT = "INSERT INTO profesores(id_profesor, nombre, apellidos) VALUES(?,?,?)";
    final String UPDATE = "UPDATE profesores SET nombre = ?, apellidos = ? WHERE id_profesor = ?";
    final String DELETE = "DELETE FROM profesores WHERE id_profesor = ?";
    final String GETALL = "SELECT id_profesor, nombre, apellidos FROM profesores";
    final String GETONE = "SELECT id_profesor, nombre, apellidos FROM profesores WHERE id_profesor = ?";
    
    private Connection conn;
    
    public MySQLProfesorDAO(Connection conn){
        this.conn = conn;
    }
    
    
    @Override
    public void insertar(Profesor a) throws DAOException{
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(INSERT);
            stmt.setLong(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setString(3, a.getApellidos());
            
            if (stmt.executeUpdate() == 0) {
                throw new DAOException("Puede que no se ingresaron los datos...!!!");
            }            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL." + ex);
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
    public void modificar(Profesor a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Profesor a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profesor> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Profesor obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
