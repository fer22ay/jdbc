package mysql;

import dao.DAOException;
import dao.MatriculaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Matricula;

public class MySQLMatriculaDAO implements MatriculaDAO{

    final String INSERT = "INSERT INTO matriculas (alumno, asignatura, fecha, nota) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE matriculas SET asignatura = ?, fecha = ?, nota = ? WHERE alumno = ?";
    final String DELETE = "DELETE FROM matriculas WHERE alumno = ?";
    final String GETALL = "SELECT alumno, asignatura, fecha, nota FROM matriculas";
    final String GETONE = "SELECT alumno, asignatura, fecha, nota FROM matriculas WHERE alumno = ?";
    
    private Connection conn;
    
    public MySQLMatriculaDAO(Connection conn){
       this.conn = conn;
    }
    
    @Override
    public void insertar(Matricula a) throws DAOException{
        PreparedStatement stmt = null;
        try {
           stmt = conn.prepareStatement(INSERT);
           stmt.setLong(1, a.getAlumno());
           stmt.setLong(2, a.getAsignatura());
           stmt.setInt(3, a.getYear());
           
            if (stmt.executeUpdate() == 0) {
                throw new DAOException("No se ingresaron los datos..!!");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL. " + ex);
        }finally{
            if (stmt !=null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL. " + ex);
                }
            }
        }
    }

    @Override
    public void modificar(Matricula a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Matricula a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Matricula> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Matricula obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
