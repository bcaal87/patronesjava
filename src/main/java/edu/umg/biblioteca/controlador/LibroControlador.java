package edu.umg.biblioteca.controlador;
import edu.umg.biblioteca.dao.LibroDAO;
import edu.umg.biblioteca.modelo.Libro;
import java.sql.SQLException;
import java.util.List;
public class LibroControlador {
 private final LibroDAO dao=new LibroDAO();
 private void validar(String titulo,String autor) {if(titulo==null||titulo.isBlank()||autor==null||autor.isBlank()) throw new IllegalArgumentException("Título y autor son obligatorios");}
 public void crear(String titulo,String autor) throws SQLException {validar(titulo,autor);dao.insertar(titulo.trim(),autor.trim());}
 public List<Libro> listar() throws SQLException {return dao.listar();}
 public void editar(int id,String titulo,String autor) throws SQLException {validar(titulo,autor);dao.actualizar(id,titulo.trim(),autor.trim());}
 public void borrar(int id) throws SQLException {dao.eliminar(id);}
}
