package edu.umg.biblioteca.dao;
import edu.umg.biblioteca.modelo.Libro;
import edu.umg.biblioteca.config.Conexion;
import java.sql.*;
import java.util.*;
public class LibroDAO {
 public void insertar(String titulo,String autor) throws SQLException {
  try(Connection c=Conexion.abrir();PreparedStatement p=c.prepareStatement("INSERT INTO Libros(titulo,autor) VALUES(?,?)")) {p.setString(1,titulo);p.setString(2,autor);p.executeUpdate();}
 }
 public List<Libro> listar() throws SQLException {
  List<Libro> libros=new ArrayList<>();
  try(Connection c=Conexion.abrir();PreparedStatement p=c.prepareStatement("SELECT id,titulo,autor FROM Libros ORDER BY id");ResultSet r=p.executeQuery()) {
   while(r.next()) libros.add(new Libro(r.getInt("id"),r.getString("titulo"),r.getString("autor")));
  } return libros;
 }
 public void actualizar(int id,String titulo,String autor) throws SQLException {
  try(Connection c=Conexion.abrir();PreparedStatement p=c.prepareStatement("UPDATE Libros SET titulo=?,autor=? WHERE id=?")) {p.setString(1,titulo);p.setString(2,autor);p.setInt(3,id);if(p.executeUpdate()==0) throw new SQLException("Libro no encontrado");}
 }
 public void eliminar(int id) throws SQLException {
  try(Connection c=Conexion.abrir();PreparedStatement p=c.prepareStatement("DELETE FROM Libros WHERE id=?")) {p.setInt(1,id);if(p.executeUpdate()==0) throw new SQLException("Libro no encontrado");}
 }
}
